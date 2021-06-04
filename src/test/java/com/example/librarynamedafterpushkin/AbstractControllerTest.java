package com.example.librarynamedafterpushkin;

import com.example.librarynamedafterpushkin.dto.BookDto;
import com.example.librarynamedafterpushkin.dto.ClientDto;
import com.example.librarynamedafterpushkin.repository.BookRepository;
import com.example.librarynamedafterpushkin.repository.ClientRepository;
import com.example.librarynamedafterpushkin.service.TimeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected ClientRepository clientRepository;
    @Autowired
    protected BookRepository bookRepository;

    @MockBean
    protected TimeService timeService;

    @SneakyThrows
    protected ResultActions createClient(ClientDto dto) {
        return mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));
    }

    protected ClientDto mockClient() {
        ClientDto dto = new ClientDto();
        dto.setFirstName("Andrey");
        dto.setLastName("Ivanov");
        dto.setEmail("andreyivanov@gmail.com");
        dto.setPhone("+9136132134");

        return dto;
    }

    @SneakyThrows
    protected ResultActions createBook(BookDto dto) {
        return mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));
    }

    protected BookDto mockBook() {
        BookDto testBook = new BookDto();
        testBook.setName("Test name");
        testBook.setDescription("Test description");
        testBook.setAuthor("Test author");
        testBook.setPublisher("Test publisher");
        testBook.setYear(2020);
        testBook.setIsbn("978-617-7866-64-9");

        return testBook;
    }
}