package com.dsn.Theater.API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TicketsNotAvailableException.class)
    public ResponseEntity<ErrorDto> notAvailableTickets(TicketsNotAvailableException ex) {
        var error = new ErrorDto(ex.getMessage(), LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDto>> validationError(MethodArgumentNotValidException e) {
        var nowTime = LocalDateTime.now();
        var errors = e.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorDto("Поле " + err.getField() + " " + err.getDefaultMessage(), nowTime))
                .toList();

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

}
