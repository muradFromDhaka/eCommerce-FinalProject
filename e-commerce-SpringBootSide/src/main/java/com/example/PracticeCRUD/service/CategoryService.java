package com.example.PracticeCRUD.service;

import java.util.List;


import com.example.PracticeCRUD.Dto.categoryDto.CategoryRequestDto;
import com.example.PracticeCRUD.Dto.categoryDto.CategoryResponseDto;
import com.example.PracticeCRUD.Mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.PracticeCRUD.entity.Category;
import com.example.PracticeCRUD.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	public List<CategoryResponseDto> getAll(){

		return categoryRepository.findAll().stream()
				.map(p -> CategoryMapper.toResponseDto(p)).toList();
	}
	
	public CategoryResponseDto getById(Long id) {
	    Category category = categoryRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
	    return CategoryMapper.toResponseDto(category);
	}
	
	
	
	public CategoryResponseDto save(CategoryRequestDto dto) {
        Category category = CategoryMapper.toEntity(dto);
       Category savedCategory = categoryRepository.save(category);
		return CategoryMapper.toResponseDto(savedCategory);
	}
	
	public CategoryResponseDto update(Long id, CategoryRequestDto updateCategory) {
		Category existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with id " + id));
		
		existingCategory.setName(updateCategory.getName());
		existingCategory.setDescription(updateCategory.getDescription());
		existingCategory.setImageUrl(updateCategory.getImageUrl());
		Category savedCategory = categoryRepository.save(existingCategory);
		return CategoryMapper.toResponseDto(savedCategory);
		
	}
	
	
	public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        categoryRepository.delete(category);
	}
}
