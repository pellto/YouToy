package com.pellto.youtoy.domain.base.application;

import java.util.List;

public interface ReadService<T, DTO> {

  List<DTO> findAll();

  DTO findById(Long id);

  T getById(Long id);

  DTO toDto(T entity);
}
