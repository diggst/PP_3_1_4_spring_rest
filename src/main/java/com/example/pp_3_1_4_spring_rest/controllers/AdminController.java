package com.example.pp_3_1_4_spring_rest.controllers;

import com.example.pp_3_1_4_spring_rest.model.User;
import com.example.pp_3_1_4_spring_rest.security.UserDetailsImp;
import com.example.pp_3_1_4_spring_rest.services.RoleService;
import com.example.pp_3_1_4_spring_rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("user", getCurrentUser());
        return "admin";
    }

    @GetMapping("/new")
    public String createForm(User newUser, Model model) {
        model.addAttribute("user", newUser);
        model.addAttribute("currentUser", getCurrentUser());
        model.addAttribute("listRoles", roleService.listRoles());
        return "admin/new";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute("user") @Valid User newUser, BindingResult bindingResult) {
//        userValidator.validate(newUser, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/new";
        }

        userService.saveUser(newUser);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/edit/{id}")
    public String userUpdate(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        return userDetails.getUser();
    }
}