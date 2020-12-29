package com.b3a4a.groups.generator.db.info;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ColumnInfo {
    /** 列名 */
    private String columnName;
    /** 驼峰名(首字母小写) */
    private String propertyName;
    /** 首字母大写  */
    private String capitalizeName;
    /** 数据库数据类型 */
    private String dbType;
    /** java数据类型 */
    private String javaType;
    /** 是否为主键 */
    private boolean isPK = false;
    /** 是否自动增长 */
    private boolean isAutoIncrement = false;

}
