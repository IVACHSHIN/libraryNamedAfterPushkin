package com.example.librarynamedafterpushkin.mapper;

import com.example.librarynamedafterpushkin.dto.ExpiredInUseDto;
import com.example.librarynamedafterpushkin.entity.BookInUse;
import com.example.librarynamedafterpushkin.service.TimeService;
import lombok.Getter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.temporal.ChronoUnit;

@Mapper
@Getter
public abstract class BookInUseMapper {

    @Autowired
    private TimeService timeService;

    @Value("${time.expired.after}")
    private Integer expiredAfter;

    @Mapping(target = "expiredDays", ignore = true)
    public abstract ExpiredInUseDto toDto(BookInUse bookInUse);

    @AfterMapping
    void map(@MappingTarget ExpiredInUseDto dto, BookInUse bookInUse) {
        var now = timeService.currentDate();

        var expiredDays = now.isAfter(dto.getInUseFrom().plusDays(expiredAfter)) ?
                ChronoUnit.DAYS.between(bookInUse.getInUseFrom().plusDays(expiredAfter), now) :
                0;

        dto.setExpiredDays(expiredDays);
    }
}