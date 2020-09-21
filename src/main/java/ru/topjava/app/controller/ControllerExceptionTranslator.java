package ru.topjava.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ControllerExceptionTranslator {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private static final String ENTITY_NOT_FOUND = "Entity not found";

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = ENTITY_NOT_FOUND)
    @ResponseBody
    public String handleException(EntityNotFoundException exception) {
        log.error("Entity Not Found Exception {}", exception.getMessage());
        return ENTITY_NOT_FOUND;
    }
}
