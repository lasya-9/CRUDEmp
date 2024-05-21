package com.example.employeemanagement.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.util.stream.IntStream;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }


    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> objectNotFound(EntityNotFoundException ex) {
        if (ex instanceof EntityNotFoundException) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
            apiError.setMessage(ex.getMessage());
            return buildResponseEntity(apiError);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException e) {
        if (e instanceof BadRequestException) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
            apiError.setMessage(e.getMessage());
            return buildResponseEntity(apiError);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(value = ServletException.class)
    public ResponseEntity<ApiError> servletException(ServletException ex) {
        if (ex instanceof ServletException) {
            ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
            apiError.setMessage(ex.getMessage());
            return buildResponseEntity(apiError);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiError> accessDeniedException(AccessDeniedException ex) {
        if (ex instanceof AccessDeniedException) {
            ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
            apiError.setMessage(ex.getMessage());
            return buildResponseEntity(apiError);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<ApiError> handleNumberFormatException(NumberFormatException e) {
        if (e instanceof NumberFormatException) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
            apiError.setMessage(e.getMessage());
            return buildResponseEntity(apiError);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    private String camelToSnake(String str) {
        StringBuilder result = new StringBuilder();
        char c = str.charAt(0);
        result.append(Character.toUpperCase(c));
        StringBuilder response = IntStream.range(1, str.length())
                .mapToObj(str::charAt)
                .collect(StringBuilder::new, (sb, ch) -> {
                    if (Character.isUpperCase(ch)) {
                        sb.append(' ').append(ch);
                    } else {
                        sb.append(ch);
                    }
                }, StringBuilder::append);

        return result.append(response).toString();
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(camelToSnake(ex.getName() + " is invalid"));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException e) {
        if (e != null) {
            return buildConstraintViolationExceptionResponse(e);

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private ResponseEntity<ApiError> buildConstraintViolationExceptionResponse(ConstraintViolationException exception) {
        ApiError response = new ApiError(HttpStatus.BAD_REQUEST);
        response.setMessage(exception.getConstraintViolations().stream().toList().get(0).getMessage());
        return buildResponseEntity(response);
    }
}