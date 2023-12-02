package com.pellto.youtoy.domain.comment.repository;

import com.pellto.youtoy.domain.comment.entity.Mention;
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
public class MentionRepository {
    private static final String TABLE = "Mention";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final RowMapper<Mention> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Mention
            .builder()
            .id(resultSet.getLong("id"))
            .commentId(resultSet.getLong("commentId"))
            .mentionedChannelId(resultSet.getLong("mentionedChannelId"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public Mention save(Mention mention) {
        if (mention.getId() == null) {
            return insert(mention);
        }
        throw new UnsupportedOperationException("변경을 지원하지 않습니다.");
    }

    private Mention insert(Mention mention) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(mention);

        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return Mention.builder()
                .id(id)
                .commentId(mention.getCommentId())
                .mentionedChannelId(mention.getMentionedChannelId())
                .createdAt(mention.getCreatedAt())
                .build();
    }
}
