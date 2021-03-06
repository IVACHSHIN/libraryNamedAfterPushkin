package com.example.librarynamedafterpushkin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookDetailsDto extends BookSmallDto {
    private List<BookHistoryDto> bookHistories;
}