package com.example.PracticeCRUD.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.PracticeCRUD.Dto.WishlistDto.WishlistRequestDto;
import com.example.PracticeCRUD.Dto.WishlistDto.WishlistResponseDto;
import com.example.PracticeCRUD.service.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wishlists")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    // Get all
    @GetMapping
    public ResponseEntity<List<WishlistResponseDto>> getAllWishlists() {
        return ResponseEntity.ok(wishlistService.getAllWishlists());
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<WishlistResponseDto> getWishlistById(@PathVariable Long id) {
        WishlistResponseDto dto = wishlistService.getWishlistById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    // Create
    @PostMapping
    public ResponseEntity<WishlistResponseDto> createWishlist(@RequestBody WishlistRequestDto dto) {
        WishlistResponseDto saved = wishlistService.createWishlist(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<WishlistResponseDto> updateWishlist(
            @PathVariable Long id,
            @RequestBody WishlistRequestDto dto) {

        WishlistResponseDto updated = wishlistService.updateWishlist(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long id) {
        wishlistService.deleteWishlist(id);
        return ResponseEntity.noContent().build();
    }
}
