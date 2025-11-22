package com.matheusdev.store.mappers;

import com.matheusdev.store.dtos.RegisterUserRequest;
import com.matheusdev.store.dtos.UserDto;
import com.matheusdev.store.enteties.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(target = "phoneNumber", ignore = true)
//    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
}
