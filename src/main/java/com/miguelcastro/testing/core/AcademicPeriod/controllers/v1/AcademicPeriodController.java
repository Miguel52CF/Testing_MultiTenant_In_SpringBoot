package com.miguelcastro.testing.core.AcademicPeriod.controllers.v1;

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

import com.miguelcastro.testing.core.AcademicPeriod.AcademicPeriod;
import com.miguelcastro.testing.core.AcademicPeriod.AcademicPeriodService;
import com.miguelcastro.testing.core.AcademicPeriod.dto.AcademicPeriodDTO;
import com.miguelcastro.testing.core.AcademicPeriod.dto.AcademicPeriodRequestDTO;
import com.miguelcastro.testing.utils.ApiResponse;
import com.miguelcastro.testing.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/academicPeriod")
public class AcademicPeriodController {

  private final AcademicPeriodService academicPeriodService;

  public AcademicPeriodController(AcademicPeriodService academicPeriodService) {
    this.academicPeriodService = academicPeriodService;
  }

  @GetMapping("/all")
  public ResponseEntity<Object> findAll(
      @RequestParam(required = false) Long id) {

    List<?> academicsPeriods = academicPeriodService.findAllById(id);

    if (academicsPeriods.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(ApiResponse.error("", "Academic periods not found", HttpStatus.NOT_FOUND));
    }

    return ResponseEntity.ok(ApiResponse.success(academicsPeriods, "Academic periods"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> findById(@PathVariable Long id) {
    AcademicPeriodDTO academicPeriod = academicPeriodService.findById(id);
    return ResponseEntity.ok(ApiResponse.success(academicPeriod, "Academic period found"));
  }

  @PostMapping("/new")
  public ResponseEntity<Object> save(
      @Valid @RequestBody AcademicPeriod academicPeriod,
      BindingResult result) {

    if (result.hasErrors()) {
      return ValidationUtils.createValidationErrorResponse(result);
    }

    academicPeriod = academicPeriodService.save(academicPeriod);
    AcademicPeriodDTO academicPeriodDTO = academicPeriodService.findById(academicPeriod.getId());

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(academicPeriodDTO, "Academic period created successfully"));
  }

  @PutMapping("/{id}/update")
  public ResponseEntity<Object> update(
      @PathVariable Long id,
      @RequestBody AcademicPeriodRequestDTO academicPeriod) {

    AcademicPeriodDTO updatedUser = academicPeriodService.update(id, academicPeriod);
    return ResponseEntity.ok(ApiResponse.success(updatedUser, "Academic period updated successfully"));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    String message = academicPeriodService.delete(id);
    return ResponseEntity.ok(ApiResponse.success(message, "Delete successful"));
  }

}
