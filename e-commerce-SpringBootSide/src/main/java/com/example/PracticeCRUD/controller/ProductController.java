package com.example.PracticeCRUD.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.example.PracticeCRUD.Dto.ProductDto.ProductDetailsDto;
import com.example.PracticeCRUD.Dto.ProductDto.ProductListDto;
import com.example.PracticeCRUD.Dto.ProductDto.ProductRequestDto;
import com.example.PracticeCRUD.Dto.ProductDto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.PracticeCRUD.entity.Product;
import com.example.PracticeCRUD.service.ProductService;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins ="*")
public class ProductController {


	private final ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductListDto>> getAllProducts() {
	    return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDetailsDto> getById(@PathVariable Long id) {
	    return ResponseEntity.ok(productService.getProductById(id));
	}

	@PostMapping
	public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto dto) {
	    return ResponseEntity.ok(productService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDto> update(
	        @PathVariable Long id,
	        @RequestBody ProductRequestDto dto) {
	    return ResponseEntity.ok(productService.update(id, dto));
	}


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }














	
	
	
	
	@GetMapping("/category/{id}")
	public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long id) {
	    return ResponseEntity.ok(productService.getProductByCategoryId(id));
	}


	@GetMapping("/category/search")
	public ResponseEntity<List<Product>> getProductsByCategory(
			@RequestParam(required = false) String name ) {
	    return ResponseEntity.ok(productService.getProductByCategoryName(name));
	}
	
	
	@GetMapping("/searchProducts")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
	    return ResponseEntity.ok(productService.searchProductsByName(name));
	}
	
	
	
	@GetMapping("/search/price")
	public ResponseEntity<List<Product>> filterByPrice(
			@RequestParam String name,
	        @RequestParam BigDecimal min,
	        @RequestParam BigDecimal max) {
	    return ResponseEntity.ok(productService.getProductsByPriceRange(name,min, max));
	}

	
	
	@GetMapping("/available")
	public ResponseEntity<Page<Product>> getAvailableProducts(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "name") String sort
	) {
	    Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
	    Page<Product> products = productService.getAvailableProducts(pageable);
	    return ResponseEntity.ok(products);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto product){
		return ResponseEntity.ok(productService.update(id, product));
	}

	
	@PostMapping("/createProductWithImage")
	public ResponseEntity<Product> createProductWithImage(
			@RequestPart("product") Product product,
			@RequestPart("files") MultipartFile[] files) throws IOException{
		
		
		return ResponseEntity.ok(productService.createProductWithImage(product, files));
	}
	
	
	
	@PutMapping("/updateProductWithImage/{id}")
	public ResponseEntity<Product> updateProductWithImage(
			@PathVariable Long id,
			@RequestPart("product") Product product,
			@RequestPart("files") MultipartFile[] files) throws IOException{
		
		return ResponseEntity.ok(productService.updateProductWithImage(id, product, files));
	}
	
	
	@DeleteMapping("/deleteSingleImage/{id}")
	public ResponseEntity<Void> deleteSingleImage(
			@PathVariable Long id,
			@RequestParam String file){
		
		try {
            productService.deleteSingleImage(id, file);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	@DeleteMapping("/deleteProductWithImage/{id}")
	public ResponseEntity<Void> deleteProductWithImage(@PathVariable Long id){
		try {
            productService.deleteProductWithImage(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}

	
}
