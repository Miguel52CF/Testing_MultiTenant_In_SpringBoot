package com.miguelcastro.testing.core.Course.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseDTO {
  private String name;
  private String logo;
  private String color;
  private String description;
  private int weekDay;
  private LocalTime startHour;
  private LocalTime endHour;
  private String room;
}
