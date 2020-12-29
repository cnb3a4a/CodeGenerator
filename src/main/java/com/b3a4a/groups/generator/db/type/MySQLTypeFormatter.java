package com.b3a4a.groups.generator.db.type;


import java.util.Arrays;
import java.util.Collections;

public class MySQLTypeFormatter implements TypeFormatter {
    private MySQLTypeFormatter(){}
    private static TypeFormatter formatter = null;
    public static TypeFormatter getInstance(){
        if (formatter == null){
            return new MySQLTypeFormatter();
        }
        return formatter;
    }

    @Override
    public boolean isBit(String columnType) {
        return contains(Collections.singletonList("bit"), columnType);
    }

    @Override
    public boolean isBoolean(String columnType) {
        return contains(Collections.singletonList("boolean"), columnType);
    }

    @Override
    public boolean isTinyint(String columnType) {
        return contains(Collections.singletonList("tinyint"), columnType);
    }

    @Override
    public boolean isSmallint(String columnType) {
        return contains(Collections.singletonList("smallint"), columnType);
    }

    @Override
    public boolean isInt(String columnType) {
        return !isLong(columnType) && contains(Arrays.asList("int", "integer"), columnType);
    }

    @Override
    public boolean isLong(String columnType) {
        return !isVarchar(columnType) && contains(Collections.singletonList("bigint"), columnType);
    }

    @Override
    public boolean isFloat(String columnType) {
        return contains(Collections.singletonList("float"), columnType);
    }

    @Override
    public boolean isDouble(String columnType) {
        return contains(Collections.singletonList("double"), columnType);
    }

    @Override
    public boolean isDecimal(String columnType) {
        return contains(Collections.singletonList("decimal"), columnType);
    }

    @Override
    public boolean isVarchar(String columnType) {
        return contains(Arrays.asList("CHAR", "VARCHAR", "TEXT"), columnType);
    }

    @Override
    public boolean isDatetime(String columnType) {
        return contains(Arrays.asList("DATE", "TIME", "DATETIME", "TIMESTAMP"), columnType);
    }

    @Override
    public boolean isBlob(String columnType) {
        return contains(Collections.singletonList("blob"), columnType);
    }
}
