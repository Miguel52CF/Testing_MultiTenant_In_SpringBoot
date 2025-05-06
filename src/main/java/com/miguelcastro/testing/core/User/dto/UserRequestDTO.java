package com.miguelcastro.testing.core.User.dto;

import java.time.Instant;

import com.miguelcastro.testing.enums.DocumentTypes;

import lombok.Getter;

@Getter
public class UserRequestDTO extends UserDTO {

  private String password;

  public UserRequestDTO(String firstName, String middleName, String lastName, String secondLastName,
      String document, DocumentTypes documentType, String email, long phone, Instant birthdate,
      String password) {
    super(firstName, middleName, lastName, secondLastName, document, documentType, email, phone, birthdate);
    this.password = password;
  }
}