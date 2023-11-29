package com.pellto.youtoy.domain.like.repository;

import com.pellto.youtoy.domain.like.entity.Like;
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

@RequiredArgsConstructor
@Repository
public class LikeRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String TABLE = "Likes";
    private static final RowMapper<Like> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Like
            .builder()
            .id(resultSet.getLong("id"))
            .videoId(resultSet.getLong("videoId"))
            .videoType(resultSet.getInt("videoType"))
            .commentId(resultSet.getLong("commentId"))
            .userId(resultSet.getLong("userId"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public Like save(Like like) {
        if (like.getId() == null) {
            return insert(like);
        }
        throw new UnsupportedOperationException("Like는 변경이 불가능합니다.");
    }

    private Like insert(Like like) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(like);

        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return Like
                .builder()
                .id(id)
                .videoId(like.getVideoId())
                .videoType(like.getVideoType())
                .commentId(like.getCommentId())
                .userId(like.getUserId())
                .createdAt(like.getCreatedAt())
                .build();
    }

    public Optional<Like> findById(Long id) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE id = :id
                """, TABLE);

        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        var like = namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
        var nullableLike = DataAccessUtils.singleResult(like);
        return Optional.ofNullable(nullableLike);
    }

    public boolean existById(Long id) {
        var like = findById(id);
        return like.isPresent();
    }

    public void deleteById(Long id) {
        var sql = String.format("""
                DELETE FROM %s
                WHERE id = :id
                """, TABLE);
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
