package com.example.PracticeCRUD.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.PracticeCRUD.Dto.ReviewDto.ReviewRequestDto;
import com.example.PracticeCRUD.Dto.ReviewDto.ReviewResponseDto;
import com.example.PracticeCRUD.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // -------------------------
    // Get all reviews
    // -------------------------
    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    // -------------------------
    // Get review by id
    // -------------------------
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable Long id) {
    	ReviewResponseDto review = reviewService.getReviewById(id);
        if (review == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(review);
    }

    // -------------------------
    // Create review
    // -------------------------
    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto dto) {
    	ReviewResponseDto saved = reviewService.createReview(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // -------------------------
    // Update review
    // -------------------------
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewRequestDto dto) {

    	ReviewResponseDto updated = reviewService.updateReview(id, dto);
        return ResponseEntity.ok(updated);
    }

    // -------------------------
    // Delete review
    // -------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
