package com.miguelcastro.testing.core.AcademicPeriod.validations;

import org.springframework.stereotype.Component;

import com.miguelcastro.testing.core.AcademicPeriod.AcademicPeriod;
import com.miguelcastro.testing.core.School.Validations.SchoolValidations;

@Component
public class AcademicPeriodSaveValidations {

  private final SchoolValidations schoolValidations;

  public AcademicPeriodSaveValidations(SchoolValidations schoolValidations) {
    this.schoolValidations = schoolValidations;
  }

  public void valideAcademicPeriod(AcademicPeriod academicPeriod) {
    schoolValidations.validateSchool(academicPeriod.getSchool());
  }

}
