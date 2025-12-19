package com.example.PracticeCRUD.Mapper;

import com.example.PracticeCRUD.Dto.ProductDto.*;
import com.example.PracticeCRUD.entity.Product;


public class ProductMapper {

    // RequestDTO → Entity
    public static Product toEntity(ProductRequestDto dto) {
        if (dto == null) return null;

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setAvailable(dto.isAvailable());
        product.setReleaseDate(dto.getReleaseDate());
        product.setImageUrls(dto.getImageUrls());
        // Category & Vendor set হবে Service layer-এ
        return product;
    }

    // Entity → ResponseDTO
    public static ProductResponseDto toResponseDto(Product product) {
        if (product == null) return null;

        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setAvailable(product.isAvailable());
        dto.setReleaseDate(product.getReleaseDate());
        dto.setImageUrls(product.getImageUrls());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }

        if (product.getVendor() != null) {
            dto.setVendorId(product.getVendor().getId());
            dto.setVendorName(product.getVendor().getShopName());
        }

        dto.setAverageRating(product.getAverageRating());
        dto.setTotalReviews(product.getTotalReviews());

        return dto;
    }

    // Entity → ListDTO (lightweight)
    public static ProductListDto toListDto(Product product) {
        if (product == null) return null;

        ProductListDto dto = new ProductListDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setAvailable(product.isAvailable());

        if (product.getImageUrls() != null && !product.getImageUrls().isEmpty()) {
            dto.setThumbnailUrl(product.getImageUrls().get(0));
        }

        return dto;
    }

    // Entity → DetailsDTO
    public static ProductDetailsDto toDetailsDto(Product product) {
        if (product == null) return null;

        ProductDetailsDto dto = new ProductDetailsDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setAvailable(product.isAvailable());
        dto.setReleaseDate(product.getReleaseDate());
        dto.setImageUrls(product.getImageUrls());

        if (product.getCategory() != null) {
            dto.setCategoryName(product.getCategory().getName());
        }

        dto.setAverageRating(product.getAverageRating());
        dto.setTotalReviews(product.getTotalReviews());

        if (product.getVendor() != null) {
            dto.setVendorId(product.getVendor().getId());
            dto.setVendorName(product.getVendor().getShopName());
        }

        return dto;
    }
}
