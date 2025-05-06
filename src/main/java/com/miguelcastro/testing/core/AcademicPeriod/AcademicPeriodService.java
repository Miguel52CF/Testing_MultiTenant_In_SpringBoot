package com.miguelcastro.testing.core.AcademicPeriod;

import java.util.List;

import org.springframework.stereotype.Service;

import com.miguelcastro.testing.base.BaseService;
import com.miguelcastro.testing.core.AcademicPeriod.dto.AcademicPeriodDTO;
import com.miguelcastro.testing.core.AcademicPeriod.mapper.AcademicPeriodMapper;
import com.miguelcastro.testing.core.AcademicPeriod.validations.AcademicPeriodSaveValidations;

@Service
public class AcademicPeriodService extends BaseService<AcademicPeriod, AcademicPeriodDTO> {

  private final AcademicPeriodRepository academicPeriodRepository;
  private final AcademicPeriodMapper mapper;
  private final AcademicPeriodSaveValidations validations;

  public AcademicPeriodService(AcademicPeriodRepository academicPeriodRepository, AcademicPeriodMapper mapper,
      AcademicPeriodSaveValidations validations) {
    super(academicPeriodRepository, AcademicPeriod.class);
    this.academicPeriodRepository = academicPeriodRepository;
    this.mapper = mapper;
    this.validations = validations;
  }

  @Override
  public List<?> findAllById(Long id) {
    return academicPeriodRepository.findAll()
        .stream()
        .map(mapper::response)
        .toList();
  }

  @Override
  public AcademicPeriod save(AcademicPeriod academicPeriod) {
    validations.valideAcademicPeriod(academicPeriod);
    return super.save(academicPeriod);
  }

  @Override
  public void saveAll(List<AcademicPeriod> academicPeriods) {
    academicPeriods.forEach(validations::valideAcademicPeriod);
    super.saveAll(academicPeriods);
  }

}
