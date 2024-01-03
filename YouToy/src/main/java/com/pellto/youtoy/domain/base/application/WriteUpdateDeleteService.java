package com.pellto.youtoy.domain.base.application;

public interface WriteUpdateDeleteService<DTO, WT, MT> {

  DTO write(WT writeRequest);

  DTO modify(MT modifyRequest);

  void deleteById(Long id);
}
