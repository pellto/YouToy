package com.pellto.youtoy.domain.channel.repository;

import com.pellto.youtoy.domain.channel.entity.Channel;
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
