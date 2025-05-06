package com.miguelcastro.testing.core.User.Validations;

import org.springframework.stereotype.Component;

import com.miguelcastro.testing.config.exceptions.BadRequestException;
import com.miguelcastro.testing.core.User.ApiUser;
import com.miguelcastro.testing.core.User.UserRepository;

@Component
public class UserValidations {

  private final UserRepository userRepository;
  
  public UserValidations(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void validateApiUser(ApiUser apiUser) {
    validateApiUserId(apiUser);
    validateApiUserExistence(apiUser.getId());
  }

  private void validateApiUserId(ApiUser apiUser) {
    Long apiUserId;

    try {
      apiUserId = apiUser.getId();
    } catch (Exception e) {
      throw new BadRequestException("User cannot be null.");
    }

    if (apiUserId == null || apiUserId <= 0) {
      throw new BadRequestException("User ID must be a valid number greater than 0.");
    }
  }

  private void validateApiUserExistence(Long apiUserId) {
    if (!userRepository.existsById(apiUserId)) {
      throw new BadRequestException("User with the specified ID does not exist.");
    }
  }

}
