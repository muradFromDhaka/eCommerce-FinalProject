package com.example.PracticeCRUD.Dto.WishlistDto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistRequestDto {
    private Long userId;
    private Set<Long> productIds;
}
