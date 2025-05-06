package com.miguelcastro.testing.core.School.Validations;

import org.springframework.stereotype.Component;

import com.miguelcastro.testing.config.exceptions.BadRequestException;
import com.miguelcastro.testing.core.School.School;
import com.miguelcastro.testing.core.School.SchoolRepository;

@Component
public class SchoolValidations {

  private final SchoolRepository schoolRepository;

  public SchoolValidations(SchoolRepository schoolRepository) {
    this.schoolRepository = schoolRepository;
  }

  public void validateSchool(School school) {
    validateSchoolId(school);
    validateSchoolExistence(school.getId());
  }

  private void validateSchoolId(School school) {
    Long schoolId;

    try {
      schoolId = school.getId();
    } catch (Exception e) {
      throw new BadRequestException("School cannot be null.");
    }

    if (schoolId == null || schoolId <= 0) {
      throw new BadRequestException("School ID must be a valid number greater than 0.");
    }
  }

  private void validateSchoolExistence(Long schoolId) {
    if (!schoolRepository.existsById(schoolId)) {
      throw new BadRequestException("School with the specified ID does not exist.");
    }
  }

}
