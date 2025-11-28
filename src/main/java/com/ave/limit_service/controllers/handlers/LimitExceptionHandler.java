package com.ave.limit_service.controllers.handlers;

import com.ave.limit_service.exception.LimitExceededException;
import com.ave.limit_service.response.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LimitExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LimitExceededException.class)
    public ErrorDto limitExceeded(LimitExceededException exceeded) {
        return new ErrorDto("Limit exceeded");
    }
}
