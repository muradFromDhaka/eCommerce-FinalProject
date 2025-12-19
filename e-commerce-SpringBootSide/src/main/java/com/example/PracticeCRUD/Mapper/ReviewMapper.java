package com.example.PracticeCRUD.Mapper;

import com.example.PracticeCRUD.Dto.ReviewDto.ReviewRequestDto;
import com.example.PracticeCRUD.Dto.ReviewDto.ReviewResponseDto;
import com.example.PracticeCRUD.entity.Product;
import com.example.PracticeCRUD.entity.Review;
import com.example.PracticeCRUD.entity.User;

import java.util.Objects;

public class ReviewMapper {

    // -------------------------
    // Entity -> Response DTO
    // -------------------------
    public static ReviewResponseDto toDto(Review review) {
        if (review == null) return null;

        Long userId = review.getUser() != null ? review.getUser().getId() : null;
        String userName = review.getUser() != null ? review.getUser().getName() : null;

        Long productId = review.getProduct() != null ? review.getProduct().getId() : null;
        String productName = review.getProduct() != null ? review.getProduct().getName() : null;

        return new ReviewResponseDto(
                review.getId(),
                review.getRating(),
                review.getComment(),
                userId,
                userName,
                productId,
                productName
        );
    }

    // -------------------------
    // Request DTO -> Entity
    // -------------------------
    public static Review toEntity(ReviewRequestDto dto, User user, Product product) {
        if (dto == null) return null;

        Review review = new Review();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setUser(Objects.requireNonNull(user, "User must not be null"));
        review.setProduct(Objects.requireNonNull(product, "Product must not be null"));

        return review;
    }

    // -------------------------
    // Update existing entity
    // -------------------------
    public static void updateEntity(Review review, ReviewRequestDto dto, User user, Product product) {
        if (review == null || dto == null) return;

        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        if (user != null) review.setUser(user);
        if (product != null) review.setProduct(product);
    }
}
