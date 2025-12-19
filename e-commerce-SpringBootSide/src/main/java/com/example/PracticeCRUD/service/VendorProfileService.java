package com.example.PracticeCRUD.service;

import com.example.PracticeCRUD.Dto.VendorProfileDto.VendorProfileRequestDto;
import com.example.PracticeCRUD.Dto.VendorProfileDto.VendorProfileResponseDto;
import com.example.PracticeCRUD.Mapper.VendorProfileMapper;
import com.example.PracticeCRUD.entity.Vendor;
import com.example.PracticeCRUD.entity.VendorProfile;
import com.example.PracticeCRUD.repository.VendorProfileRepository;
import com.example.PracticeCRUD.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorProfileService {

    private final VendorProfileRepository profileRepo;
    private final VendorRepository vendorRepo;

    public VendorProfileResponseDto createProfile(VendorProfileRequestDto dto) {

        Vendor vendor = vendorRepo.findById(dto.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        VendorProfile profile = VendorProfileMapper.toEntity(dto, vendor);
        profileRepo.save(profile);

        return VendorProfileMapper.toResponseDto(profile);
    }

    public VendorProfileResponseDto getProfileById(Long id) {
        VendorProfile profile = profileRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("VendorProfile not found"));

        return VendorProfileMapper.toResponseDto(profile);
    }

    public VendorProfileResponseDto getProfileByVendorId(Long vendorId) {
        VendorProfile profile = profileRepo.findByVendorId(vendorId)
                .orElseThrow(() -> new RuntimeException("VendorProfile not found"));

        return VendorProfileMapper.toResponseDto(profile);
    }

    public VendorProfileResponseDto updateProfile(Long id, VendorProfileRequestDto dto) {

        VendorProfile profile = profileRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("VendorProfile not found"));

        if (dto.getBusinessEmail() != null) profile.setBusinessEmail(dto.getBusinessEmail());
        if (dto.getPhone() != null) profile.setPhone(dto.getPhone());
        if (dto.getAddress() != null) profile.setAddress(dto.getAddress());
        if (dto.getLogoUrl() != null) profile.setLogoUrl(dto.getLogoUrl());
        if (dto.getBannerUrl() != null) profile.setBannerUrl(dto.getBannerUrl());

        profileRepo.save(profile);

        return VendorProfileMapper.toResponseDto(profile);
    }

    public List<VendorProfileResponseDto> getAllProfiles() {
        return profileRepo.findAll()
                .stream()
                .map(VendorProfileMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteProfile(Long id) {
        if (!profileRepo.existsById(id)) throw new RuntimeException("VendorProfile not found");
        profileRepo.deleteById(id);
    }
}
