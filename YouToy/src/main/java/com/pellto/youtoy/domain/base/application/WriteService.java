package com.pellto.youtoy.domain.base.application;

public abstract class WriteService<DTO, WT, MT> {

  public abstract DTO write(WT writeRequest);

  public abstract DTO modify(MT modifyRequest);

  public abstract void deleteById(Long id);
}
