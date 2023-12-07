package com.pellto.youtoy.domain.view.repository;

import com.pellto.youtoy.domain.view.entity.Video;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VideoRepository {

  private static final String TABLE = "Video";
  private static final RowMapper<Video> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Video.builder()
      .id(resultSet.getLong("id"))
      .channelId(resultSet.getLong("channelId"))
      .title(resultSet.getString("title"))
      .viewCount(resultSet.getLong("viewCount"))
      .description(resultSet.getString("description"))
      .likeCount(resultSet.getLong("likeCount"))
      .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
      .build();
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public void delete(Long id) {
    var sql = String.format("""
        DELETE FROM %s
        WHERE id = :id
        """, TABLE);
    var params = new MapSqlParameterSource().addValue("id", id);
    namedParameterJdbcTemplate.update(sql, params);
  }

  public boolean existVideo(Long id) {
    return findById(id).isPresent();
  }

  public Optional<Video> findById(Long id) {
    var sql = String.format("""
        SELECT *
        FROM %s
        WHERE id = :id
        """, TABLE);

    var params = new MapSqlParameterSource().addValue("id", id);

    var video = namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    Video nullableVideo = DataAccessUtils.singleResult(video);
    return Optional.ofNullable(nullableVideo);
  }

  private Video insert(Video video) {
    JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName(TABLE)
        .usingGeneratedKeyColumns("id");
    SqlParameterSource params = new BeanPropertySqlParameterSource(video);

    var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

    return Video.builder()
        .id(id)
        .channelId(video.getChannelId())
        .title(video.getTitle())
        .viewCount(video.getViewCount())
        .description(video.getDescription())
        .createdAt(video.getCreatedAt())
        .build();
  }

  public Video save(Video video) {
    if (video.getId() == null) {
      return insert(video);
    }
    return update(video);
  }

  private Video update(Video video) {
    var sql = String.format("""
        UPDATE %s
        SET title = :title,
            viewCount = :viewCount,
            description = :description,
            likeCount = :likeCount
        WHERE id = :id
        """, TABLE);

    SqlParameterSource params = new BeanPropertySqlParameterSource(video);
    namedParameterJdbcTemplate.update(sql, params);
    return video;
  }
}
