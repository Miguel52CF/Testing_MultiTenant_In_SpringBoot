package com.miguelcastro.testing.core.AcademicPeriod.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AcademicPeriodDTO {
  private String name;
  private Instant starDate;
  private Instant endDate;
}
