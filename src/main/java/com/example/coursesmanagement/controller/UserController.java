package com.example.coursesmanagement.controller;

import com.example.coursesmanagement.model.rest.request.SaveUserRequest;
import com.example.coursesmanagement.model.rest.response.UserResponse;
import com.example.coursesmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.coursesmanagement.controller.ApiConstraints.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    public final static String SAVE_USER = "/save";

    private final UserService userService;

    @PostMapping(SAVE_USER)
    public ResponseEntity<UserResponse> saveUser(@RequestBody SaveUserRequest user) {
        return ResponseEntity.ok(UserResponse.from(userService.saveUser(SaveUserRequest.toDto(user))));
    }

}
