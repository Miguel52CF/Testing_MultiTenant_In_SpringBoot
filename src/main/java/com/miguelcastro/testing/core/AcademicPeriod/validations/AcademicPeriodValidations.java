package com.miguelcastro.testing.core.AcademicPeriod.validations;

import org.springframework.stereotype.Component;

import com.miguelcastro.testing.config.exceptions.BadRequestException;
import com.miguelcastro.testing.core.AcademicPeriod.AcademicPeriod;
import com.miguelcastro.testing.core.AcademicPeriod.AcademicPeriodRepository;

@Component
public class AcademicPeriodValidations {

  private final AcademicPeriodRepository academicPeriodRepository;

  public AcademicPeriodValidations(AcademicPeriodRepository academicPeriodRepository) {
    this.academicPeriodRepository = academicPeriodRepository;
  }

  public void validateAcademicPeriod(AcademicPeriod academicPeriod) {
    validateAcademicPeriodId(academicPeriod);
    validateAcademicPeriodExistence(academicPeriod.getId());
  }

  private void validateAcademicPeriodId(AcademicPeriod academicPeriod) {
    Long academicPeriodId;

    try {
      academicPeriodId = academicPeriod.getId();
    } catch (Exception e) {
      throw new BadRequestException("Academic period cannot be null.");
    }

    if (academicPeriodId == null || academicPeriodId <= 0) {
      throw new BadRequestException("Academic period ID must be a valid number greater than 0.");
    }
  }

  private void validateAcademicPeriodExistence(Long academicPeriodId) {
    if (!academicPeriodRepository.existsById(academicPeriodId)) {
      throw new BadRequestException("Academic period with the specified ID does not exist.");
    }
  }

}
