package com.example.PracticeCRUD.Dto.VendorProfileDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorProfileResponseDto {

    private Long id;
    private Long vendorId;
    private String vendorName; // shopName from Vendor
    private String businessEmail;
    private String phone;
    private String address;
    private String logoUrl;
    private String bannerUrl;
}

