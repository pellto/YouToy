package com.pellto.youtoy.domain.channel.api;

import com.pellto.youtoy.domain.channel.application.ChannelReadService;
import com.pellto.youtoy.domain.channel.application.ChannelWriteService;
import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.dto.CreateChannelRequest;
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
@RequestMapping("/channels")
@RestController
public class ChannelController {

  private final ChannelReadService channelReadService;
  private final ChannelWriteService channelWriteService;

  @GetMapping
  public List<ChannelDto> findAll() {
    return channelReadService.findAll();
  }

  @GetMapping("/{id}")
  public ChannelDto findById(@PathVariable Long id) {
    return channelReadService.findById(id);
  }

  @PostMapping
  public ChannelDto createChannel(@RequestBody @Valid CreateChannelRequest req) {
    return channelWriteService.create(req);
  }

  @DeleteMapping("/{id}")
  public void deleteChannel(@PathVariable Long id) {
    channelWriteService.delete(id);
  }

}
