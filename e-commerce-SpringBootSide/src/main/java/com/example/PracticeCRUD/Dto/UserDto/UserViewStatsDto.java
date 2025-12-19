package com.example.PracticeCRUD.Dto.UserDto;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserViewStatsDto {

    private Long productId;
    private LocalDateTime viewedAt;
}

