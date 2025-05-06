package com.miguelcastro.testing.core.User;

import java.time.Instant;
import java.util.List;

import com.miguelcastro.testing.base.BaseModel;
import com.miguelcastro.testing.core.Course.Course;
import com.miguelcastro.testing.core.School.School;
import com.miguelcastro.testing.enums.DocumentTypes;
import com.miguelcastro.testing.enums.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class ApiUser extends BaseModel {

  @NotEmpty
  @NotNull
  private String firstName;

  private String middleName;

  @NotEmpty
  @NotNull
  private String lastName;

  private String secondLastName;

  @NotNull
  private String document;

  @NotNull
  private DocumentTypes documentType;

  @NotEmpty
  @NotNull
  @Column(unique = true)
  private String email;

  @NotEmpty
  @NotNull
  private String password;

  private long phone;

  @NotNull
  private Instant birthdate;

  @NotNull
  private Roles role;

  @ManyToOne
  @JoinColumn(name = "school_id", nullable = false)
  private School school;

  @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Course> courses;

}
