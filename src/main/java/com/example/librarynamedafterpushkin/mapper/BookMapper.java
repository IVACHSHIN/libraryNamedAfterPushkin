package com.example.librarynamedafterpushkin.mapper;
import com.example.librarynamedafterpushkin.dto.BookDetailsDto;
import com.example.librarynamedafterpushkin.dto.BookSmallDto;
import com.example.librarynamedafterpushkin.entity.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    BookSmallDto toSmall(Book book);

    BookDetailsDto toDetails(Book book);
}