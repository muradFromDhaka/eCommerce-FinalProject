package com.example.PracticeCRUD.Dto.ProductDto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProductResponseDto {

    private Long id;                  // Product ID
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private boolean available;
    private LocalDate releaseDate;

    private Long categoryId;
    private String categoryName;

    private Long vendorId;            // Flattened vendor reference
    private String vendorName;

    private Double averageRating;
    private Integer totalReviews;
    private List<String> imageUrls;
}
