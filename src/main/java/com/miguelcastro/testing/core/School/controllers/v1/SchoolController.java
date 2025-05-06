package com.miguelcastro.testing.core.School.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguelcastro.testing.core.School.School;
import com.miguelcastro.testing.core.School.SchoolService;
import com.miguelcastro.testing.core.School.dto.SchoolDTO;
import com.miguelcastro.testing.utils.ApiResponse;
import com.miguelcastro.testing.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/school")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        SchoolDTO school = schoolService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(school, "School found"));
    }

    @PostMapping("/new")
    public ResponseEntity<Object> save(@Valid @RequestBody School school, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationUtils.createValidationErrorResponse(result);
        }

        school = schoolService.save(school);
        SchoolDTO schoolDTO = schoolService.findById(school.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(schoolDTO, "School created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable Long id,
            @RequestBody SchoolDTO school) {

        SchoolDTO updateSchoolDTO = schoolService.update(id, school);
        return ResponseEntity.ok(ApiResponse.success(updateSchoolDTO, "School updated successfully"));
    }
}
