package com.example.coursesmanagement.exception.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionMessages {

    ENTITY_FOR_PROVIDED_ID_NOT_FOUND("Encja %s dla podanego id: %s nie istnieje");

    private final String message;
}
