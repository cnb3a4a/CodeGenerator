package com.b3a4a.groups.generator.db.mysql;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public interface TableResultSetMapping {
    /* =====================================查询所有表信息======================================= */
    String TABLE_NAME = "Name";
    String ENGINE = "Engine";
    String VERSION = "Version";
    String ROW_FORMAT = "Row_format";
    String TABLE_ROWS = "Rows";
    String AVG_ROW_LENGTH = "Avg_row_length";
    String DATA_LENGTH = "Data_length";
    String MAX_DATA_LENGTH = "Max_data_length";
    String INDEX_LENGTH = "Index_length";
    String DATA_FREE = "Data_free";
    String AUTO_INCREMENT = "Auto_increment";
    String CREATE_TIME = "Create_time";
    String UPDATE_TIME = "Update_time";
    String CHECK_TIME = "Check_time";
    String TABLE_COLLATION = "Collation";
    String CHECKSUM = "Checksum";
    String CREATE_OPTIONS = "Create_options";
    String TABLE_COMMENT = "Comment";

    /* =====================================查询表的所有列======================================= */
    String COLUMN_NAME = "Field";
    String COLUMN_TYPE = "Type";
    String COLLATION_NAME = "Collation";
    String IS_NULLABLE = "Null";
    String COLUMN_KEY = "Key";
    String COLUMN_DEFAULT = "Default";
    String EXTRA = "Extra";
    String PRIVILEGES = "Privileges";
    String COLUMN_COMMENT = "Comment";

    /**
     * jdbcMetaDataTables  => jdbc查询出来的源数据
     * mysqlMetaDataTables => sql语句执行后sql中视图展示的信息
     *
     * jdbc查出来的和sql执行后数据库中显示的不一样,所以做了个Map做了映射关系
     * @SEE MysqlSQLConfig.tableInfo()
     *      获取当前数据库表的信息
     *
     */
    HashMap<String, String> tablesMappingMap = new HashMap<String, String>();
    List<String> jdbcMetaDataTables = Arrays.asList("TABLE_NAME", "ENGINE", "VERSION", "ROW_FORMAT", "TABLE_ROWS", "AVG_ROW_LENGTH", "DATA_LENGTH", "MAX_DATA_LENGTH", "INDEX_LENGTH", "DATA_FREE", "AUTO_INCREMENT", "CREATE_TIME", "UPDATE_TIME", "CHECK_TIME", "TABLE_COLLATION", "CHECKSUM", "CREATE_OPTIONS", "TABLE_COMMENT");
    List<String> mysqlMetaDataTables = Arrays.asList("Name", "Engine", "Version", "Row_format", "Rows", "Avg_row_length", "Data_length", "Max_data_length", "Index_length", "Data_free", "Auto_increment", "Create_time", "Update_time", "Check_time", "Collation", "Checksum", "Create_options", "Comment");

    /**
     * jdbcMetaDataColumns  => jdbc查询出来的源数据
     * mysqlMetaDataColumns => sql语句执行后sql中视图展示的信息(每一列对应的参数信息)
     *
     * jdbc查出来的和sql执行后数据库中显示的不一样,所以做了个Map做了映射关系
     * @SEE MysqlSQLConfig.tableColumnInfo()
     *      获取当前数据库表的信息
     *
     */
    HashMap<String, String> columnsMappingMap = new HashMap<String, String>();
    List<String> jdbcMetaDataColumns = Arrays.asList("COLUMN_NAME","COLUMN_TYPE","COLLATION_NAME","IS_NULLABLE","COLUMN_KEY","COLUMN_DEFAULT","EXTRA","PRIVILEGES","COLUMN_COMMENT");
    List<String> mysqlMetaDataColumns = Arrays.asList("Field","Type","Collation","Null","Key","Default","Extra","Privileges","Comment");




    static HashMap<String, String> TABLE_STATUS_MAPPING() {
        jdbcMetaDataTables.stream().forEach(str -> {
            tablesMappingMap.put(str, mysqlMetaDataTables.get(jdbcMetaDataTables.indexOf(str)));
        });
        return tablesMappingMap;
    }

    static HashMap<String, String> TABLE_COLUMN_MAPPING() {
        jdbcMetaDataColumns.stream().forEach(str -> {
            columnsMappingMap.put(str, mysqlMetaDataColumns.get(jdbcMetaDataColumns.indexOf(str)));
        });
        return columnsMappingMap;
    }

}
