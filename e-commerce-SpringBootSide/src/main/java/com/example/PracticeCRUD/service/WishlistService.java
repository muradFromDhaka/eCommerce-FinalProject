package com.example.PracticeCRUD.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.PracticeCRUD.Dto.WishlistDto.WishlistRequestDto;
import com.example.PracticeCRUD.Dto.WishlistDto.WishlistResponseDto;
import com.example.PracticeCRUD.Mapper.WishlistMapper;
import com.example.PracticeCRUD.entity.Product;
import com.example.PracticeCRUD.entity.User;
import com.example.PracticeCRUD.entity.Wishlist;
import com.example.PracticeCRUD.repository.ProductRepository;
import com.example.PracticeCRUD.repository.UserRepository;
import com.example.PracticeCRUD.repository.WishlistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // Get all wishlists
    public List<WishlistResponseDto> getAllWishlists() {
        return wishlistRepository.findAll().stream()
                .map(WishlistMapper::toDto)
                .collect(Collectors.toList());
    }

    // Get wishlist by id
    public WishlistResponseDto getWishlistById(Long id) {
        return wishlistRepository.findById(id)
                .map(WishlistMapper::toDto)
                .orElse(null);
    }

    // Create wishlist
    @Transactional
    public WishlistResponseDto createWishlist(WishlistRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Set<Product> products = dto.getProductIds().stream()
                .map(pid -> productRepository.findById(pid)
                        .orElseThrow(() -> new RuntimeException("Product not found: " + pid)))
                .collect(Collectors.toSet());

        Wishlist wishlist = WishlistMapper.toEntity(dto, user, products);
        Wishlist saved = wishlistRepository.save(wishlist);
        return WishlistMapper.toDto(saved);
    }

    // Update wishlist
    @Transactional
    public WishlistResponseDto updateWishlist(Long id, WishlistRequestDto dto) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Set<Product> products = dto.getProductIds().stream()
                .map(pid -> productRepository.findById(pid)
                        .orElseThrow(() -> new RuntimeException("Product not found: " + pid)))
                .collect(Collectors.toSet());

        wishlist.setUser(user);
        wishlist.setProducts(products);
        Wishlist saved = wishlistRepository.save(wishlist);
        return WishlistMapper.toDto(saved);
    }

    // Delete wishlist
    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }
}
