package com.byod.userdetail.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));
        log.warn("Validation failed: {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        log.warn("Email Already Exists: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message","Email Already Exists " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        log.warn("User Not Found: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "User Not Found " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserIdNotFoundException(UserIdNotFoundException ex) {
        log.warn("User ID Not Found: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "User ID Not Found " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(SameApiNameExistsException.class)
    public ResponseEntity<Map<String, String>> handleSameNameApiExistException(SameApiNameExistsException ex) {
        log.warn("API with Same Name Exists: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "API with Same Name Exists " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        Map<String, String> error = new HashMap<>();
        error.put("message", "An unexpected error occurred. Please contact support.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(ApiExpiredErrorException.class)
    public ResponseEntity<Map<String, String>> handleApiExpiredErrorException(ApiExpiredErrorException ex) {
        log.warn("API Expired: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "API Experied "+ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }
    @ExceptionHandler(ApiNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleApiNotFoundException(ApiNotFoundException ex) {
        log.warn("API Not Found: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "API Not Found "+ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
}
