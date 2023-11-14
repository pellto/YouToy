package com.pellto.youtoy.domain.subscribe.repository;

import com.pellto.youtoy.domain.subscribe.entity.Subscribe;
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
public class SubscribeRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String TABLE = "subscribe";
    private static final RowMapper<Subscribe> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Subscribe.
            builder()
            .id(resultSet.getLong("id"))
            .channelId(resultSet.getLong("channelId"))
            .userId(resultSet.getLong("userId"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public Subscribe save(Subscribe subscribe) {
        if (subscribe.getId() == null) {
            return insert(subscribe);
        }
        throw new UnsupportedOperationException("Subscribe는 변경이 불가능합니다.");
    }

    public boolean existSubscribe(Long userId, Long channelId) {
        return findByUserIdAndChannelId(userId, channelId).isPresent();
    }

    private Optional<Subscribe> findByUserIdAndChannelId(Long channelId, Long userId) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE channelId = :channelId AND userId = :userId
                """, TABLE);
        var params = new MapSqlParameterSource()
                .addValue("channelId", channelId)
                .addValue("userId", userId);

        var subscribe = namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
        Subscribe nullableSubscribe = DataAccessUtils.singleResult(subscribe);
        return Optional.ofNullable(nullableSubscribe);
    }

    private Subscribe insert(Subscribe subscribe) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(subscribe);

        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return Subscribe
                .builder()
                .id(id)
                .channelId(subscribe.getChannelId())
                .userId(subscribe.getUserId())
                .createdAt(subscribe.getCreatedAt())
                .build();
    }

    public void delete(Long userId, Long channelId) {
        var subscribe = findByUserIdAndChannelId(userId, channelId).orElseThrow();
        var sql = String.format("""
                DELETE FROM %s
                WHERE id = :id
                """, TABLE);
        var params = new MapSqlParameterSource().addValue("id", subscribe.getId());
        namedParameterJdbcTemplate.update(sql, params);
    }
}
