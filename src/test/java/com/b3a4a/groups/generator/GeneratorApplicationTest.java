package com.b3a4a.groups.generator;

import com.b3a4a.groups.generator.db.DatabaseConfigurer;
import com.b3a4a.groups.generator.db.info.DatabaseInfo;
import com.b3a4a.groups.generator.db.info.TableInfo;
import com.b3a4a.groups.generator.engine.VelocityEngineTemplate;
import com.b3a4a.groups.generator.global.PackageInfo;
import com.b3a4a.groups.generator.global.Result;
import com.b3a4a.groups.generator.global.config.GlobalConfiguration;
import com.b3a4a.groups.generator.global.config.SQLConfiguration;
import com.b3a4a.groups.generator.utils.CollectionUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class GeneratorApplicationTest {
    @Autowired
    HikariDataSource ds;
    DatabaseInfo info;

    @Value("${template.output.path}")
    String outputPath;

    @Before
    public void test1() throws SQLException {
        info = new DatabaseInfo();
        info.setDriverClass("com.mysql.cj.jdbc.Driver");
        info.setJdbcUrl("jdbc:mysql:///school?serverTimezone=UTC");
        info.setUsername("root");
        info.setPassword("123456");
        info.setDataSource(ds);
        Result result = DatabaseConfigurer.testConnection(info);
        System.out.println(result);
    }

    @Test
    public void test2() throws SQLException {
        SQLConfiguration configurationBuilder = new SQLConfiguration();
        configurationBuilder.handleDatabaseInfo(info);
        List<TableInfo> tableInfo = configurationBuilder.getTableInfo();
        for (TableInfo info : tableInfo) {
            System.out.println(info);
        }
    }

    @Test
    public void test3() throws SQLException {
        DatabaseInfo info = new DatabaseInfo();
        info.setDriverClass(ds.getDriverClassName());
        info.setJdbcUrl(ds.getJdbcUrl());
        info.setUsername(ds.getUsername());
        info.setPassword(ds.getPassword());
        info.setDataSource(ds);


        SQLConfiguration configurationBuilder = new SQLConfiguration();
        configurationBuilder.handleDatabaseInfo(info);
        List<TableInfo> list = configurationBuilder.getTableInfo();
        PackageInfo pkgInfo = new PackageInfo();
        pkgInfo.setBasePackageName("com.b3a4a.groups");
        pkgInfo.setEntityName(list.get(0).getTableName());
        pkgInfo.setClassName(StringUtils.capitalize(list.get(0).getTableName()));
        pkgInfo.setObjName(StringUtils.uncapitalize(list.get(0).getTableName()));
        Map<String, String> pkgMap = GlobalConfiguration.handlePackageInfo(pkgInfo);
        for (String key : pkgMap.keySet()) {
            System.out.println(key + "===" + pkgMap.get(key));
        }
        System.out.println(list.get(0));
    }

    @Test
    public void test4() throws Exception {
        File file = new File(outputPath);
        if (!file.exists())
            file.mkdirs();
        VelocityEngineTemplate template = new VelocityEngineTemplate();
        template.init();
        /*封装数据库相关信息*/
        DatabaseInfo info = new DatabaseInfo();
        info.setDriverClass(ds.getDriverClassName());
        info.setJdbcUrl(ds.getJdbcUrl());
        info.setUsername(ds.getUsername());
        info.setPassword(ds.getPassword());
        info.setDataSource(ds);

        /*封装表相关信息*/
        TableInfo tableInfo = GlobalConfiguration.handleTableInfo(info, "aaa");


        /*封装包相关信息*/
        PackageInfo pkgInfo = new PackageInfo();
        pkgInfo.setBasePackageName("com.b3a4a.ssmdemo");
        pkgInfo.setEntityName(tableInfo.getTableName());
        pkgInfo.setClassName(StringUtils.capitalize(tableInfo.getTableName()));
        pkgInfo.setObjName(StringUtils.uncapitalize(tableInfo.getTableName()));
        Map<String, String> pkgMap = GlobalConfiguration.handlePackageInfo(pkgInfo);

        HashMap<String, Object> map = CollectionUtils.newHashMap();
        map.put("package", pkgMap);
        map.put("table", tableInfo);

        System.out.println(tableInfo);

        template.generator(map, "templates/Mapper.java.vm", outputPath + pkgInfo.getClassName() + "Mapper.java");
        template.generator(map, "templates/Service.java.vm", outputPath + pkgInfo.getClassName() + "Service.java");
        template.generator(map, "templates/ServiceImpl.java.vm", outputPath + pkgInfo.getClassName() + "ServiceImpl.java");
        template.generator(map, "templates/Controller.java.vm", outputPath + pkgInfo.getClassName() + "Controller.java");
        template.generator(map, "templates/Entity.java.vm", outputPath + pkgInfo.getClassName() + ".java");
        template.generator(map, "templates/Mapper.xml.vm", outputPath + pkgInfo.getClassName() + "Mapper.xml");
    }
}
