package com.pellto.youtoy.domain.channel.application;

import com.pellto.youtoy.domain.channel.domain.Admin;
import com.pellto.youtoy.domain.channel.dto.AdminDto;
import com.pellto.youtoy.domain.channel.exception.NotExistAdminException;
import com.pellto.youtoy.domain.channel.repository.AdminRepository;
import com.pellto.youtoy.global.error.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminReadService {

  private final AdminRepository adminRepository;

  public List<AdminDto> findAll() {
    return adminRepository.findAll().stream().map(this::toDto).toList();
  }

  public AdminDto findById(Long id) {
    var nullableAdmin = adminRepository.findById(id).orElse(null);
    if (nullableAdmin == null) {
      throw new NotExistAdminException(ErrorCode.NOT_EXIST_ADMIN);
    }
    return toDto(nullableAdmin);
  }

  public AdminDto toDto(Admin admin) {
    return new AdminDto(
        admin.getId(),
        admin.getTargetChannel().getId(),
        admin.getAdminUuid().getValue(),
        admin.getAuthLevel()
    );
  }

}
