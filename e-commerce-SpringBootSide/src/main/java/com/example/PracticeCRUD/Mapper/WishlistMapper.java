package com.example.PracticeCRUD.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.example.PracticeCRUD.Dto.WishlistDto.WishlistRequestDto;
import com.example.PracticeCRUD.Dto.WishlistDto.WishlistResponseDto;
import com.example.PracticeCRUD.entity.Product;
import com.example.PracticeCRUD.entity.User;
import com.example.PracticeCRUD.entity.Wishlist;

public class WishlistMapper {

    // -------------------------
    // Entity -> Response DTO
    // -------------------------
    public static WishlistResponseDto toDto(Wishlist wishlist) {
        if (wishlist == null) return null;

        Long userId = wishlist.getUser() != null ? wishlist.getUser().getId() : null;
        String userName = wishlist.getUser() != null ? wishlist.getUser().getName() : null;

        Set<WishlistResponseDto.ProductDto> products = wishlist.getProducts() != null
                ? wishlist.getProducts().stream()
                    .map(p -> new WishlistResponseDto.ProductDto(p.getId(), p.getName()))
                    .collect(Collectors.toSet())
                : Set.of();

        return new WishlistResponseDto(
                wishlist.getId(),
                userId,
                userName,
                products
        );
    }

    // -------------------------
    // Request DTO -> Entity
    // -------------------------
    public static Wishlist toEntity(WishlistRequestDto dto, User user, Set<Product> products) {
        if (dto == null) return null;

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProducts(products);

        return wishlist;
    }
}
