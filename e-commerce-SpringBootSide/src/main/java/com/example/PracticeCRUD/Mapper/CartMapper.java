package com.example.PracticeCRUD.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.PracticeCRUD.Dto.CartDto.CartDto;
import com.example.PracticeCRUD.Dto.CartDto.CartItemDto;
import com.example.PracticeCRUD.entity.Cart;
import com.example.PracticeCRUD.entity.CartItem;

public class CartMapper {

    public static CartDto toDto(Cart cart) {
        if (cart == null) return null;

        return new CartDto(
                cart.getId(),
                cart.getUser() != null ? cart.getUser().getId() : null,
                toItemDtoList(cart.getItems()),
                cart.getTotalPrice()
        );
    }

    // -------------------------
    // CartItem → CartItemDto
    // -------------------------
    public static CartItemDto toItemDto(CartItem item) {
        if (item == null) return null;

        return new CartItemDto(
                item.getId(),
                item.getProduct() != null ? item.getProduct().getId() : null,
                item.getProduct() != null ? item.getProduct().getName() : null,
                item.getQuantity(),
                item.getPrice(),
                item.getTotalPrice()
        );
    }

    // -------------------------
    // List<CartItem> → List<CartItemDto>
    // -------------------------
    private static List<CartItemDto> toItemDtoList(List<CartItem> items) {
        if (items == null) return List.of();

        return items.stream()
                .map(CartMapper::toItemDto)
                .collect(Collectors.toList());
    }
}

