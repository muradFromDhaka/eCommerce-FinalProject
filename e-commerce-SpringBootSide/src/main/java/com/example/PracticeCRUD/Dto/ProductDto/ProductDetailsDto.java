package com.example.PracticeCRUD.Dto.ProductDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProductDetailsDto {

    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private boolean available;

    private LocalDate releaseDate;

    private Double averageRating;
    private Integer totalReviews;

    private List<String> imageUrls;

    private Long categoryId;
    private String categoryName;

    private Long vendorId;
    private String vendorName;
}
