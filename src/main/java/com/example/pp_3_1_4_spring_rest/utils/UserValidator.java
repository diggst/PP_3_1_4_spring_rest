package com.example.pp_3_1_4_spring_rest.utils;

import com.example.pp_3_1_4_spring_rest.model.User;
import com.example.pp_3_1_4_spring_rest.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserServiceImp userService;

    @Autowired
    public UserValidator(UserServiceImp userService) {
        this.userService = userService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;

        if (userService.findByUsername(user.getEmail()).isEmpty()) {
            return;
        }

        errors.rejectValue("email", "", "Username already exists");
    }
}
