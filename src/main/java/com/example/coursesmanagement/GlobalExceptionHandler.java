package com.example.coursesmanagement;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public String entityNotFound(EntityNotFoundException e, Model model) {
        String message = e.getMessage();
        model.addAttribute("message", message);
        return "not-found-for-admin";
    }

    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    public String userEntityWithProvidedNameAndSurnameNotFound(jakarta.persistence.EntityNotFoundException e, Model model) {
        String message = e.getMessage();
        model.addAttribute("message", message);
        return "not-found-for-student";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValid() {
        return "method-argument-not-valid";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String typeAllData() {
        return "type-all-data";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgument() {
        return "type-all-data";
    }

}
