package com.example.PracticeCRUD.Mapper;

import com.example.PracticeCRUD.Dto.categoryDto.CategoryRequestDto;
import com.example.PracticeCRUD.Dto.categoryDto.CategoryResponseDto;
import com.example.PracticeCRUD.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDto dto){
        if(dto == null) return  null;

        Category category = new Category();

        category.setName(dto.getName());
        category.setDescription((dto.getDescription()));
        category.setImageUrl(dto.getImageUrl());

        return category;
    }

    public static CategoryResponseDto toResponseDto(Category cateogry){
        if(cateogry == null) return null;

        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(cateogry.getId());
        dto.setName(cateogry.getName());
        dto.setDescription(cateogry.getDescription());
        dto.setImageUrl(cateogry.getImageUrl());

        return dto;
    }

}
