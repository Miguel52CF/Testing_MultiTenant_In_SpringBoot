package com.miguelcastro.testing.base;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.miguelcastro.testing.config.exceptions.ResourceNotFoundException;

import lombok.Getter;

/**
 * BaseService is a generic abstract service class that provides common CRUD
 * functionality
 * for any entity that extends {@link BaseModel}.
 *
 * <p>
 * The generic type {@code T} is bounded to {@link BaseModel} to ensure that all
 * entities
 * using this service inherit common fields such as {@code id}, {@code active},
 * {@code createdAt},
 * and {@code updatedAt}, as well as lifecycle callbacks like {@code onCreate()}
 * and {@code onUpdate()}.
 * </p>
 *
 * <p>
 * By constraining {@code T} to extend {@code BaseModel}, this service can
 * safely access and
 * manipulate shared fields (e.g., {@code setActive(false)} for soft deletes)
 * without needing
 * to cast or duplicate logic across child services.
 * </p>
 *
 * @param <T>   the entity type, which must extend {@link BaseModel}
 * @param <DTO> the Data Transfer Object (DTO) type used in API operations
 */
@Service
public abstract class BaseService<T extends BaseModel, DTO> {

  private final BaseRepository<T, DTO> baseRepository;
  @Getter
  private final Class<T> modelType;

  public BaseService(BaseRepository<T, DTO> baseRepository, Class<T> modelType) {
    this.baseRepository = baseRepository;
    this.modelType = modelType;
  }

  // Métodos base
  public abstract List<?> findAllById(Long id);

  public DTO findById(Long id) {
    return baseRepository.findByIdEqualsAndActiveIsTrue(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            String.format("%s not found with id: %d", modelType.getSimpleName(), id)));
  }

  public T findOriginalElementsById(Long id) {
    return baseRepository.findByIdAndActiveIsTrue(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            String.format("%s not found with id: %d", modelType.getSimpleName(), id)));
  }

  public T save(T object) {
    return baseRepository.save(object);
  }

  public void saveAll(List<T> objects) {
    baseRepository.saveAll(objects);
  }

  public DTO update(Long id, DTO updatedObject) {
    T existingObject = findOriginalElementsById(id);
    BeanUtils.copyProperties(updatedObject, existingObject, "id", "active");
    save(existingObject);
    return findById(id);
  }

  public String delete(Long id) {
    T object = findOriginalElementsById(id);
    object.setActive(false);
    save(object);
    return String.format("%s with id %d deleted successfully.", modelType.getSimpleName(), id);
  }
}