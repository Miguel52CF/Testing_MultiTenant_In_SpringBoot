package com.miguelcastro.testing.core.School.dto;

import com.miguelcastro.testing.enums.DocumentTypes;

import lombok.Getter;

@Getter
public class SchoolRequestDTO extends SchoolDTO {
    public SchoolRequestDTO(String name, String document, DocumentTypes documentType, String logo, long phone,
            String email, String address, String headquarters, String city) {
        super(name, document, documentType, logo, phone, email, address, headquarters, city);
    }
}
