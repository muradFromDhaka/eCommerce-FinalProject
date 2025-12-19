package com.example.PracticeCRUD.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.PracticeCRUD.Dto.ProductDto.ProductDetailsDto;
import com.example.PracticeCRUD.Dto.ProductDto.ProductListDto;
import com.example.PracticeCRUD.Dto.ProductDto.ProductRequestDto;
import com.example.PracticeCRUD.Dto.ProductDto.ProductResponseDto;
import com.example.PracticeCRUD.Mapper.ProductMapper;
import com.example.PracticeCRUD.entity.Category;
import com.example.PracticeCRUD.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.PracticeCRUD.entity.Product;
import com.example.PracticeCRUD.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	
	private final ProductRepository productRepo;
    private final CategoryRepository categoryRepository;
	private final FileStorageService fileStorageService;


	public ProductResponseDto create(ProductRequestDto dto) {
	    Product product = ProductMapper.toEntity(dto);

	    if (dto.getCategoryId() != null) {
	        Category category = categoryRepository.findById(dto.getCategoryId())
	                .orElseThrow(() -> new RuntimeException("Category not found"));
	        product.setCategory(category);
	    }

	    // Vendor assign করলে এখানে handle করো
	    // product.setVendor(...)

	    productRepo.save(product);

	    return ProductMapper.toResponseDto(product);
	}

	public ProductResponseDto update(Long id, ProductRequestDto dto) {
	    Product product = productRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found"));

	    product.setName(dto.getName());
	    product.setDescription(dto.getDescription());
	    product.setPrice(dto.getPrice());
	    product.setStockQuantity(dto.getStockQuantity());
	    product.setAvailable(dto.isAvailable());
	    product.setReleaseDate(dto.getReleaseDate());
	    product.setImageUrls(dto.getImageUrls());

	    if (dto.getCategoryId() != null) {
	        Category category = categoryRepository.findById(dto.getCategoryId())
	                .orElseThrow(() -> new RuntimeException("Category not found"));
	        product.setCategory(category);
	    }

	    // Vendor assign করলে এখানে handle করো
	    // product.setVendor(...)

	    productRepo.save(product);

	    return ProductMapper.toResponseDto(product);
	}

	public List<ProductListDto> getAllProducts() {
	    return productRepo.findAll()
	            .stream()
	            .map(ProductMapper::toListDto)
	            .toList();
	}

	public ProductDetailsDto getProductById(Long id) {
	    Product product = productRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found"));
	    return ProductMapper.toDetailsDto(product);
	}

	public boolean delete(Long id) {
		Product delete = productRepo.findById(id)
				.orElseThrow(() -> new RuntimeException(id + " id isn't found."));

		if(delete.isAvailable()) {
			productRepo.deleteById(id);
			return true;
		}else {
			return false;
		}


	}








	public List<Product> getProductByCategoryId(Long category) {
		return productRepo.findByCategoryId(category);
	}

	public List<Product> getProductByCategoryName(String name) {
		return productRepo.findByCategoryName(name);
	}
	
	public List<Product> searchProductsByName(String name) {
	    return productRepo.findProductsByName(name);
	}

	public List<Product> getProductsByPriceRange(String name, BigDecimal min, BigDecimal max) {
	    return productRepo.findByPriceBetween(min, max);
	}

	public Page<Product> getAvailableProducts(Pageable pageable) {
	    return productRepo.findByAvailableTrue(pageable);
	}

	public Product createProductWithImage(Product product, MultipartFile[] files) throws IOException{
		
		List<String> urls = new ArrayList<>();
		if(files != null && files.length >0) {
			for (MultipartFile file : files) {
				if(!file.isEmpty()) {
					urls.add(fileStorageService.saveFile(file));
				}
			}
		}
		
		product.setImageUrls(urls);
		return productRepo.save(product);
	}
	
	public Product updateProductWithImage(Long id, Product updatedProduct, MultipartFile[] files) throws IOException{
		
		Optional<Product> existing = productRepo.findById(id);
		
		if(existing.isEmpty()) {
		    throw new RuntimeException("Product not found with id: " + id);
		}

		
		Product existingProduct = existing.get();
		
		existingProduct.setName(updatedProduct.getName());
		existingProduct.setDescription(updatedProduct.getDescription());
		existingProduct.setPrice(updatedProduct.getPrice());
		existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
		existingProduct.setCategory(updatedProduct.getCategory());
		existingProduct.setReviews(updatedProduct.getReviews());
		existingProduct.setAvailable(updatedProduct.isAvailable());
		existingProduct.setReleaseDate(updatedProduct.getReleaseDate());
		existingProduct.setImageUrls(updatedProduct.getImageUrls());
		
		
		List<String> finalImageUrls = existingProduct.getImageUrls() != null
			    ? new ArrayList<>(existingProduct.getImageUrls())
			    : new ArrayList<>();

			// Frontend থেকে JSON images থাকলে add করা
			if (updatedProduct.getImageUrls() != null) {
			    finalImageUrls.addAll(updatedProduct.getImageUrls());
			}

			// নতুন আপলোড করা ফাইলগুলো add করা
			if (files != null && files.length > 0) {
			    for (MultipartFile file : files) {
			        if (!file.isEmpty()) {
			            finalImageUrls.add(fileStorageService.saveFile(file));
			        }
			    }
			}

			// সবশেষে set করা
			existingProduct.setImageUrls(finalImageUrls);

		return productRepo.save(existingProduct);
	}

	public void deleteSingleImage(Long id, String file) throws IOException{
		Optional<Product> existing = productRepo.findById(id);
		
		if(existing.isEmpty()) {
			throw new RuntimeException("this image not found.");
		}
		
		Product product = existing.get();
		
		if(file != null) {
			if(!product.getImageUrls().remove(file)) {
				throw new RuntimeException("this image not found in database.");
			}
			
			fileStorageService.deleteFile(file);
			
			productRepo.save(product);
			
		}
	}

	// ❌ DELETE ENTIRE Product (with images)
	public String deleteProductWithImage(Long id) throws IOException {
		Optional<Product> existing = productRepo.findById(id);
		if (existing.isEmpty()) {
			throw new RuntimeException("produc not found.");
		}

		Product product = existing.get();

//		for (String img : product.getImageUrls()) {
//			fileStorageService.deleteFile(img);
//		}

		List<String> images = product.getImageUrls();
		if (images != null && !images.isEmpty()) {
		    for (String img : images) {
		        fileStorageService.deleteFile(img);
		    }
		}


		productRepo.deleteById(id);

		return " Product deleted successfully.";
	}
	
}
