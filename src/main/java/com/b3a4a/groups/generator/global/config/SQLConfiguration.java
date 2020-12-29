package com.b3a4a.groups.generator.global.config;


import com.b3a4a.groups.generator.db.*;
import com.b3a4a.groups.generator.db.JdbcUtils.ResultCallback;
import com.b3a4a.groups.generator.db.info.ColumnInfo;
import com.b3a4a.groups.generator.db.info.DatabaseInfo;
import com.b3a4a.groups.generator.db.info.TableInfo;
import com.b3a4a.groups.generator.db.mysql.SQL;
import com.b3a4a.groups.generator.db.mysql.TableResultSetMapping;
import com.b3a4a.groups.generator.db.type.MySQLTypeFormatter;
import com.b3a4a.groups.generator.db.type.ColumnTypeConverter;
import com.b3a4a.groups.generator.utils.NameUtils;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SQLConfiguration {
    private Connection connection;
    private SQL sentence;
    private DataSource dataSource;

    public void handleDatabaseInfo(DatabaseInfo databaseInfo) throws SQLException {
        dataSource = databaseInfo.getDataSource();
        connection = dataSource.getConnection();
        sentence = new SQL();
    }

    /**
     * 获取所有表的信息
     */
    public List<TableInfo> getTableInfo() {
        //获取所有表的信息
        String sql = sentence.tableInfo();
        List<TableInfo> tableList = JdbcUtils.query(connection, sql, map -> {
            TableInfo info = new TableInfo();
            //获取表名
            String tableName = (String) map.get(TableResultSetMapping.TABLE_NAME);
            if (StringUtils.isEmpty(tableName)) {
                System.err.println("没有获取到数据库的表信息,请确定数据库是否正确");
            }
            //指定每个TableInfo的表名
            info.setTableName(tableName);
            return info;
        });
        tableList.forEach(t -> {
            completeTableColumnInfo(t);
        });
        return tableList;
    }

    private TableInfo completeTableColumnInfo(TableInfo tableInfo) {
        String sql = String.format(sentence.tableColumnInfo(), tableInfo.getTableName());
        JdbcUtils.queryForObject(connection, sql, map -> {
            String key = (String) map.get(TableResultSetMapping.COLUMN_KEY);
            ColumnInfo colInfo = new ColumnInfo();
            String columnName = (String) map.get(TableResultSetMapping.COLUMN_NAME);
            colInfo.setColumnName(StringUtils.lowerCase(columnName));

            String propertyName = NameUtils.lineToHump(columnName);
            colInfo.setCapitalizeName(StringUtils.capitalize(propertyName));
            colInfo.setPropertyName(StringUtils.uncapitalize(propertyName));

            String dbType = (String) map.get(TableResultSetMapping.COLUMN_TYPE);
            colInfo.setDbType(dbType);
            String javaType = ColumnTypeConverter.getJavaType(dbType);
            colInfo.setJavaType(javaType);

            boolean isKey = StringUtils.isNotBlank(key) && "PRI".equalsIgnoreCase(key);
            if (isKey) {
                tableInfo.setPkDBName(columnName);
                tableInfo.setPkName(StringUtils.uncapitalize(propertyName));
                tableInfo.setPkCapitalizeName(StringUtils.capitalize(propertyName));
                tableInfo.setPkType(MySQLTypeFormatter.getInstance().format(javaType));
                tableInfo.setHasPK(true);
                colInfo.setPK(true);
                colInfo.setAutoIncrement(sentence.isAutoIncrement(map));
            }
            tableInfo.getColumns().add(colInfo);
            return tableInfo;
        });
        return tableInfo;
    }
}
