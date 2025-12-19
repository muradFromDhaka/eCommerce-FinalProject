package com.example.PracticeCRUD.Dto.ReviewDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {

	    private Long id;
	    private Double rating;
	    private String comment;
	    private Long userId;
	    private String userName;
	    private Long productId;
	    private String productName;
}
