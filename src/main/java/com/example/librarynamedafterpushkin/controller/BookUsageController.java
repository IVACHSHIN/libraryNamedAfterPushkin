package com.example.librarynamedafterpushkin.controller;

import com.example.librarynamedafterpushkin.dto.ExpiredInUseDto;
import com.example.librarynamedafterpushkin.entity.BookHistory;
import com.example.librarynamedafterpushkin.entity.BookInUse;
import com.example.librarynamedafterpushkin.mapper.BookInUseMapper;
import com.example.librarynamedafterpushkin.service.BookUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookUsageController {

    private final BookUsageService bookUsageService;
    private final BookInUseMapper bookInUseMapper;

    @GetMapping("/usage/expired")
    public List<ExpiredInUseDto> expired(@RequestParam(defaultValue = "${time.expired.after}") Integer minExpiredDays) {
        List<BookInUse> expired = bookUsageService.getExpired(minExpiredDays);

        return expired.stream()
                .map(bookInUseMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/usage/client/{clientId}/book/{bookId}")
    public BookInUse takeBook(@PathVariable Long clientId,
                              @PathVariable Long bookId) {
        return bookUsageService.takeBook(clientId, bookId);
    }
    @DeleteMapping("/usage/client/{clientId}/book/{bookId}")
    public BookHistory returnBook(@PathVariable Long clientId,
                                  @PathVariable Long bookId) {
        return bookUsageService.returnBook(clientId, bookId);
    }
}
