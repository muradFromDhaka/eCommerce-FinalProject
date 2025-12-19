package com.example.PracticeCRUD.Dto.CartDto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
	 private Long id;
	    private Long userId;
	    private List<CartItemDto> items;
	    private BigDecimal totalPrice;
}
