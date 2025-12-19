package com.example.PracticeCRUD.Dto.VendorDto;

import com.example.PracticeCRUD.Enum.VendorStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorRequestDto {

    private String shopName;
    private String slug;
    private String description;
    private VendorStatus status;  // Optional, default PENDING
    private Long userId;          // Link to User
}
