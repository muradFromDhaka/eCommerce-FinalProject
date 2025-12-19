package com.example.PracticeCRUD.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.PracticeCRUD.Dto.ReviewDto.ReviewRequestDto;
import com.example.PracticeCRUD.Dto.ReviewDto.ReviewResponseDto;
import com.example.PracticeCRUD.Mapper.ReviewMapper;
import com.example.PracticeCRUD.entity.Product;
import com.example.PracticeCRUD.entity.Review;
import com.example.PracticeCRUD.entity.User;
import com.example.PracticeCRUD.repository.ProductRepository;
import com.example.PracticeCRUD.repository.ReviewRepository;
import com.example.PracticeCRUD.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // -------------------------
    // Get all reviews
    // -------------------------
    public List<ReviewResponseDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(ReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    // -------------------------
    // Get review by id
    // -------------------------
    public ReviewResponseDto getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewMapper::toDto)
                .orElse(null);
    }

    // -------------------------
    // Create review
    // -------------------------
    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Review review = ReviewMapper.toEntity(dto, user, product);
        Review saved = reviewRepository.save(review);

        return ReviewMapper.toDto(saved);
    }

    // -------------------------
    // Update review
    // -------------------------
    @Transactional
    public ReviewResponseDto updateReview(Long id, ReviewRequestDto dto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        User user = dto.getUserId() != null
                ? userRepository.findById(dto.getUserId()).orElse(null)
                : null;
        Product product = dto.getProductId() != null
                ? productRepository.findById(dto.getProductId()).orElse(null)
                : null;

        ReviewMapper.updateEntity(review, dto, user, product);
        Review saved = reviewRepository.save(review);

        return ReviewMapper.toDto(saved);
    }

    // -------------------------
    // Delete review
    // -------------------------
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
