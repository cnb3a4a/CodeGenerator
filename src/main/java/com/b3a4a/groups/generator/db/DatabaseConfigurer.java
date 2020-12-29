package com.b3a4a.groups.generator.db;

import com.b3a4a.groups.generator.db.info.DatabaseInfo;
import com.b3a4a.groups.generator.global.Result;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfigurer {

    private static Connection getConnection(DatabaseInfo dbInfo) throws ClassNotFoundException, SQLException {
        Class.forName(dbInfo.getDriverClass());
        return DriverManager.getConnection(dbInfo.getJdbcUrl(), dbInfo.getUsername(), dbInfo.getPassword());
    }

    /**
     * 测试数据库能否连接成功
     *
     * @param dbInfo
     * @return
     */
    public static Result testConnection(DatabaseInfo dbInfo) {
        String errorMsg = "";
        try {
            Connection con = DatabaseConfigurer.getConnection(dbInfo);
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return Result.ok();
            }
            errorMsg = dbInfo.getDbName() + "连接失败";
        } catch (ClassNotFoundException e) {
            errorMsg = dbInfo.getDbName() + "连接失败" + "，" + "找不到驱动" + dbInfo.getDriverClass();
        } catch (SQLException e) {
            errorMsg = dbInfo.getDbName() + "连接失败" + "，" + e.getMessage();
        }
        return Result.error(errorMsg);
    }
}
