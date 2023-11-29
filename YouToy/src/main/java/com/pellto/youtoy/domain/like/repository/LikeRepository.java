package com.pellto.youtoy.domain.like.repository;

import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;
import com.pellto.youtoy.domain.like.entity.Like;
import com.pellto.youtoy.util.SqlQueryGenerator;
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
import java.util.List;
import java.util.Optional;

import static com.pellto.youtoy.util.SqlQueryGenerator.addQueryCondition;

@RequiredArgsConstructor
@Repository
public class LikeRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String TABLE = "Likes";
    private static final RowMapper<Like> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Like
            .builder()
            .id(resultSet.getLong("id"))
            .videoId(resultSet.getObject("videoId", Long.class))
            .videoType(resultSet.getObject("videoType", Integer.class))
            .commentId(resultSet.getObject("commentId", Long.class))
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
        var sql = SqlQueryGenerator.findAllQuery(TABLE);
        sql = addQueryCondition(sql, "id", id);

        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return transformSingleListToSingleLike(
                namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER)
        );
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

    public Optional<Like> findByCreateCmd(CreateLikeCommand cmd) {
        String sql = SqlQueryGenerator.findAllQuery(TABLE);
        sql = addQueryCondition(sql, "videoId", cmd.videoId());
        sql = addQueryCondition(sql, "videoType", cmd.videoType());
        sql = addQueryCondition(sql, "commentId", cmd.commentId());
        sql = addQueryCondition(sql, "userId", cmd.userId());

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("videoId", cmd.videoId())
                .addValue("videoType", cmd.videoType())
                .addValue("commentId", cmd.commentId())
                .addValue("userId", cmd.userId());

        return transformSingleListToSingleLike(namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER));
    }

    private Optional<Like> transformSingleListToSingleLike(List<Like> likes) {
        return Optional.ofNullable(DataAccessUtils.singleResult(likes));
    }
}
