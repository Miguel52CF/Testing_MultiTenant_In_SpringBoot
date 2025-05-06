package com.miguelcastro.testing.core.User.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miguelcastro.testing.core.User.ApiUser;
import com.miguelcastro.testing.core.User.UserService;
import com.miguelcastro.testing.core.User.dto.UserDTO;
import com.miguelcastro.testing.core.User.dto.UserRequestDTO;
import com.miguelcastro.testing.utils.ApiResponse;
import com.miguelcastro.testing.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/all")
  public ResponseEntity<Object> findAll(
      @RequestParam(required = false) Long id) {

    List<?> users = userService.findAllById(id);

    if (users.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(ApiResponse.error("", "Users not found", HttpStatus.NOT_FOUND));
    }

    return ResponseEntity.ok(ApiResponse.success(users, "Users found"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserDTO>> findById(@PathVariable Long id) {
    UserDTO user = userService.findById(id);
    return ResponseEntity.ok(ApiResponse.success(user, "User found"));
  }

  @PostMapping("/new")
  public ResponseEntity<Object> save(
      @Valid @RequestBody ApiUser user,
      BindingResult result) {

    if (result.hasErrors()) {
      return ValidationUtils.createValidationErrorResponse(result);
    }

    user = userService.save(user);
    UserDTO userDTO = userService.findById(user.getId());

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(userDTO, "User created successfully"));
  }

  @PutMapping("/{id}/update")
  public ResponseEntity<Object> update(
      @PathVariable Long id,
      @RequestBody UserRequestDTO user) {

    UserDTO updatedUser = userService.update(id, user);
    return ResponseEntity.ok(ApiResponse.success(updatedUser, "User updated successfully"));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
    String message = userService.delete(id);
    return ResponseEntity.ok(ApiResponse.success(message, "Delete successful"));
  }
}