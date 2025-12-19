package com.example.PracticeCRUD.Dto.ReviewDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {

	    private Double rating;
	    private String comment;
	    private Long userId;
	    private Long productId;
	
}
