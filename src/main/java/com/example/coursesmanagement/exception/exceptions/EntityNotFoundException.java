package com.example.coursesmanagement.exception.exceptions;

import com.example.coursesmanagement.exception.messages.ExceptionMessages;

public class EntityNotFoundException extends RuntimeException {

    public <T> EntityNotFoundException(Class<T> className, Long id) {
        super(String.format(ExceptionMessages.ENTITY_FOR_PROVIDED_ID_NOT_FOUND.getMessage(),className.getSimpleName(),id));
    }
}
