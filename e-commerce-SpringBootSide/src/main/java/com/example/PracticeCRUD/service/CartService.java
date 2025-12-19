package com.example.PracticeCRUD.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PracticeCRUD.Dto.CartDto.CartDto;
import com.example.PracticeCRUD.Mapper.CartMapper;
import com.example.PracticeCRUD.entity.Cart;
import com.example.PracticeCRUD.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    // Get all carts
    public List<CartDto> getAllCarts() {
        return cartRepository.findAll()
                .stream()
                .map(CartMapper::toDto)
                .toList();
    }

    // Get cart by id
    public CartDto getCartById(Long id) {
        return cartRepository.findById(id)
                .map(CartMapper::toDto)
                .orElse(null);
    }

    // Save / Update cart
    public CartDto saveCart(Cart cart) {

        if (cart.getItems() != null) {
            cart.getItems().forEach(item -> {
                item.setCart(cart);   // relationship fix
                item.updateTotalPrice();
            });
        }

        Cart saved = cartRepository.save(cart);
        return CartMapper.toDto(saved);
    }

    // Delete cart
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
