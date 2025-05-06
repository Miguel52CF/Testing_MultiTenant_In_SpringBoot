package com.miguelcastro.testing.core.Course;

import java.time.LocalTime;

import com.miguelcastro.testing.base.BaseModel;
import com.miguelcastro.testing.core.AcademicPeriod.AcademicPeriod;
import com.miguelcastro.testing.core.School.School;
import com.miguelcastro.testing.core.User.ApiUser;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course extends BaseModel {

  @NotEmpty
  @NotNull
  private String name;

  @ManyToOne
  @JoinColumn(name = "teacher_id", nullable = false)
  private ApiUser teacher;

  @ManyToOne
  @JoinColumn(name = "school_id", nullable = false)
  private School school;

  private String logo;

  private String color;

  private String description;

  @ManyToOne
  @JoinColumn(name = "academic_period", nullable = false)
  private AcademicPeriod academicPeriod;

  @Min(1)
  @Max(7)
  private int weekDay;

  @NotNull
  private LocalTime startHour;

  @NotNull
  private LocalTime endHour;

  @NotNull
  @NotEmpty
  private String room;

}
