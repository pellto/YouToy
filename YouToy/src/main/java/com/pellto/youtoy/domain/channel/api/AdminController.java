package com.pellto.youtoy.domain.channel.api;

import com.pellto.youtoy.domain.channel.application.AdminReadService;
import com.pellto.youtoy.domain.channel.application.AdminWriteService;
import com.pellto.youtoy.domain.channel.dto.AdminDto;
import com.pellto.youtoy.domain.channel.dto.InviteAdminRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admins")
@RestController
public class AdminController {

  private final AdminReadService adminReadService;
  private final AdminWriteService adminWriteService;

  @GetMapping
  public List<AdminDto> findAll() {
    return adminReadService.findAll();
  }

  @PostMapping
  public AdminDto invite(@RequestBody @Valid InviteAdminRequest req) {
    return adminWriteService.invite(req);
  }

  @DeleteMapping("/{id}")
  public void emit(@PathVariable Long id) {
    adminWriteService.emit(id);
  }
}
