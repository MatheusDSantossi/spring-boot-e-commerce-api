package com.matheusdev.store.controllers;

import com.matheusdev.store.dtos.UserDto;
import com.matheusdev.store.enteties.User;
import com.matheusdev.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    //    method: GET, POST, PUT DELETE
//    We're focusing in GET HERE
//    public Iterable<User> getAllUsers() {
    public Iterable<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> new UserDto(user.getId(), user.getUsername(), user.getEmail())).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
//        return userRepository.findById(id).orElse(null);
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        var userDto = new UserDto(user.getId(), user.getUsername(), user.getEmail());
        return ResponseEntity.ok(userDto);
    }
}
