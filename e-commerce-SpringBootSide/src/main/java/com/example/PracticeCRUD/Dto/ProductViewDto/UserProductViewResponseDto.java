package com.example.PracticeCRUD.Dto.ProductViewDto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProductViewResponseDto {
    private Long id;
    private Long userId;
    private String userName;
    private Long productId;
    private String productName;
    private LocalDateTime viewedAt;
}

