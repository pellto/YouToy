package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.video.entity.Video;
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

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VideoRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String TABLE = "Video";

    static final RowMapper<Video> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Video.builder()
            .id(resultSet.getLong("id"))
            .channelId(resultSet.getLong("channelId"))
            .title(resultSet.getString("title"))
            .viewCount(resultSet.getLong("viewCount"))
            .description(resultSet.getString("description"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

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
                    description = :description
                WHERE id = :id
                """, TABLE);

        SqlParameterSource params = new BeanPropertySqlParameterSource(video);
        namedParameterJdbcTemplate.update(sql, params);
        return video;
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

    public boolean existVideo(Long id) {
        return findById(id).isPresent();
    }

    public void delete(Long id) {
        var sql = String.format("""
                DELETE FROM %s
                WHERE id = :id
                """, TABLE);
        var params = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
