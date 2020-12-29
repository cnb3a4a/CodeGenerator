package com.b3a4a.groups.generator.db.type;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public interface TypeFormatter {
    default String format(String columnType) {
        if (isBit(columnType)) {
            return ColumnType.BIT.getType();
        }
        if (isBoolean(columnType)) {
            return ColumnType.BOOLEAN.getType();
        }
        if (isTinyint(columnType)) {
            return ColumnType.TINYINT.getType();
        }
        if (isSmallint(columnType)) {
            return ColumnType.SMALLINT.getType();
        }
        if (isInt(columnType)) {
            return ColumnType.INT.getType();
        }
        if (isLong(columnType)) {
            return ColumnType.BIGINT.getType();
        }
        if (isFloat(columnType)) {
            return ColumnType.FLOAT.getType();
        }
        if (isDouble(columnType)) {
            return ColumnType.DOUBLE.getType();
        }
        if (isDecimal(columnType)) {
            return ColumnType.DECIMAL.getType();
        }
        if (isVarchar(columnType)) {
            return ColumnType.VARCHAR.getType();
        }
        if (isDatetime(columnType)) {
            return ColumnType.DATETIME.getType();
        }
        if (isBlob(columnType)) {
            return ColumnType.BLOB.getType();
        }
        return ColumnType.VARCHAR.getType();
    }

    default boolean contains(List<String> columnTypes, String type) {
        for (String columnType : columnTypes) {
            if (StringUtils.containsIgnoreCase(type, columnType)) {
                return true;
            }
        }
        return false;
    }

    boolean isBit(String columnType);
    boolean isBoolean(String columnType);
    boolean isTinyint(String columnType);
    boolean isSmallint(String columnType);
    boolean isInt(String columnType);
    boolean isLong(String columnType);
    boolean isFloat(String columnType);
    boolean isDouble(String columnType);
    boolean isDecimal(String columnType);
    boolean isVarchar(String columnType);
    boolean isDatetime(String columnType);
    boolean isBlob(String columnType);
}
