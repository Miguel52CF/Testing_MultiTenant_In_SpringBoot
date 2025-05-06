package com.miguelcastro.testing.core.School.mapper;

import org.springframework.stereotype.Component;

import com.miguelcastro.testing.core.School.School;
import com.miguelcastro.testing.core.School.dto.SchoolResponseDTO;

@Component
public class SchoolMapper {
    public SchoolResponseDTO response(School school) {
        return new SchoolResponseDTO(
                school.getId(),
                school.getName(),
                school.getDocument(),
                school.getDocumentType(),
                school.getLogo(),
                school.getPhone(),
                school.getEmail(),
                school.getAddress(),
                school.getHeadquarters(),
                school.getCity()
        );
    }
}
