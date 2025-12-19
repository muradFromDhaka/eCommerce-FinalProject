package com.example.PracticeCRUD.Dto.VendorDto;

import com.example.PracticeCRUD.Enum.VendorStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorResponseDto {

    private Long id;
    private String shopName;
    private String slug;
    private String description;
    private VendorStatus status;
    private Double rating;
    private Long userId;
    private String userName; // Optional: for display purposes
}
