package com.example.PracticeCRUD.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.PracticeCRUD.Dto.ProductViewDto.UserProductViewRequestDto;
import com.example.PracticeCRUD.Dto.ProductViewDto.UserProductViewResponseDto;
import com.example.PracticeCRUD.Mapper.UserProductViewMapper;
import com.example.PracticeCRUD.entity.Product;
import com.example.PracticeCRUD.entity.User;
import com.example.PracticeCRUD.entity.UserProductView;
import com.example.PracticeCRUD.repository.ProductRepository;
import com.example.PracticeCRUD.repository.UserProductViewRepository;
import com.example.PracticeCRUD.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProductViewService {

    private final UserProductViewRepository viewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // Get all views
    public List<UserProductViewResponseDto> getAllViews() {
        return viewRepository.findAll().stream()
                .map(UserProductViewMapper::toDto)
                .collect(Collectors.toList());
    }

    // Get view by id
    public UserProductViewResponseDto getViewById(Long id) {
        return viewRepository.findById(id)
                .map(UserProductViewMapper::toDto)
                .orElse(null);
    }

    // Create view
    @Transactional
    public UserProductViewResponseDto createView(UserProductViewRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        UserProductView view = UserProductViewMapper.toEntity(dto, user, product);
        UserProductView saved = viewRepository.save(view);
        return UserProductViewMapper.toDto(saved);
    }

    // Delete view
    public void deleteView(Long id) {
        viewRepository.deleteById(id);
    }
}
