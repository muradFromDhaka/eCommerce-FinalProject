package com.example.PracticeCRUD.controller;

import com.example.PracticeCRUD.Dto.VendorDto.VendorRequestDto;
import com.example.PracticeCRUD.Dto.VendorDto.VendorResponseDto;
import com.example.PracticeCRUD.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VendorController {

    private final VendorService vendorService;

    @PostMapping
    public ResponseEntity<VendorResponseDto> create(@RequestBody VendorRequestDto dto) {
        return ResponseEntity.ok(vendorService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorResponseDto> update(@PathVariable Long id, @RequestBody VendorRequestDto dto) {
        return ResponseEntity.ok(vendorService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VendorResponseDto>> getAll() {
        return ResponseEntity.ok(vendorService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vendorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
