package com.example.PracticeCRUD.controller;

import java.util.List;

import com.example.PracticeCRUD.Dto.categoryDto.CategoryRequestDto;
import com.example.PracticeCRUD.Dto.categoryDto.CategoryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PracticeCRUD.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryResponseDto>> getAllCategory(){
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id) {
	    return ResponseEntity.ok(service.getById(id));
	}

	
	
	@PostMapping
	public ResponseEntity<CategoryResponseDto> create(@RequestBody CategoryRequestDto category){
		return ResponseEntity.ok(service.save(category));
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryResponseDto> update(@PathVariable Long id, @RequestBody CategoryRequestDto category){
		return ResponseEntity.ok(service.update(id, category));
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
