package com.example.coursesmanagement.controller;

import com.example.coursesmanagement.model.dto.UserDto;
import com.example.coursesmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static final String ALL_USERS = "/all";

    private final UserService userService;

    @GetMapping(ALL_USERS)
    public String getAllUsers(Model model) {
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "allUsers";
    }

}
