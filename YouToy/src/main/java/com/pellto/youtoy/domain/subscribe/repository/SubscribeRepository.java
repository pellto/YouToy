package com.pellto.youtoy.domain.subscribe.repository;

import com.pellto.youtoy.domain.subscribe.entity.Subscribe;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;

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
}
