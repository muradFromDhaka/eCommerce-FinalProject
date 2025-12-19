package com.example.PracticeCRUD.Dto.CartDto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
	 private Long id;
	    private Long productId;       // শুধু product id পাঠানো হচ্ছে
	    private String productName;   // optional: product name
	    private int quantity;
	    private BigDecimal price;
	    private BigDecimal totalPrice;
}
