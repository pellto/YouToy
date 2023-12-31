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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String TABLE = "User";

    private static final RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> User
            .builder()
            .id(resultSet.getLong("id"))
            .email(resultSet.getString("email"))
            .pwd(resultSet.getString("pwd"))
            .name(resultSet.getString("name"))
            .birthDate(resultSet.getObject("birthDate", LocalDate.class))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public User save(User user) {
        if (user.getId() == null) {
            return insert(user);
        }
        return update(user);
    }

    private User update(User user) {
        var sql = String.format("""
                UPDATE %s
                SET email = :email, pwd = :pwd, name = :name, birthDate = :birthDate
                WHERE id = :id
                """, TABLE);

        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql, params);
        return user;
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
                .name(user.getName())
                .birthDate(user.getBirthDate())
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

    public Optional<User> findById(Long id) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE id = :id
                """, TABLE);

        var params = new MapSqlParameterSource().addValue("id", id);

        var user = namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
        User nullableUser = DataAccessUtils.singleResult(user);
        return Optional.ofNullable(nullableUser);
    }


}
