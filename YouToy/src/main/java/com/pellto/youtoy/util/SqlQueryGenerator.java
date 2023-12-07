package com.pellto.youtoy.util;

import java.util.List;
import java.util.Optional;
import org.springframework.dao.support.DataAccessUtils;

public class SqlQueryGenerator {

  public static String addOrderByCondition(String sql, String columnName) {
    return sql + String.format(" ORDER BY %s", columnName);
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

  public static String deleteBasicQuery(String tableName) {
    return String.format("""
        DELETE FROM %s
        """, tableName);
  }

  public static String deleteByIdQuery(String tableName) {
    var sql = deleteBasicQuery(tableName);
    return addQueryCondition(sql, "id", "id");
  }

  public static String findAllQuery(String tableName) {
    return String.format("""
        SELECT *
        FROM %s
        """, tableName);
  }

  public static <T> Optional<T> transformSingleListToSingleObject(List<T> obj) {
    return Optional.ofNullable(DataAccessUtils.singleResult(obj));
  }
}
