package com.example.coursesmanagement;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public String entityNotFound(EntityNotFoundException e, Model model) {
        String message = e.getMessage();
        model.addAttribute("message", message);
        return "not-found";
    }

    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    public String userEntityWithProvidedNameAndSurnameNotFound(jakarta.persistence.EntityNotFoundException e, Model model) {
        String message = e.getMessage();
        model.addAttribute("message", message);
        return "not-found";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentException(IllegalArgumentException e, Model model) {
        String message = e.getMessage();
        model.addAttribute("message", message);
        return "illegal-argument";
    }

}
