package com.miguelcastro.testing.utils;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResponse<T> {
  private String message;
  private T data;
  private int status;
  private LocalDateTime timestamp;
  private String error;
  private List<String> details;

  // Factory method para éxito
  public static <T> ApiResponse<T> success(T data, String message) {
    ApiResponse<T> response = new ApiResponse<>();
    response.setMessage(message);
    response.setData(data);
    response.setStatus(HttpStatus.OK.value());
    response.setTimestamp(LocalDateTime.now());
    return response;
  }

  // Factory method para errores HTTP
  public static <T> ApiResponse<T> error(String message, String error, HttpStatus status) {
    ApiResponse<T> response = new ApiResponse<>();
    response.setMessage(message);
    response.setError(error);
    response.setStatus(status.value());
    response.setTimestamp(LocalDateTime.now());
    return response;
  }

  // Método para agregar detalles (ej: validaciones)
  public ApiResponse<T> withDetails(List<String> details) {
    this.setDetails(details);
    return this;
  }
}