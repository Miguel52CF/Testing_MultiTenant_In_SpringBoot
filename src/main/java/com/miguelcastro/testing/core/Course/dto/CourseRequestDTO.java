package com.miguelcastro.testing.core.Course.dto;

import java.time.LocalTime;

import lombok.Getter;

@Getter
public class CourseRequestDTO extends CourseDTO {

  public CourseRequestDTO(
      String name,
      String logo,
      String color,
      String description,
      int weekDay,
      LocalTime startHour,
      LocalTime endHour,
      String room) {
    super(name, logo, color, description, weekDay, startHour, endHour, room);
  }

}
