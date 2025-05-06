package com.miguelcastro.testing.core.User;

import java.util.List;

import org.springframework.stereotype.Service;

import com.miguelcastro.testing.base.BaseService;
import com.miguelcastro.testing.core.User.Validations.UserSaveValidations;
import com.miguelcastro.testing.core.User.dto.UserDTO;
import com.miguelcastro.testing.core.User.dto.UserResponseDTO;
import com.miguelcastro.testing.core.User.mapper.UserMapper;

@Service
public class UserService extends BaseService<ApiUser, UserDTO> {

  private final UserRepository userRepository;
  private final UserMapper mapper;
  private final UserSaveValidations validations;

  public UserService(UserRepository userRepository, UserMapper mapper, UserSaveValidations validations) {
    super(userRepository, ApiUser.class);
    this.userRepository = userRepository;
    this.mapper = mapper;
    this.validations = validations;
  }

  @Override
  public List<?> findAllById(Long id) {
    List<UserResponseDTO> users = userRepository.findAll()
        .stream()
        .map(mapper::response)
        .toList();
    return users;
  }

  @Override
  public ApiUser save(ApiUser user) {
    validations.validateUser(user);
    return super.save(user);
  }

  @Override
  public void saveAll(List<ApiUser> users) {
    users.forEach(validations::validateUser);
    super.saveAll(users);
  }

}
