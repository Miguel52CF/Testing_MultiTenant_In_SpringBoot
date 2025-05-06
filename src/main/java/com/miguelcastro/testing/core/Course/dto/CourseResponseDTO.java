package com.miguelcastro.testing.core.Course.dto;

import java.time.LocalTime;

import lombok.Getter;

@Getter
public class CourseResponseDTO extends CourseDTO {
  private Long id;

  public CourseResponseDTO(
      Long id,
      String name,
      String logo,
      String color,
      String description,
      int weekDay,
      LocalTime startHour,
      LocalTime endHour,
      String room) {
    super(name, logo, color, description, weekDay, startHour, endHour, room);
    this.id = id;
  }
}
