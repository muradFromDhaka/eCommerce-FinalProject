package com.example.PracticeCRUD.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.PracticeCRUD.Dto.ProductViewDto.UserProductViewRequestDto;
import com.example.PracticeCRUD.Dto.ProductViewDto.UserProductViewResponseDto;
import com.example.PracticeCRUD.service.UserProductViewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user-product-views")
@RequiredArgsConstructor
public class UserProductViewController {

    private final UserProductViewService viewService;

    // Get all views
    @GetMapping
    public ResponseEntity<List<UserProductViewResponseDto>> getAllViews() {
        return ResponseEntity.ok(viewService.getAllViews());
    }

    // Get view by id
    @GetMapping("/{id}")
    public ResponseEntity<UserProductViewResponseDto> getViewById(@PathVariable Long id) {
        UserProductViewResponseDto view = viewService.getViewById(id);
        if (view == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(view);
    }

    // Create view
    @PostMapping
    public ResponseEntity<UserProductViewResponseDto> createView(@RequestBody UserProductViewRequestDto dto) {
        UserProductViewResponseDto saved = viewService.createView(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Delete view
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteView(@PathVariable Long id) {
        viewService.deleteView(id);
        return ResponseEntity.noContent().build();
    }
}
