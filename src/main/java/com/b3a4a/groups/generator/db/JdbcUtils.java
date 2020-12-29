package com.b3a4a.groups.generator.db;

import com.b3a4a.groups.generator.db.mysql.TableResultSetMapping;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class JdbcUtils {
    private static Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

    public static <T> T queryForObject(Connection connection,String sql,ResultCallback<T> callback) {
        PreparedStatement pstmt = null;
        T result = null;
        try {
            pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<String> columns = new ArrayList<>();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                columns.add(rs.getMetaData().getColumnName(i + 1));
            }
            while(rs.next()){
                Map<String, Object> row = new LinkedHashMap<>();
                for (String column : columns) {
                    String mappingColumnName = TableResultSetMapping.TABLE_COLUMN_MAPPING().get(column);
                    if(!StringUtils.isEmpty(mappingColumnName)) {
                        row.put(mappingColumnName, rs.getObject(mappingColumnName));
                    }else {
                        row.put(column,rs.getObject(column));
                    }
                }
                result = callback.handleResult(row);
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(pstmt!=null){
                try {
                    pstmt.closeOnCompletion();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return result;
    }

    public static <T> List<T> query(Connection connection,String sql,ResultCallback<T> callback) {
        List<T> list = new ArrayList<T>();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<String> columns = new ArrayList<>();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                columns.add(rs.getMetaData().getColumnName(i + 1));
            }
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (String column : columns) {
                    String mappingTableName = TableResultSetMapping.TABLE_STATUS_MAPPING().get(column);
                    if(!StringUtils.isEmpty(mappingTableName)) {
                        row.put(mappingTableName, rs.getObject(mappingTableName));
                    }else {
                        row.put(column,rs.getObject(column));
                    }
                }
                T result = callback.handleResult(row);
                list.add(result);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(pstmt!=null){
                try {
                    pstmt.closeOnCompletion();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return list;
    }

    public interface ResultCallback<T> {
        T handleResult(Map<String, Object> map);
    }
}