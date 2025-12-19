package com.example.PracticeCRUD.Mapper;

import com.example.PracticeCRUD.Dto.ProductViewDto.UserProductViewRequestDto;
import com.example.PracticeCRUD.Dto.ProductViewDto.UserProductViewResponseDto;
import com.example.PracticeCRUD.entity.Product;
import com.example.PracticeCRUD.entity.User;
import com.example.PracticeCRUD.entity.UserProductView;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserProductViewMapper {

    // -------------------------
    // Entity -> Response DTO
    // -------------------------
    public static UserProductViewResponseDto toDto(UserProductView view) {
        if (view == null) return null;

        Long userId = view.getUser() != null ? view.getUser().getId() : null;
        String userName = view.getUser() != null ? view.getUser().getName() : null;

        Long productId = view.getProduct() != null ? view.getProduct().getId() : null;
        String productName = view.getProduct() != null ? view.getProduct().getName() : null;

        return new UserProductViewResponseDto(
                view.getId(),
                userId,
                userName,
                productId,
                productName,
                view.getViewedAt()
        );
    }

    // -------------------------
    // Request DTO -> Entity
    // -------------------------
    public static UserProductView toEntity(UserProductViewRequestDto dto, User user, Product product) {
        if (dto == null) return null;

        UserProductView view = new UserProductView();
        view.setUser(Objects.requireNonNull(user, "User must not be null"));
        view.setProduct(Objects.requireNonNull(product, "Product must not be null"));
        view.setViewedAt(LocalDateTime.now());

        return view;
    }
}

