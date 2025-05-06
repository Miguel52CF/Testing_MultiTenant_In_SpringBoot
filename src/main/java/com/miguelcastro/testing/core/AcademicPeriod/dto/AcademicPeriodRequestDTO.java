package com.miguelcastro.testing.core.AcademicPeriod.dto;

import java.time.Instant;

import lombok.Getter;

@Getter
public class AcademicPeriodRequestDTO extends AcademicPeriodDTO {

  public AcademicPeriodRequestDTO(
      String name,
      Instant starDate,
      Instant endDate) {
    super(
        name,
        starDate,
        endDate);
  }

}
