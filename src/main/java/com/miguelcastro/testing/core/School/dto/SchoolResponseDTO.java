package com.miguelcastro.testing.core.School.dto;

import com.miguelcastro.testing.enums.DocumentTypes;

import lombok.Getter;

@Getter
public class SchoolResponseDTO extends SchoolDTO {
    private Long id;

    public SchoolResponseDTO(Long id, String name, String document, DocumentTypes documentType,
            String logo, long phone, String email, String address, String headquarters, String city) {
        super(name, document, documentType, logo, phone, email, address, headquarters, city);
        this.id = id;
    }
}
