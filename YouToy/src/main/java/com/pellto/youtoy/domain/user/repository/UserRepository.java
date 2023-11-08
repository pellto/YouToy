package com.pellto.youtoy.domain.user.repository;

import com.pellto.youtoy.domain.user.entity.User;
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
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String TABLE = "User";

    static final RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> User
            .builder()
            .id(resultSet.getLong("id"))
            .email(resultSet.getString("email"))
            .pwd(resultSet.getString("pwd"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public User save(User user) {
        if (user.getId() == null) {
            return insert(user);
        }
        throw new UnsupportedOperationException("User는 업데이트를 지원하지 않습니다.");
    }

    private User insert(User user) {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);

        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return User
                .builder()
                .id(id)
                .email(user.getEmail())
                .pwd(user.getPwd())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public boolean existsEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public Optional<User> findByEmail(String email) {
        /*
        * SELECT * FROM User WHERE email = :email
        * */
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE email = :email
                """, TABLE);
        var params = new MapSqlParameterSource().addValue("email", email);

        var user = namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
        User nullableUser = DataAccessUtils.singleResult(user);
        return Optional.ofNullable(nullableUser);
    }
}
