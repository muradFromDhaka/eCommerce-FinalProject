package com.example.PracticeCRUD.Dto.DealDto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DealCreateDto {

    private String title;
    private Integer discountPercent;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long productId;
}
