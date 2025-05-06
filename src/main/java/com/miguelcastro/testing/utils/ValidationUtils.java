package com.miguelcastro.testing.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ValidationUtils {

  public static List<String> getValidationErrors(BindingResult result) {
    return result.getFieldErrors()
        .stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.toList());
  }

  public static ResponseEntity<Object> createValidationErrorResponse(BindingResult result) {
    List<String> errors = getValidationErrors(result);
    return ResponseEntity.badRequest()
        .body(ApiResponse.error("An unexpected error occurred", "Validation failed", HttpStatus.BAD_REQUEST)
            .withDetails(errors));
  }
}