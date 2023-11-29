package com.pellto.youtoy.domain.like.repository;

import com.pellto.youtoy.domain.like.entity.Dislike;
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
public class DislikeRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String TABLE = "Dislike";
    private static final RowMapper<Dislike> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Dislike
            .builder()
            .id(resultSet.getLong("id"))
            .videoId(resultSet.getLong("videoId"))
            .videoType(resultSet.getInt("videoType"))
            .commentId(resultSet.getLong("commentId"))
            .userId(resultSet.getLong("userId"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public Dislike save(Dislike dislike) {
        if (dislike.getId() == null) {
            return insert(dislike);
        }
        throw new UnsupportedOperationException("Disike는 변경이 불가능합니다.");
    }

    private Dislike insert(Dislike dislike) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(dislike);

        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return Dislike
                .builder()
                .id(id)
                .videoId(dislike.getVideoId())
                .videoType(dislike.getVideoType())
                .commentId(dislike.getCommentId())
                .userId(dislike.getUserId())
                .createdAt(dislike.getCreatedAt())
                .build();
    }

    public Optional<Dislike> findById(Long id) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE id = :id
                """, TABLE);

        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        var dislike = namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
        var nullableDislike = DataAccessUtils.singleResult(dislike);
        return Optional.ofNullable(nullableDislike);
    }

    public boolean existById(Long id) {
        var dislike = findById(id);
        return dislike.isPresent();
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
