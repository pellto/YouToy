package com.pellto.youtoy.domain.base.application;

import java.util.List;

public abstract class ReadService<T, DTO> {

  public abstract List<DTO> findAll();

  public abstract DTO findById(Long id);

  public abstract T getById(Long id);

  public abstract DTO toDto(T entity);
}
