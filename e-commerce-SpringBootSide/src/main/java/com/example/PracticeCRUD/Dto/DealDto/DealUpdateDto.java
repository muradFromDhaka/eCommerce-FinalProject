package com.example.PracticeCRUD.Dto.DealDto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DealUpdateDto {

    private String title;
    private Integer discountPercent;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

