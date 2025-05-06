package com.miguelcastro.testing.core.School.dto;

import com.miguelcastro.testing.enums.DocumentTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchoolDTO {
    private String name;
    private String document;
    private DocumentTypes documentType;
    private String logo;
    private long phone;
    private String email;
    private String address;
    private String headquarters;
    private String city;
}
