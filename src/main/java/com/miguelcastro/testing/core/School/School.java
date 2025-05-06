package com.miguelcastro.testing.core.School;

import java.util.List;

import com.miguelcastro.testing.base.BaseModel;
import com.miguelcastro.testing.core.AcademicPeriod.AcademicPeriod;
import com.miguelcastro.testing.core.Course.Course;
import com.miguelcastro.testing.core.User.ApiUser;
import com.miguelcastro.testing.enums.DocumentTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schools")
@Getter
@Setter
@NoArgsConstructor
public class School extends BaseModel {

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    @NotEmpty
    private String document;

    @NotNull
    private DocumentTypes documentType;

    private String logo;

    private long phone;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotEmpty
    @NotNull
    private String address;

    private String headquarters;

    private String city;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApiUser> users;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AcademicPeriod> academicPeriods;
}
