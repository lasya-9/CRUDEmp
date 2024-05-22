package com.example.employeemanagement.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

    private ResponseUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseEntity<Object> response(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        ResponseStatus statusObj = new ResponseStatus();
        statusObj.setMessage(message);
        statusObj.setStatus(status);
        statusObj.setStatusCode(status.value());
        if (status.equals(HttpStatus.CREATED)) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(responseObj).toUri();
            return ResponseEntity.created(location).body(statusObj);
        }
        map.put("status", statusObj);
        if (responseObj != null) {
            map.put("data", responseObj);
        }

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> response(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        ResponseStatus statusObj = new ResponseStatus();
        statusObj.setMessage(message);
        statusObj.setStatus(status);
        statusObj.setStatusCode(status.value());
        map.put("status", statusObj);
        return new ResponseEntity<>(map, status);
    }
}
