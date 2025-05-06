package com.miguelcastro.testing.core.Course.mapper;

import org.springframework.stereotype.Component;

import com.miguelcastro.testing.core.Course.Course;
import com.miguelcastro.testing.core.Course.dto.CourseResponseDTO;

@Component
public class CourseMapper {

  public CourseResponseDTO response(Course course) {
    return new CourseResponseDTO(
        course.getId(),
        course.getName(),
        course.getLogo(),
        course.getColor(),
        course.getDescription(),
        course.getWeekDay(),
        course.getStartHour(),
        course.getEndHour(),
        course.getRoom());
  }

}
