package com.miguelcastro.testing.core.Course;

import java.util.List;

import org.springframework.stereotype.Service;

import com.miguelcastro.testing.base.BaseService;
import com.miguelcastro.testing.core.Course.dto.CourseDTO;
import com.miguelcastro.testing.core.Course.dto.CourseResponseDTO;
import com.miguelcastro.testing.core.Course.mapper.CourseMapper;
import com.miguelcastro.testing.core.Course.validations.CourseSaveValidations;

@Service
public class CourseService extends BaseService<Course, CourseDTO> {

  private final CourseRepository courseRepository;
  private final CourseMapper mapper;
  private final CourseSaveValidations validations;

  public CourseService(CourseRepository courseRepository, CourseMapper mapper, CourseSaveValidations validations) {
    super(courseRepository, Course.class);
    this.courseRepository = courseRepository;
    this.mapper = mapper;
    this.validations = validations;
  }

  @Override
  public List<?> findAllById(Long id) {
    List<CourseResponseDTO> courses = courseRepository.findAll()
        .stream()
        .map(mapper::response)
        .toList();
    return courses;
  }

  @Override
  public Course save(Course course) {
    validations.validateCourse(course);
    return super.save(course);
  }

  @Override
  public void saveAll(List<Course> courses) {
    courses.forEach(validations::validateCourse);
    super.saveAll(courses);
  }

}
