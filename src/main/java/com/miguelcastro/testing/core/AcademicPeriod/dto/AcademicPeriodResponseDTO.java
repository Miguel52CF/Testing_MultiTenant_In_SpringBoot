package com.miguelcastro.testing.core.AcademicPeriod.dto;

import java.time.Instant;

import lombok.Getter;

@Getter
public class AcademicPeriodResponseDTO extends AcademicPeriodDTO {
  private Long id;

  public AcademicPeriodResponseDTO(
      Long id,
      String name,
      Instant starDate,
      Instant endDate) {
    super(name, starDate, endDate);
    this.id = id;
  }

}
