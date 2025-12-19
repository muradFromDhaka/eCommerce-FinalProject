package com.example.PracticeCRUD.controller;

import com.example.PracticeCRUD.Dto.VendorPayoutDto.VendorPayoutRequestDto;
import com.example.PracticeCRUD.Dto.VendorPayoutDto.VendorPayoutResponseDto;
import com.example.PracticeCRUD.service.VendorPayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-payouts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VendorPayoutController {

    private final VendorPayoutService payoutService;

    @PostMapping
    public ResponseEntity<VendorPayoutResponseDto> create(
            @RequestBody VendorPayoutRequestDto dto) {
        return ResponseEntity.ok(payoutService.createPayout(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorPayoutResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(payoutService.getPayoutById(id));
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorPayoutResponseDto>> getByVendor(
            @PathVariable Long vendorId) {
        return ResponseEntity.ok(payoutService.getPayoutsByVendorId(vendorId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<VendorPayoutResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestBody VendorPayoutRequestDto dto) {
        return ResponseEntity.ok(payoutService.updatePayoutStatus(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        payoutService.deletePayout(id);
        return ResponseEntity.noContent().build();
    }
}
