package com.b3a4a.groups.generator.db.mysql;

import java.util.Map;

public class SQL {
    /** 获取当前数据库表的信息 */
    public String tableInfo() {
        return "SHOW TABLE STATUS";
    }
    /** 获取当前表所有列的信息 */
    public String tableColumnInfo() {
        return "SHOW FULL FIELDS FROM `%s`";
    }
    /** 判断是否自动增长 */
    public boolean isAutoIncrement(Map map) {
        return "auto_increment".equalsIgnoreCase(String.valueOf(map.get(TableResultSetMapping.EXTRA)));
    }
}
