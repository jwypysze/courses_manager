package com.example.coursesmanagement.controller;

import com.example.coursesmanagement.model.rest.request.SaveRegistrationRequest;
import com.example.coursesmanagement.model.rest.response.RegistrationResponse;
import com.example.coursesmanagement.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.coursesmanagement.controller.ApiConstraints.REGISTRATION;

@RestController
@RequestMapping(REGISTRATION)
@RequiredArgsConstructor
public class RegistrationController {

    public static final String SAVE_REGISTRATION = "/save";
    private final RegistrationService registrationService;

    @PostMapping(SAVE_REGISTRATION)
    public ResponseEntity<RegistrationResponse> saveRegistration(@RequestBody SaveRegistrationRequest registration) {
        return ResponseEntity.ok(RegistrationResponse.from(registrationService.saveRegistration(SaveRegistrationRequest.toDto(registration))));
    }

}
