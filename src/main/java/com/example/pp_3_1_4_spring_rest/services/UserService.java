package com.example.pp_3_1_4_spring_rest.services;

import com.example.pp_3_1_4_spring_rest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> index();

    void saveUser(User newUser);

    void deleteUser(long id);

    void updateUser(User updUser);

    User getUser(long id);

    Optional<User> findByUsername(String username);
}
