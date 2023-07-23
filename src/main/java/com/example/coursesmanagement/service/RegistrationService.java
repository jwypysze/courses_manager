package com.example.coursesmanagement.service;

import com.example.coursesmanagement.repository.RegistrationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationJpaRepository registrationJpaRepository;
}
