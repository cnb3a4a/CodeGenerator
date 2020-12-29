package com.b3a4a.groups.generator.db.info;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.sql.DataSource;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class DatabaseInfo {
    /** 数据库名称 */
    private String dbName;
    /** 数据库host */
    private String host = "localhost";
    /** 数据库端口 */
    private Integer port = 3306;
    /** 数据库用户名 */
    private String username;
    /** 数据库密码 */
    private String password;
    /** 数据库驱动类 */
    private String driverClass;
    /** 连接地址 */
    private String jdbcUrl;
    /** 数据库连接池 */
    private DataSource dataSource;
}