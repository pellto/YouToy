package com.pellto.youtoy.domain.channel.repository;

import com.pellto.youtoy.domain.channel.entity.ChannelAdmin;
import lombok.RequiredArgsConstructor;
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
public class ChannelAdminRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String TABLE = "ChannelAdmin";
    static final RowMapper<ChannelAdmin> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> ChannelAdmin
            .builder()
            .id(resultSet.getLong("id"))
            .channelId(resultSet.getLong("channelId"))
            .userId(resultSet.getLong("userId"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public ChannelAdmin save(ChannelAdmin channelAdmin) {
        if (channelAdmin.getId() == null) {
            return insert(channelAdmin);
        }
        throw new UnsupportedOperationException("ChannelAdmin은 변경을 지원하지 않습니다.");
    }

    private ChannelAdmin insert(ChannelAdmin channelAdmin) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(channelAdmin);

        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return ChannelAdmin
                .builder()
                .id(id)
                .channelId(channelAdmin.getChannelId())
                .userId(channelAdmin.getUserId())
                .createdAt(channelAdmin.getCreatedAt())
                .build();
    }

    public void delete(Long channelId, Long userId) {
        var sql = String.format("""
                DELETE FROM %s
                WHERE channelId = :channelId AND userId = :userId
                """, TABLE);
        var params = new MapSqlParameterSource()
                .addValue("channelId", channelId)
                .addValue("userId", userId);
        namedParameterJdbcTemplate.update(sql, params);
    }

    public Optional<ChannelAdmin> findByChannelIdAndUserId(Long channelId, Long userId) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE channelId = :channelId AND userId = :userId
                """, TABLE);
        var params = new MapSqlParameterSource()
                .addValue("channelId", channelId)
                .addValue("userId", userId);
        var nullableChannelAdmin = namedParameterJdbcTemplate.queryForObject(sql, params, ROW_MAPPER);
        return Optional.ofNullable(nullableChannelAdmin);
    }
}
