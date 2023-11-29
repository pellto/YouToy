package com.pellto.youtoy.domain.comment.repository;

import com.pellto.youtoy.domain.comment.dto.CommentDto;
import com.pellto.youtoy.domain.comment.entity.Comment;
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

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String TABLE = "Comment";
    static final RowMapper<Comment> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Comment
            .builder()
            .id(resultSet.getLong("id"))
            .videoId(resultSet.getLong("videoId"))
            .userId(resultSet.getLong("userId"))
            .content(resultSet.getString("content"))
            .repliedCommentId(resultSet.getLong("repliedCommentId"))
            .video(resultSet.getBoolean("video"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            return insert(comment);
        }
        return update(comment);
    }

    private Comment update(Comment comment) {
        var sql = String.format("""
                UPDATE %s
                SET content = :content
                WHERE id = :id
                """, TABLE);

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", comment.getId())
                .addValue("content", comment.getContent());
        namedParameterJdbcTemplate.update(sql, params);
        return comment;

    }

    private Comment insert(Comment comment) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(comment);
        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return Comment
                .builder()
                .id(id)
                .videoId(comment.getVideoId())
                .userId(comment.getUserId())
                .content(comment.getContent())
                .repliedCommentId(comment.getRepliedCommentId())
                .video(comment.isVideo())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public Optional<Comment> findById(Long id) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE id = :id
                """, TABLE);

        var params = new MapSqlParameterSource().addValue("id", id);
        var comment = namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);

        Comment nullableComment = DataAccessUtils.singleResult(comment);
        return Optional.ofNullable(nullableComment);
    }

    public List<Comment> findByVideoIdAndVideo(Long videoId, boolean isVideo) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE videoId = :videoId AND video = :video
                """, TABLE);

        var params = new MapSqlParameterSource()
                .addValue("videoId", videoId)
                .addValue("video", isVideo);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public List<Comment> findByRepliedCommentId(Long repliedCommentId) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE repliedCommentId = :repliedCommentId
                """, TABLE);

        var params = new MapSqlParameterSource()
                .addValue("repliedCommentId", repliedCommentId);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public Long countByRepliedCommentId(Long repliedCommentId) {
        var sql = String.format("""
                SELECT COUNT(*)
                FROM %s
                WHERE repliedCommentId = :repliedCommentId
                """, TABLE);
        var params = new MapSqlParameterSource()
                .addValue("repliedCommentId", repliedCommentId);
        return namedParameterJdbcTemplate.queryForObject(sql, params, Long.class);
    }

    public boolean existById(Long id) {
        var comment = findById(id);
        return comment.isPresent();
    }
}
