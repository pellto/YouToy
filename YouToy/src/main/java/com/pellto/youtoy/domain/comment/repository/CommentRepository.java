package com.pellto.youtoy.domain.comment.repository;

import com.pellto.youtoy.domain.comment.entity.Comment;
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
        throw new UnsupportedOperationException("TEMP ERROR");
    }

    private Comment insert(Comment comment) {
        System.out.println("comment = " + comment);
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        System.out.println("here 11");
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        System.out.println("here 22");
        SqlParameterSource params = new BeanPropertySqlParameterSource(comment);
        System.out.println("here 33");
        System.out.println("params = " + params);
        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        System.out.println("here 44");
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
}
