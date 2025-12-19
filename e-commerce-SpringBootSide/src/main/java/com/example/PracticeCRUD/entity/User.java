package com.example.PracticeCRUD.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.PracticeCRUD.Enum.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"reviews", "orders"})
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity{
	    
	    private String name;
	    
	    @Column(unique = true)
	    private String email; // unique
	    
	    
	    private String password;
	    
	    private String phone;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(length= 30)
	    private UserRole role; 
	    
	    private boolean active;
	    
	    @Column(length= 50)
	    private String address;
	    
	    private String profilePicture;
	    
	    @OneToMany(mappedBy = "user")
	    @JsonIgnoreProperties("user")
	    private List<Order> orders;

	    @OneToMany(mappedBy = "user")
	    @JsonIgnoreProperties("user")
	    private List<Review> reviews;
	    
	    
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonIgnoreProperties("items")
	    private List<Cart> carts = new ArrayList<>();
        
	    @OneToMany(mappedBy = "user")
	    @JsonIgnoreProperties("user")
	    private List<Wishlist> wishlists;
	
	

}


