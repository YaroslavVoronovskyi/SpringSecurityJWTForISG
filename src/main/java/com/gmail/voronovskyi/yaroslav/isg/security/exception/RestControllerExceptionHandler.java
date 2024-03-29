package com.gmail.voronovskyi.yaroslav.isg.security.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getField() + " " + x.getDefaultMessage())
                .toList();
        ApiError apiError = ApiError.builder()
                .error(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .dateTime(LocalDateTime.now())
                .message(errors)
                .build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handlerRequestException(EntityNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .error(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .dateTime(LocalDateTime.now())
                .message(List.of(exception.getMessage()))
                .build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handlerRequestException(EmptyResultDataAccessException exception) {
        ApiError apiError = ApiError.builder()
                .error(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .dateTime(LocalDateTime.now())
                .message(List.of(exception.getMessage()))
                .build();
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
