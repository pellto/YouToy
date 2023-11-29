package com.pellto.youtoy.util;

import org.springframework.dao.support.DataAccessUtils;

import java.util.List;
import java.util.Optional;

public class SqlQueryGenerator {
    public static String findAllQuery(String tableName) {
        return String.format("""
                SELECT *
                FROM %s
                """, tableName);
    }

    public static String addQueryCondition(String sql, String columnName, Object value) {
        var prefix = " AND";
        var operator = "=";

        if (!sql.toLowerCase().contains("where")) {
            prefix = "WHERE";
        }
        if (value == null) {
            operator = "IS";
        }

        return sql + String.format("%s %s %s :%s", prefix, columnName, operator, columnName);
    }

    public static <T> Optional<T> transformSingleListToSingleObject(List<T> obj) {
        return Optional.ofNullable(DataAccessUtils.singleResult(obj));
    }
}
