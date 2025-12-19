package com.example.PracticeCRUD.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
@ToString(exclude = {"imageUrls", "category", "reviews"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product extends BaseEntity{
	
	    @Column(nullable = false, length=100, name= "product_name")
	    private String name;

	    @Column(length=1000)
	    private String description;
        
	    @Column(nullable = false, precision= 10, scale=2)
	    private BigDecimal price;

	    private Integer stockQuantity;

	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "category_id")
	    @JsonIgnoreProperties("products")
	    private Category category;
	    
	    @OneToMany(mappedBy = "product")
	    @JsonIgnoreProperties("product")
	    private List<Deal> deals;
	    
	    
	    @OneToMany(mappedBy = "product")
	    @JsonIgnoreProperties("product")
	    private List<Review> reviews;
	    
	    
	    @OneToMany(mappedBy = "product")
	    @JsonIgnoreProperties("product")
	    private List<CartItem> cartItem;
	    
	    private boolean available;

	    private LocalDate releaseDate;
	    
	    private Double averageRating;
	    private Integer totalReviews;

	    @ElementCollection
	    @CollectionTable(name="product_images", joinColumns = @JoinColumn(name="product_id"))
	    @Column(name="image_url")
	    private List<String> imageUrls;
	    
	    @ManyToMany(mappedBy = "products")
	    private Set<Wishlist> wishlists;

	    @ManyToOne
	    @JoinColumn(name = "vendor_id")
	    private Vendor vendor;


	    
}
