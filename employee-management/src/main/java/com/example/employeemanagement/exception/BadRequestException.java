package com.example.employeemanagement.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class BadRequestException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

}