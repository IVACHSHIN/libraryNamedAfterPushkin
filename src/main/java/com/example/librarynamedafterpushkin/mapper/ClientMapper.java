package com.example.librarynamedafterpushkin.mapper;

import com.example.librarynamedafterpushkin.dto.ClientDetailsDto;
import com.example.librarynamedafterpushkin.dto.ClientSmallDto;
import com.example.librarynamedafterpushkin.entity.Client;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {
    ClientSmallDto toSmall(Client client);

    ClientDetailsDto toDetails(Client client);
}
