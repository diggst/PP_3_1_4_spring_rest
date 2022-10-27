package com.example.pp_3_1_4_spring_rest.services;

import com.example.pp_3_1_4_spring_rest.model.User;
import com.example.pp_3_1_4_spring_rest.repositories.RoleRepository;
import com.example.pp_3_1_4_spring_rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userDAO, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> index() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(User updUser) {
        if (!getUser(updUser.getId()).getPassword().equals(updUser.getPassword())) {
            updUser.setPassword(passwordEncoder.encode(updUser.getPassword()));
        }
        userRepository.save(updUser);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional <User> findByUsername (String username) {
        return userRepository.findByEmail(username);
    }
}