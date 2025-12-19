package com.example.PracticeCRUD.controller;

import com.example.PracticeCRUD.Dto.VendorProfileDto.VendorProfileRequestDto;
import com.example.PracticeCRUD.Dto.VendorProfileDto.VendorProfileResponseDto;
import com.example.PracticeCRUD.service.VendorProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-profiles")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VendorProfileController {

    private final VendorProfileService profileService;

    @PostMapping
    public ResponseEntity<VendorProfileResponseDto> create(@RequestBody VendorProfileRequestDto dto) {
        return ResponseEntity.ok(profileService.createProfile(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorProfileResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<VendorProfileResponseDto> getByVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(profileService.getProfileByVendorId(vendorId));
    }

    @GetMapping
    public ResponseEntity<List<VendorProfileResponseDto>> getAll() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorProfileResponseDto> update(
            @PathVariable Long id,
            @RequestBody VendorProfileRequestDto dto) {
        return ResponseEntity.ok(profileService.updateProfile(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
