package com.example.coursesmanagement.controller;

import com.example.coursesmanagement.model.rest.request.SaveUserRequest;
import com.example.coursesmanagement.model.rest.request.UpdateUserRequest;
import com.example.coursesmanagement.model.rest.response.FindAllUsersResponse;
import com.example.coursesmanagement.model.rest.response.UserResponse;
import com.example.coursesmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.coursesmanagement.controller.ApiConstraints.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    public final static String SAVE_USER = "/save";
    public static final String FIND_ALL_USERS = "/findall";
    public static final String UPDATE_USER = "/update";
    public static final String FIND_USER_BY_ID = "/{id}";
    public static final String DELETE_USER_BY_ID = "/{id}";
    private final UserService userService;

    @GetMapping(FIND_ALL_USERS)
    public ResponseEntity<FindAllUsersResponse> findAllUsers() {
        return ResponseEntity.ok(FindAllUsersResponse.from(userService.findAllUsers()));
    }


    @PostMapping(SAVE_USER)
    public ResponseEntity<UserResponse> saveUser(@RequestBody SaveUserRequest user) {
        return ResponseEntity.ok(UserResponse.from(userService.saveUser(SaveUserRequest.toDto(user))));
    }

    @PutMapping(UPDATE_USER)
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest user) {
        return ResponseEntity.ok(UserResponse.from(userService.updateUser(UpdateUserRequest.toDto(user))));
    }

    @GetMapping(FIND_USER_BY_ID)
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(UserResponse.from(userService.findUserById(id)));
    }

    @DeleteMapping(DELETE_USER_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
