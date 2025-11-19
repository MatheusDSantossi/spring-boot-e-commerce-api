package com.matheusdev.store.mappers;

import com.matheusdev.store.dtos.UserDto;
import com.matheusdev.store.enteties.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UserMapperManual implements UserMapper{
    @Override
    public UserDto toDto(User user) {
        if (user == null) return null;

        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
