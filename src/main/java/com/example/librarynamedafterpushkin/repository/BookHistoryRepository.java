package com.example.librarynamedafterpushkin.repository;

import com.example.librarynamedafterpushkin.entity.BookHistory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookHistoryRepository extends PagingAndSortingRepository<BookHistory, Long> {
}
