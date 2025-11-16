package com.matheusdev.store.repositories;

import com.matheusdev.store.enteties.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public Iterable<User> findAll();
}
