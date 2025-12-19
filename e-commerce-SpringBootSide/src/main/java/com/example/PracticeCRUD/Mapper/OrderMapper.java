package com.example.PracticeCRUD.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.example.PracticeCRUD.Dto.OrderDto.OrderDto;
import com.example.PracticeCRUD.Dto.OrderDto.OrderItemDto;
import com.example.PracticeCRUD.entity.Order;
import com.example.PracticeCRUD.entity.OrderItem;

public class OrderMapper {

	 public static OrderDto toDto(Order order) {
	        if (order == null) return null;

	        List<OrderItemDto> items = order.getOrderItems()
	                .stream()
	                .map(OrderMapper::toItemDto)
	                .collect(Collectors.toList());

	        return new OrderDto(
	                order.getId(),
	                order.getUser().getId(),
	                items,
	                order.getTotalPrice(),
	                order.getOrderStatus().name()
	        );
	    }

	    private static OrderItemDto toItemDto(OrderItem item) {
	        BigDecimal totalPrice =
	                item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

	        OrderItemDto dto = new OrderItemDto();
	        dto.setId(item.getId());
	        dto.setProductId(item.getProduct().getId());
	        dto.setProductName(item.getProduct().getName());
	        dto.setVendorId(item.getVendor().getId());
	        dto.setVendorName(item.getVendor().getShopName());
	        dto.setQuantity(item.getQuantity());
	        dto.setUnitPrice(item.getUnitPrice());
	        dto.setTotalPrice(totalPrice);

	        return dto;
	    }
}
