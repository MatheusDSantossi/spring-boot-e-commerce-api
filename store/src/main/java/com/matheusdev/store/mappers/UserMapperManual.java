package com.matheusdev.store.mappers;

import com.matheusdev.store.dtos.RegisterUserRequest;
import com.matheusdev.store.dtos.UpdateUserRequest;
import com.matheusdev.store.dtos.UserDto;
import com.matheusdev.store.enteties.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Primary
public class UserMapperManual implements UserMapper{
    @Override
    public UserDto toDto(User user) {
        if (user == null) return null;

        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
//        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), LocalDateTime.now());
    }

    public User toEntity(RegisterUserRequest request) {
        if (request == null) return null;

        return new User(request.getId(), request.getName(), request.getEmail(), request.getPassword());
    }

    @Override
    public void update(UpdateUserRequest request, User user) {
        if(request != null && user != null) {
            user.setEmail(request.getEmail());
            user.setUsername(request.getName());
        }


    }
}
