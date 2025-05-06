package com.miguelcastro.testing.core.AcademicPeriod.mapper;

import org.springframework.stereotype.Component;

import com.miguelcastro.testing.core.AcademicPeriod.AcademicPeriod;
import com.miguelcastro.testing.core.AcademicPeriod.dto.AcademicPeriodResponseDTO;

@Component
public class AcademicPeriodMapper {

  public AcademicPeriodResponseDTO response(AcademicPeriod academicPeriod) {
    return new AcademicPeriodResponseDTO(
        academicPeriod.getId(),
        academicPeriod.getName(),
        academicPeriod.getStarDate(),
        academicPeriod.getEndDate());
  }

}
