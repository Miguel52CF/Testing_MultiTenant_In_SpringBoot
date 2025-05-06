package com.miguelcastro.testing.core.Course.controllers.v1;

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

import com.miguelcastro.testing.core.Course.Course;
import com.miguelcastro.testing.core.Course.CourseService;
import com.miguelcastro.testing.core.Course.dto.CourseDTO;
import com.miguelcastro.testing.core.Course.dto.CourseRequestDTO;
import com.miguelcastro.testing.utils.ApiResponse;
import com.miguelcastro.testing.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/course")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping("/all")
  public ResponseEntity<Object> findAll(
      @RequestParam(required = false) Long id) {

    List<?> courses = courseService.findAllById(id);

    if (courses.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(ApiResponse.error("", "Courses not found", HttpStatus.NOT_FOUND));
    }

    return ResponseEntity.ok(ApiResponse.success(courses, "Courses found"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<CourseDTO>> findById(@PathVariable Long id) {
    CourseDTO course = courseService.findById(id);
    return ResponseEntity.ok(ApiResponse.success(course, "Course found"));
  }

  @PostMapping("/new")
  public ResponseEntity<Object> save(
      @Valid @RequestBody Course course,
      BindingResult result) {

    if (result.hasErrors()) {
      return ValidationUtils.createValidationErrorResponse(result);
    }

    course = courseService.save(course);
    CourseDTO CourseDTO = courseService.findById(course.getId());

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(CourseDTO, "Course created successfully"));
  }

  @PutMapping("/{id}/update")
  public ResponseEntity<Object> update(
      @PathVariable Long id,
      @RequestBody CourseRequestDTO course) {

    CourseDTO updatedCourse = courseService.update(id, course);
    return ResponseEntity.ok(ApiResponse.success(updatedCourse, "Course updated successfully"));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
    String message = courseService.delete(id);
    return ResponseEntity.ok(ApiResponse.success(message, "Delete successful"));
  }

}
