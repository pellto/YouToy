package com.pellto.youtoy.post.application.adapter.out.persistence;

import com.pellto.youtoy.global.interfaces.PersistenceAdapter;
import com.pellto.youtoy.post.domain.model.Post;
import com.pellto.youtoy.post.domain.port.out.LoadPostPort;
import com.pellto.youtoy.post.domain.port.out.SavePostPort;
import lombok.RequiredArgsConstructor;


@PersistenceAdapter
@RequiredArgsConstructor
public class PostPersistenceAdapter implements LoadPostPort, SavePostPort {

  private final PostMapper postMapper;
  private final PostJpaDataRepository jpaDataRepository;

  @Override
  public Post load(Long id) {
    var entity = jpaDataRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("변경 예정")
    );
    return postMapper.toDomain(entity);
  }

  @Override
  public boolean isExistById(Long id) {
    return jpaDataRepository.existsById(id);
  }

  @Override
  public void deleteById(Long postId) {
    jpaDataRepository.deleteById(postId);
  }

  @Override
  public Post save(Post post) {
    var entity = postMapper.toEntity(post);
    return postMapper.toDomain(jpaDataRepository.save(entity));
  }

  @Override
  public void update(Post post) {
    var entity = postMapper.toEntity(post);
    jpaDataRepository.save(entity);
  }
}
