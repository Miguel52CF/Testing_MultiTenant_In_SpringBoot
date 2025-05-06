package com.miguelcastro.testing.core.User.mapper;

import org.springframework.stereotype.Component;

import com.miguelcastro.testing.core.User.ApiUser;
import com.miguelcastro.testing.core.User.dto.UserResponseDTO;

@Component
public class UserMapper {

  public UserResponseDTO response(ApiUser user) {
    return new UserResponseDTO(
        user.getId(),
        user.getFirstName(),
        user.getMiddleName(),
        user.getLastName(),
        user.getSecondLastName(),
        user.getDocument(),
        user.getDocumentType(),
        user.getEmail(),
        user.getPhone(),
        user.getBirthdate());
  }

}
