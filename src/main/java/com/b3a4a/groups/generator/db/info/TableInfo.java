package com.b3a4a.groups.generator.db.info;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
public class TableInfo {
    /** 表名 */
    private String tableName;
    /* 是否存在主键 */
    private boolean hasPK;
    /** 主键名称 */
    private String pkName;
    /** 主键名称首字母大写 */
    private String pkCapitalizeName;
    /** 主键在数据库中名称 */
    private String pkDBName;
    /** 主键对应的java类型 */
    private String pkType;
    /** 当前表对应的所有列的对象 */
    private List<ColumnInfo> columns = new ArrayList<>();
}
