package com.miguelcastro.testing.core.School;

import java.util.List;

import org.springframework.stereotype.Service;

import com.miguelcastro.testing.base.BaseService;
import com.miguelcastro.testing.core.School.Validations.SchoolSaveValidations;
import com.miguelcastro.testing.core.School.dto.SchoolDTO;
import com.miguelcastro.testing.core.School.dto.SchoolResponseDTO;
import com.miguelcastro.testing.core.School.mapper.SchoolMapper;

@Service
public class SchoolService extends BaseService<School, SchoolDTO> {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper mapper;
    private final SchoolSaveValidations validations;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper mapper, SchoolSaveValidations validations) {
        super(schoolRepository, School.class);
        this.schoolRepository = schoolRepository;
        this.mapper = mapper;
        this.validations = validations;
    }

    @Override
    public List<?> findAllById(Long id) {
        List<SchoolResponseDTO> schools = schoolRepository.findAll()
                .stream()
                .map(mapper::response)
                .toList();
        return schools;
    }

    @Override
    public School save(School school) {
        validations.validateShool(school);
        return super.save(school);
    }
}
