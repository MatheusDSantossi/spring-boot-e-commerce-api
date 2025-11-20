package com.matheusdev.store.controllers;

import com.matheusdev.store.dtos.UserDto;
import com.matheusdev.store.mappers.UserMapper;
import com.matheusdev.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    //    method: GET, POST, PUT DELETE
//    We're focusing in GET HERE
//    public Iterable<User> getAllUsers() {
    public Iterable<UserDto> getAllUsers(@RequestParam(required = false, defaultValue = "", name = "sort") String sort) {

        if (!Set.of("username", "email").contains(sort))
            sort = "username";

        return userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
//        return userRepository.findAll().stream().map(user -> new UserDto(user.getId(), user.getUsername(), user.getEmail())).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
//        return userRepository.findById(id).orElse(null);
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
//        var userDto = new UserDto(user.getId(), user.getUsername(), user.getEmail());
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
