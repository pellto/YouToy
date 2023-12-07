package com.pellto.youtoy.domain.playlist.repository;

import com.pellto.youtoy.domain.playlist.entity.PlaylistVideo;
import com.pellto.youtoy.util.SqlQueryGenerator;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PlaylistVideoRepository {

  private static final RowMapper<PlaylistVideo> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> PlaylistVideo
      .builder()
      .id(resultSet.getLong("id"))
      .playlistId(resultSet.getLong("playlistId"))
      .videoId(resultSet.getLong("videoId"))
      .videoType(resultSet.getInt("videoType"))
      .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
      .build();
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final String TABLE = "Playlist_Video";

  public void delete(Long id) {
    var sql = SqlQueryGenerator.deleteByIdQuery(TABLE);
    SqlParameterSource params = new MapSqlParameterSource("id", id);
    namedParameterJdbcTemplate.update(sql, params);
  }

  public Optional<PlaylistVideo> findById(Long id) {
    var sql = SqlQueryGenerator.findAllQuery(TABLE);
    sql = SqlQueryGenerator.addQueryCondition(sql, "id", id);
    SqlParameterSource params = new MapSqlParameterSource("id", id);
    return SqlQueryGenerator.transformSingleListToSingleObject(
        namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER)
    );
  }

  public List<PlaylistVideo> findByPlaylistId(Long playlistId) {
    var sql = SqlQueryGenerator.findAllQuery(TABLE);
    sql = SqlQueryGenerator.addQueryCondition(sql, "playlistId", playlistId);
    sql = SqlQueryGenerator.addOrderByCondition(sql, "createdAt");
    SqlParameterSource params = new MapSqlParameterSource("playlistId", playlistId);
    return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
  }

  private PlaylistVideo insert(PlaylistVideo playlistVideo) {
    JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName(TABLE)
        .usingGeneratedKeyColumns("id");
    SqlParameterSource params = new BeanPropertySqlParameterSource(playlistVideo);
    var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

    return PlaylistVideo
        .builder()
        .id(id)
        .playlistId(playlistVideo.getPlaylistId())
        .videoId(playlistVideo.getVideoId())
        .videoType(playlistVideo.getVideoType())
        .createdAt(playlistVideo.getCreatedAt())
        .build();
  }

  public PlaylistVideo save(PlaylistVideo playlistVideo) {
    if (playlistVideo.getId() == null) {
      return insert(playlistVideo);
    }
    throw new UnsupportedOperationException("Playlist-Video는 변경을 지원하지 않습니다.");
  }
}
