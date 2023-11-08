package com.pellto.youtoy.domain.channel.repository;

import com.pellto.youtoy.domain.channel.entity.Channel;
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
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChannelRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String TABLE = "Channel";

    static final RowMapper<Channel> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Channel
            .builder()
            .id(resultSet.getLong("id"))
            .ownerId(resultSet.getLong("ownerId"))
            .displayName(resultSet.getString("displayName"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public Channel save(Channel channel) {
        if (channel.getId() == null) {
            return insert(channel);
        }
        // TODO: throw to update Channel
        throw new UnsupportedOperationException("채널은 업데이트를 지원하지 않습니다.");
    }

    public Optional<Channel> findById(Long id) {
        /*
        * SELECT *
        * FROM channel
        * WHERE id = :id
        * */
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE id = :id
                """, TABLE);
        var params = new MapSqlParameterSource().addValue("id", id);
        var nullableChannel = namedParameterJdbcTemplate.queryForObject(sql, params, ROW_MAPPER);
        return Optional.ofNullable(nullableChannel);
    }

    private Channel insert(Channel channel) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(channel);

        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return Channel
                .builder()
                .id(id)
                .displayName(channel.getDisplayName())
                .ownerId(channel.getOwnerId())
                .createdAt(channel.getCreatedAt())
                .build();

    }
}
