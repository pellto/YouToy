package com.pellto.youtoy.domain.channel.repository;

import com.pellto.youtoy.domain.channel.entity.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
public class ChannelRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String TABLE = "Channel";

    static final RowMapper<Channel> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Channel
            .builder()
            .id(resultSet.getLong("id"))
            .ownerId(resultSet.getLong("ownerId"))
            .handle(resultSet.getString("handle"))
            .displayName(resultSet.getString("displayName"))
            .description(resultSet.getString("description"))
            .banner(resultSet.getString("banner"))
            .profile(resultSet.getString("profile"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public Channel save(Channel channel) {
        if (channel.getId() == null) {
            return insert(channel);
        }
        return update(channel);
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

    public boolean existsHandle(String handle) {
        return findByHandle(handle).isPresent();
    }

    public Optional<Channel> findByHandle(String handle) {
        /*
         * SELECT *
         * FROM channel
         * WHERE handle = :handle
         * */
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE handle = :handle
                """, TABLE);
        var params = new MapSqlParameterSource().addValue("handle", handle);
        try {
            var nullableChannel = namedParameterJdbcTemplate.queryForObject(sql, params, ROW_MAPPER);
            return Optional.ofNullable(nullableChannel);
        } catch (EmptyResultDataAccessException emptyErr) {
            return Optional.empty();
        }
    }

    public Optional<Channel> findByOwnerId(Long ownerId) {
        /*
         * SELECT *
         * FROM channel
         * WHERE ownerId = :ownerId
         * */
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE ownerId = :ownerId
                """, TABLE);
        var params = new MapSqlParameterSource().addValue("ownerId", ownerId);
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

    private Channel update(Channel channel) {
        var sql = String.format("""
                UPDATE %s
                SET handle = :handle,
                    displayName = :displayName,
                    description = :description,
                    banner = :banner,
                    profile = :profile
                WHERE id = :id
                """, TABLE);

        SqlParameterSource params = new BeanPropertySqlParameterSource(channel);
        namedParameterJdbcTemplate.update(sql, params);
        return channel;
    }

    public Optional<Channel> findByChannelIdAndOwnerId(Long id, Long ownerId) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE id = :id AND ownerId = :ownerId
                """, TABLE);

        var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("ownerId", ownerId);
        var nullableChannel = namedParameterJdbcTemplate.queryForObject(sql, params, ROW_MAPPER);
        return Optional.ofNullable(nullableChannel);
    }
}
