package com.miguelcastro.testing.core.User;

import com.miguelcastro.testing.base.BaseRepository;
import com.miguelcastro.testing.core.User.dto.UserDTO;

public interface UserRepository extends BaseRepository<ApiUser, UserDTO> {

  boolean existsByEmail(String email);
  
}
