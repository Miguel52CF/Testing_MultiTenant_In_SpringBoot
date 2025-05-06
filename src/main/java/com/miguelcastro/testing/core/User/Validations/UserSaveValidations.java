package com.miguelcastro.testing.core.User.Validations;

import org.springframework.stereotype.Component;

import com.miguelcastro.testing.core.School.Validations.SchoolValidations;
import com.miguelcastro.testing.core.User.ApiUser;
import com.miguelcastro.testing.core.User.UserRepository;

@Component
public class UserSaveValidations {

  private final UserRepository userRepository;
  private final SchoolValidations schoolValidations;

  public UserSaveValidations(
      UserRepository userRepository,
      SchoolValidations schoolValidations) {
    this.userRepository = userRepository;
    this.schoolValidations = schoolValidations;
  }

  public void validateUser(ApiUser user) {
    validateEmail(user.getEmail());
    schoolValidations.validateSchool(user.getSchool());
  }

  private void validateEmail(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new IllegalArgumentException("Email already exists");
    }
  }

}
