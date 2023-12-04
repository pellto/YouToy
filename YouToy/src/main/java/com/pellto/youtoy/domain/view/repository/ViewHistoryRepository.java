package com.pellto.youtoy.domain.view.repository;

import com.pellto.youtoy.domain.view.entity.ViewHistory;
import com.pellto.youtoy.util.SqlQueryGenerator;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
public class ViewHistoryRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String TABLE = "ViewHistory";
    private static final RowMapper<ViewHistory> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> ViewHistory
            .builder()
            .id(resultSet.getLong("id"))
            .userId(resultSet.getLong("userId"))
            .videoId(resultSet.getLong("videoId"))
            .videoType(resultSet.getInt("videoType"))
            .lastViewAt(resultSet.getLong("lastViewAt"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public ViewHistory save(ViewHistory viewHistory) {
        if (viewHistory.getId() == null) {
            return insert(viewHistory);
        }
        throw new UnsupportedOperationException(ErrorCode.UNSUPPORTED_UPDATE_VIEW_HISTORY.getMessage());
    }

    private ViewHistory insert(ViewHistory viewHistory) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(viewHistory);

        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return ViewHistory.builder()
                .id(id)
                .userId(viewHistory.getUserId())
                .videoId(viewHistory.getVideoId())
                .videoType(viewHistory.getVideoType())
                .lastViewAt(viewHistory.getLastViewAt())
                .createdAt(viewHistory.getCreatedAt())
                .build();
    }

    public Optional<ViewHistory> findById(Long id) {
        var sql = SqlQueryGenerator.findAllQuery(TABLE);
        sql = SqlQueryGenerator.addQueryCondition(sql, "id", id);
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, params, ROW_MAPPER));
    }

    public boolean existByUserIdVideoIdVideoType(Long userId, Long videoId, Integer videoType) {
        return findByUserIdVideoIdVideoType(userId, videoId, videoType).isPresent();
    }

    public Optional<ViewHistory> findByUserIdVideoIdVideoType(Long userId, Long videoId, Integer videoType) {
        var sql = SqlQueryGenerator.findAllQuery(TABLE);
        sql = SqlQueryGenerator.addQueryCondition(sql, "userId", userId);
        sql = SqlQueryGenerator.addQueryCondition(sql, "videoId", videoId);
        sql = SqlQueryGenerator.addQueryCondition(sql, "videoType", videoType);
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("videoId", videoId)
                .addValue("videoType", videoType);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, params, ROW_MAPPER));
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        } catch (IncorrectResultSizeDataAccessException incorrectResultSizeDataAccessException) {
            throw new IncorrectResultSizeDataAccessException(ErrorCode.INTERNAL_VIEW_HISTORY_DATA_CONFLICT.getMessage(), 1);
        }
    }

    public void deleteByUserIdVideoIdVideoType(Long userId, Long videoId, Integer videoType) {
        var sql = SqlQueryGenerator.deleteBasicQuery(TABLE);
        sql = SqlQueryGenerator.addQueryCondition(sql, "userId", userId);
        sql = SqlQueryGenerator.addQueryCondition(sql, "videoId", videoId);
        sql = SqlQueryGenerator.addQueryCondition(sql, "videoType", videoType);
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("videoId", videoId)
                .addValue("videoType", videoType);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
