package com.pellto.youtoy.domain.base.application;

public interface WriteService<DTO, WT, MT> {

  DTO write(WT writeRequest);

  DTO modify(MT modifyRequest);

  void deleteById(Long id);
}
