package com.pellto.youtoy.domain.playlist.repository;

import static com.pellto.youtoy.util.SqlQueryGenerator.transformSingleListToSingleObject;

import com.pellto.youtoy.domain.playlist.entity.Playlist;
import com.pellto.youtoy.util.SqlQueryGenerator;
import java.sql.ResultSet;
import java.time.LocalDateTime;
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
public class PlaylistRepository {

  private static final RowMapper<Playlist> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Playlist
      .builder()
      .id(resultSet.getLong("id"))
      .channelId(resultSet.getLong("channelId"))
      .title(resultSet.getString("title"))
      .targetRange(resultSet.getInt("targetRange"))
      .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
      .build();
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final String TABLE = "Playlist";

  public boolean existById(Long id) {
    return findById(id).isPresent();
  }

  public Optional<Playlist> findById(Long id) {
    var sql = SqlQueryGenerator.findAllQuery(TABLE);
    sql = SqlQueryGenerator.addQueryCondition(sql, "id", id);
    SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
    return transformSingleListToSingleObject(
        namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER));
  }

  private Playlist insert(Playlist playlist) {
    JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName(TABLE)
        .usingGeneratedKeyColumns("id");
    SqlParameterSource params = new BeanPropertySqlParameterSource(playlist);

    var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

    return Playlist
        .builder()
        .id(id)
        .channelId(playlist.getChannelId())
        .title(playlist.getTitle())
        .targetRange(playlist.getTargetRange())
        .createdAt(playlist.getCreatedAt())
        .build();
  }

  public Playlist save(Playlist playlist) {
    if (playlist.getId() == null) {
      return insert(playlist);
    }
    return update(playlist);
  }

  private Playlist update(Playlist playlist) {
    var sql = String.format("""
        UPDATE %s
        SET targetRange = :targetRange, title = :title
        WHERE id = :id
        """, TABLE);
    SqlParameterSource params = new BeanPropertySqlParameterSource(playlist);
    namedParameterJdbcTemplate.update(sql, params);
    return playlist;
  }
}
