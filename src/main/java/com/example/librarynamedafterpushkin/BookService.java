package com.example.librarynamedafterpushkin;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    public Book get(Long id) {
        //TODO get book by id from the DB
        Book testBook = new Book();
        testBook.setName("Идиот");
        testBook.setAuthor("Достоевский");
        testBook.setYear(1995);
        return testBook;
    }

    public List<Book> getAll() {
        //TODO save the book to the DB

        Book testBook = new Book();
        testBook.setName("Идиот");
        testBook.setAuthor("Достоевский");
        testBook.setYear(1995);

        Book testBook2 = new Book();
        testBook2.setName("Идиот2");
        testBook2.setAuthor("Достоевский");
        testBook2.setYear(2020);
        return List.of(testBook,testBook2);
    }

    public Book create(Book book) {

        return book;
    }
    public Book update(Long id, Book book) {
        //TODO get book id end update it
        return book;
    }
    public void delete(Long id) {
        //TODO delete book by id
    }
}
