package com.miguelcastro.testing.core.AcademicPeriod;

import java.time.Instant;
import java.util.List;

import com.miguelcastro.testing.base.BaseModel;
import com.miguelcastro.testing.core.Course.Course;
import com.miguelcastro.testing.core.School.School;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "academic_courses")
@Getter
@Setter
@NoArgsConstructor
public class AcademicPeriod extends BaseModel {

  @NotEmpty
  @NotNull
  private String name;

  @ManyToOne
  @JoinColumn(name = "school_id", nullable = false)
  private School school;

  @NotNull
  private Instant starDate;

  @NotNull
  private Instant endDate;

  @OneToMany(mappedBy = "academicPeriod", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Course> courses;

}
