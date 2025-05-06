package com.miguelcastro.testing.core.Course.validations;

import org.springframework.stereotype.Component;

import com.miguelcastro.testing.config.exceptions.BadRequestException;
import com.miguelcastro.testing.core.Course.Course;
import com.miguelcastro.testing.core.Course.CourseRepository;

@Component
public class CourseValidations {

  private final CourseRepository courseRepository;

  public CourseValidations(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public void validateCourse(Course course) {
    validateCourseId(course);
    validateCourseExistence(course.getId());
  }

  private void validateCourseId(Course course) {
    Long courseId;

    try {
      courseId = course.getId();
    } catch (Exception e) {
      throw new BadRequestException("Course cannot be null.");
    }

    if (courseId == null || courseId <= 0) {
      throw new BadRequestException("Course ID must be a valid number greater than 0.");
    }
  }

  private void validateCourseExistence(Long courseId) {
    if (!courseRepository.existsById(courseId)) {
      throw new BadRequestException("Course with the specified ID does not exist.");
    }
  }

}
