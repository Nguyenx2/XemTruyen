package com.example.xemtruyen.exceptions;

import com.example.xemtruyen.reponses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiResponse> handleException(
            BaseException exception
    ) {
        return ResponseEntity.status(exception.getCode())
                .body(
                        ApiResponse.builder()
                                .code(exception.getCode())
                                .message(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse> handleValidationExceptions(
            MethodArgumentNotValidException exception
    ) {
        String errorMessage = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .findFirst()
                .orElse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiResponse.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .message(errorMessage)
                                .build()
                );
    }
}
