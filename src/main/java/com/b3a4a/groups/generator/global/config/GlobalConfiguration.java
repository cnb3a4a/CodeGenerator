package com.b3a4a.groups.generator.global.config;

import com.b3a4a.groups.generator.db.info.DatabaseInfo;
import com.b3a4a.groups.generator.db.info.TableInfo;
import com.b3a4a.groups.generator.global.PackageInfo;
import com.b3a4a.groups.generator.utils.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GlobalConfiguration {

    public static Map<String,String> handlePackageInfo(PackageInfo packageInfo){
        return new PackageConfiguration().handlePackageInfo(packageInfo);
    }
    private static List<TableInfo> tableInfoMap(DatabaseInfo dbInfo) throws SQLException {
        SQLConfiguration config = new SQLConfiguration();
        config.handleDatabaseInfo(dbInfo);
        return config.getTableInfo();
    }

    public static TableInfo handleTableInfo(DatabaseInfo dbInfo,String tableName) throws SQLException {
        List<TableInfo> list = tableInfoMap(dbInfo);
        for (TableInfo tableInfo : list) {
          if (tableInfo.getTableName().equalsIgnoreCase(tableName) ){
              return tableInfo;
          }
        }
        return null;
    }
}
