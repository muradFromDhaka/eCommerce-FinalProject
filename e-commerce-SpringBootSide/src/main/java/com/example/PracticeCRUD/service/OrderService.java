package com.example.PracticeCRUD.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.PracticeCRUD.Dto.OrderDto.OrderDto;
import com.example.PracticeCRUD.Dto.OrderDto.OrderItemRequestDto;
import com.example.PracticeCRUD.Dto.OrderDto.OrderRequestDto;
import com.example.PracticeCRUD.Enum.OrderStatus;
import com.example.PracticeCRUD.Enum.VendorOrderStatus;
import com.example.PracticeCRUD.Mapper.OrderMapper;
import com.example.PracticeCRUD.entity.Order;
import com.example.PracticeCRUD.entity.OrderItem;
import com.example.PracticeCRUD.entity.Product;
import com.example.PracticeCRUD.entity.User;
import com.example.PracticeCRUD.entity.Vendor;
import com.example.PracticeCRUD.entity.VendorOrder;
import com.example.PracticeCRUD.repository.OrderItemRepository;
import com.example.PracticeCRUD.repository.OrderRepository;
import com.example.PracticeCRUD.repository.ProductRepository;
import com.example.PracticeCRUD.repository.UserRepository;
import com.example.PracticeCRUD.repository.VendorOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final OrderItemRepository orderItemRepo;
    private final VendorOrderRepository vendorOrderRepo;

    // ==========================
    // PLACE ORDER (MAIN METHOD)
    // ==========================
    @Transactional
    public OrderDto placeOrder(OrderRequestDto dto) {

        User user = getUser(dto.getUserId());

        Order order = createOrder(user);

        List<OrderItem> orderItems = createOrderItems(order, dto.getItems());

        createVendorOrders(order, orderItems);

        calculateAndSetTotal(order, orderItems);

        Order savedOrder = orderRepo.save(order);
        return OrderMapper.toDto(savedOrder);
    }

    // ==========================
    // GET ORDER BY ID
    // ==========================
    @Transactional(readOnly = true)
    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.toDto(order);
    }

    // ==========================
    // GET ORDERS BY USER
    // ==========================
    @Transactional(readOnly = true)
    public List<OrderDto> getOrdersByUser(Long userId) {
        return orderRepo.findByUserId(userId)
                .stream()
                .map(OrderMapper::toDto)
                .toList();
    }

    // ==========================
    // HELPER METHODS
    // ==========================

    private User getUser(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Order createOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDING);
        return orderRepo.save(order);
    }

    private List<OrderItem> createOrderItems(
            Order order,
            List<OrderItemRequestDto> itemsDto) {

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemRequestDto dto : itemsDto) {

            Product product = productRepo.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Stock validation
            if (product.getStockQuantity() < dto.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product: " + product.getName());
            }

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setVendor(product.getVendor());
            item.setQuantity(dto.getQuantity());
            item.setUnitPrice(product.getPrice());

            // Reduce stock
            product.setStockQuantity(product.getStockQuantity() - dto.getQuantity());

            items.add(item);
        }

        return orderItemRepo.saveAll(items);
    }

    private void createVendorOrders(Order order, List<OrderItem> items) {

        Map<Vendor, List<OrderItem>> vendorWiseItems =
                items.stream()
                        .collect(Collectors.groupingBy(OrderItem::getVendor));

        for (Map.Entry<Vendor, List<OrderItem>> entry : vendorWiseItems.entrySet()) {

            Vendor vendor = entry.getKey();
            List<OrderItem> vendorItems = entry.getValue();

            BigDecimal subtotal = vendorItems.stream()
                    .map(i -> i.getUnitPrice()
                            .multiply(BigDecimal.valueOf(i.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            VendorOrder vendorOrder = new VendorOrder();
            vendorOrder.setOrder(order);
            vendorOrder.setVendor(vendor);
            vendorOrder.setSubtotal(subtotal);
            vendorOrder.setStatus(VendorOrderStatus.PENDING);

            vendorOrderRepo.save(vendorOrder);
        }
    }

    private void calculateAndSetTotal(Order order, List<OrderItem> items) {

        BigDecimal total = items.stream()
                .map(i -> i.getUnitPrice()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(total);
    }
}
