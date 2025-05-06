package com.miguelcastro.testing.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, DTO> extends JpaRepository<T, Long> {

  List<T> findByActiveIsTrue();

  Optional<T> findByIdAndActiveIsTrue(Long id);

  // Métodos que retornan DTOs y consideran si el registro está activo
  List<DTO> findAllByIdGreaterThanAndActiveIsTrue(Long alwaysZeroAsId);

  Optional<DTO> findByIdEqualsAndActiveIsTrue(Long id);
}