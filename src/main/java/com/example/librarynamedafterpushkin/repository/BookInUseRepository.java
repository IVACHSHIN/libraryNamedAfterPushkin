package com.example.librarynamedafterpushkin.repository;

import com.example.librarynamedafterpushkin.entity.Book;
import com.example.librarynamedafterpushkin.entity.BookInUse;
import com.example.librarynamedafterpushkin.entity.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface BookInUseRepository extends PagingAndSortingRepository<BookInUse, Long> {

    Optional<BookInUse> findByClientAndBook(Client client, Book book);
}
