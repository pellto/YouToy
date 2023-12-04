package com.pellto.youtoy.domain.view.repository;

import com.pellto.youtoy.domain.view.entity.Shorts;
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
public class ShortRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String TABLE = "Short";

    private static final RowMapper<Shorts> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Shorts.builder()
            .id(resultSet.getLong("id"))
            .channelId(resultSet.getLong("channelId"))
            .title(resultSet.getString("title"))
            .viewCount(resultSet.getLong("viewCount"))
            .description(resultSet.getString("description"))
            .likeCount(resultSet.getLong("likeCount"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public Shorts save(Shorts shorts) {
        if (shorts.getId() == null) {
            return insert(shorts);
        }
        return update(shorts);
    }

    private Shorts update(Shorts shorts) {
        var sql = String.format("""
                UPDATE %s
                SET title = :title,
                    viewCount = :viewCount,
                    description = :description,
                    likeCount = :likeCount
                WHERE id = :id
                """, TABLE);

        SqlParameterSource params = new BeanPropertySqlParameterSource(shorts);
        namedParameterJdbcTemplate.update(sql, params);
        return shorts;
    }

    private Shorts insert(Shorts shorts) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(shorts);

        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return Shorts.builder()
                .id(id)
                .channelId(shorts.getChannelId())
                .title(shorts.getTitle())
                .viewCount(shorts.getViewCount())
                .description(shorts.getDescription())
                .createdAt(shorts.getCreatedAt())
                .build();
    }

    public Optional<Shorts> findById(Long id) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE id = :id
                """, TABLE);

        var params = new MapSqlParameterSource().addValue("id", id);

        var shorts = namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
        Shorts nullableShort = DataAccessUtils.singleResult(shorts);
        return Optional.ofNullable(nullableShort);
    }

    public boolean existShort(Long id) {
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
