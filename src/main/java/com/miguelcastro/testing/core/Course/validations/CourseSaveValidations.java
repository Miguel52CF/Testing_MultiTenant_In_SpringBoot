package com.miguelcastro.testing.core.Course.validations;

import org.springframework.stereotype.Component;

import com.miguelcastro.testing.core.AcademicPeriod.validations.AcademicPeriodValidations;
import com.miguelcastro.testing.core.Course.Course;
import com.miguelcastro.testing.core.School.Validations.SchoolValidations;
import com.miguelcastro.testing.core.User.Validations.UserValidations;

@Component
public class CourseSaveValidations {

  private final UserValidations userValidations;
  private final SchoolValidations schoolValidations;
  private final AcademicPeriodValidations academicPeriodValidations;

  public CourseSaveValidations(UserValidations userValidations, SchoolValidations schoolValidations,
      AcademicPeriodValidations academicPeriodValidations) {
    this.userValidations = userValidations;
    this.schoolValidations = schoolValidations;
    this.academicPeriodValidations = academicPeriodValidations;
  }

  public void validateCourse(Course course) {
    userValidations.validateApiUser(course.getTeacher());
    schoolValidations.validateSchool(course.getSchool());
    academicPeriodValidations.validateAcademicPeriod(course.getAcademicPeriod());
  }

}
