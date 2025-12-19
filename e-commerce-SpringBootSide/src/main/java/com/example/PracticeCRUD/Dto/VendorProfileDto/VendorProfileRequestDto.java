package com.example.PracticeCRUD.Dto.VendorProfileDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorProfileRequestDto {

    private Long vendorId;         // Vendor reference
    private String businessEmail;
    private String phone;
    private String address;
    private String logoUrl;
    private String bannerUrl;
}

