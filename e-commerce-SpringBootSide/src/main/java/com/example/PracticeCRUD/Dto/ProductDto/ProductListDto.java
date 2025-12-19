package com.example.PracticeCRUD.Dto.ProductDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductListDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean available;
    private Integer stockQuantity;
    private String thumbnailUrl;
    private Long vendorId;
    private String vendorName;
}
