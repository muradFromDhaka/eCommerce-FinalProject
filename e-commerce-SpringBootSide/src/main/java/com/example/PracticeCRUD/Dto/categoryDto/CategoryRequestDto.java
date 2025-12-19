package com.example.PracticeCRUD.Dto.categoryDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryRequestDto {

    private String name;
    private String description;
    private String imageUrl;
}
