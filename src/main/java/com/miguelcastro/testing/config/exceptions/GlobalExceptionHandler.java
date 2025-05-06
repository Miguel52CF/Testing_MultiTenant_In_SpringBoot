package com.miguelcastro.testing.config.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.miguelcastro.testing.utils.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(ResourceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ApiResponse.error(
            "Resource not found",
            ex.getMessage(),
            HttpStatus.NOT_FOUND));
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ApiResponse<Void>> handleBadRequestException(BadRequestException ex) {
    return ResponseEntity.badRequest()
        .body(ApiResponse.error(
            "An unexpected error occurred",
            ex.getMessage(),
            HttpStatus.BAD_REQUEST));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ApiResponse.error(
            "An unexpected error occurred", // Mensaje genérico
            ex.getMessage(), // Detalle técnico en 'error'
            HttpStatus.INTERNAL_SERVER_ERROR));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {

    String customMessage = "The provided value is not valid. Please check the allowed values.";
    Throwable cause = ex.getCause();

    if (cause instanceof InvalidFormatException) {
      InvalidFormatException invalidFormatEx = (InvalidFormatException) cause;
      String fieldName = invalidFormatEx.getPath().get(0).getFieldName();
      Class<?> targetType = invalidFormatEx.getTargetType();

      if (targetType.isEnum()) {
        Object[] enumValues = targetType.getEnumConstants();
        String acceptedValues = String.join(", ", java.util.Arrays.stream(enumValues)
            .map(Object::toString)
            .toArray(String[]::new));

        customMessage = String.format(
            "Invalid value for field '%s'. Valid values are: %s.",
            fieldName, acceptedValues);
      }
    }

    return ResponseEntity.badRequest()
        .body(ApiResponse.error(
            "An unexpected error occurred",
            customMessage,
            HttpStatus.BAD_REQUEST));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex) {

    String customMessage = "Data integrity error. Please check the provided data.";
    String exceptionMessage = ex.getMostSpecificCause().getMessage();

    if (exceptionMessage != null && exceptionMessage.contains("violates foreign key constraint")) {
      String referencedTable = exceptionMessage.substring(
          exceptionMessage.indexOf("\"") + 1,
          exceptionMessage.indexOf("\"", exceptionMessage.indexOf("\"") + 1));
      String foreignKey = exceptionMessage.substring(
          exceptionMessage.lastIndexOf("\"") + 1,
          exceptionMessage.lastIndexOf("\"", exceptionMessage.lastIndexOf("\"") - 1));
      customMessage = String.format(
          "The value for '%s' does not exist in table '%s'.",
          foreignKey, referencedTable);
    }

    return ResponseEntity.badRequest()
        .body(ApiResponse.error(
            "An unexpected error occurred",
            customMessage,
            HttpStatus.BAD_REQUEST));
  }
}