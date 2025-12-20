package com.example.PracticeCRUD.controller;

import com.example.PracticeCRUD.Dto.VendorDto.VendorDashboardDto;
import com.example.PracticeCRUD.service.VendorDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor-dashboard")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VendorDashboardController {

    private final VendorDashboardService dashboardService;

    @GetMapping("/{vendorId}")
    public ResponseEntity<VendorDashboardDto> getDashboard(
            @PathVariable Long vendorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(dashboardService.getVendorDashboard(vendorId, page, size));
    }
}

