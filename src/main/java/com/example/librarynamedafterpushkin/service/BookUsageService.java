package com.example.librarynamedafterpushkin.service;

import com.example.librarynamedafterpushkin.entity.Book;
import com.example.librarynamedafterpushkin.entity.BookHistory;
import com.example.librarynamedafterpushkin.entity.BookInUse;
import com.example.librarynamedafterpushkin.entity.Client;
import com.example.librarynamedafterpushkin.repository.BookHistoryRepository;
import com.example.librarynamedafterpushkin.repository.BookInUseRepository;
import com.github.dockerjava.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookUsageService {
    private final BookService bookService;
    private final ClientService clientService;
    private final BookHistoryRepository bookHistoryRepository;
    private final BookInUseRepository bookInUseRepository;
    private final TimeService timeService;

    @Value("${time.expired.after}")
    private Integer expiredAfterDays;

    public List<BookInUse> getExpired(Integer minExpiredDays) {
        return bookInUseRepository.findByInUseFromBefore(timeService.currentDate().minusDays(minExpiredDays));
    }

    public BookInUse takeBook(Long clientId, Long bookId) {
        Client client = clientService.get(clientId);
        Book book = bookService.get(bookId);

        book.setAvailable(false);

        BookInUse bookInUse = new BookInUse();
        bookInUse.setClient(client);
        bookInUse.setBook(book);

        log.info("Client {} took a book {}", clientId, bookId);

        return bookInUseRepository.save(bookInUse);
    }

    public BookHistory returnBook(Long clientId, Long bookId) {
        Client client = clientService.get(clientId);
        Book book = bookService.get(bookId);

        book.setAvailable(true);

        Optional<BookInUse> optional = bookInUseRepository.findByClientAndBook(client, book);

        if (optional.isEmpty()) {
            throw new NotFoundException(String.format("Client with id %d doesn't use a book with id %d", clientId, bookId));
        }

        BookHistory bookHistory = new BookHistory();
        bookHistory.setClient(client);
        bookHistory.setBook(book);
        bookHistory.setInUseFrom(optional.get().getInUseFrom());
        bookHistory.setInUseTo(LocalDate.now());

        bookInUseRepository.delete(optional.get());
        BookHistory save = bookHistoryRepository.save(bookHistory);

        log.info("Client {} returns a book {}", clientId, bookId);

        return save;
    }
}