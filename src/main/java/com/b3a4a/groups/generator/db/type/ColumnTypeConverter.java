package com.b3a4a.groups.generator.db.type;

import com.b3a4a.groups.generator.utils.CollectionUtils;

import java.util.Map;

public class ColumnTypeConverter {
    private static Map<String, String> TYPE_MAP = CollectionUtils.newHashMap();
    static{
        TYPE_MAP.put(ColumnType.BIT.getType(), "boolean");
        TYPE_MAP.put(ColumnType.BOOLEAN.getType(), "boolean");
        TYPE_MAP.put(ColumnType.TINYINT.getType(), "byte");
        TYPE_MAP.put(ColumnType.SMALLINT.getType(), "short");
        TYPE_MAP.put(ColumnType.INT.getType(), "int");
        TYPE_MAP.put(ColumnType.BIGINT.getType(), "long");
        TYPE_MAP.put(ColumnType.FLOAT.getType(), "float");
        TYPE_MAP.put(ColumnType.DOUBLE.getType(), "double");
        TYPE_MAP.put(ColumnType.DECIMAL.getType(), "BigDecimal");
        TYPE_MAP.put(ColumnType.VARCHAR.getType(), "String");
        TYPE_MAP.put(ColumnType.DATETIME.getType(), "Date");
        TYPE_MAP.put(ColumnType.BLOB.getType(), "byte[]");
    }

    /**
     * 传入数据库类型来获取对应的java类型
     * @param dbType  数据库类型
     * @return
     */
    public static String getJavaType(String dbType) {
        return TYPE_MAP.getOrDefault(MySQLTypeFormatter.getInstance().format(dbType), "String");
    }
}
