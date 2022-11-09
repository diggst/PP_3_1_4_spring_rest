package com.example.pp_3_1_4_spring_rest.controllers;

import com.example.pp_3_1_4_spring_rest.model.User;
import com.example.pp_3_1_4_spring_rest.security.UserDetailsImp;
import com.example.pp_3_1_4_spring_rest.services.RoleService;
import com.example.pp_3_1_4_spring_rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public ApiRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/getUser")
    public User getCurrentSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        return userDetails.getUser();
    }

    @GetMapping()
    public List<User> indexUsers() {
        return userService.index();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable ("id") long id) {
        return userService.getUser(id);
    }

    @PostMapping()
    public User saveUser(@RequestBody User newUser) {
        userService.saveUser(newUser);
        return newUser;
    }

    @PatchMapping("/{id}")
    public User updateUser(@RequestBody User updUser) {
        userService.updateUser(updUser);
        return updUser;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable ("id") long id) {
        userService.deleteUser(id);
    }
}
