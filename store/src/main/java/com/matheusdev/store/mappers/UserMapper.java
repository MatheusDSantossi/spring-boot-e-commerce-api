package com.matheusdev.store.mappers;

import com.matheusdev.store.dtos.UserDto;
import com.matheusdev.store.enteties.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
