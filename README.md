# 目录

- 快速入门
  - [安装](https://github.com/cnb3a4a/CodeGenerator/blob/main/README.md#安装)
  - [用法](https://github.com/cnb3a4a/CodeGenerator/blob/main/README.md#用法)

- [维护者](https://github.com/cnb3a4a/CodeGenerator/blob/main/README.md#维护者)



## 快速入门



### 安装

```
初版

	直接在test包下去找测试文件
	
GeneratorApplicationTest.java
	test4方法
	
需要手动指定
		包名
		表名
```

目录说明:

```
src
├─main
│  ├─java
│  │  └─com
│  │      └─b3a4a
│  │          └─groups
│  │              └─generator
│  │                  ├─db				
│  │                  ├─engine			模板引擎
│  │                  ├─global
│  │
│  └─resources
│      │
│      └─templates						模板文件
│              Controller.java.vm
│              Entity.java.vm
│              Mapper.java.vm
│              Mapper.xml.vm
│              Service.java.vm
│              ServiceImpl.java.vm
```

### 用法

```java
@SpringBootTest
public class GeneratorApplicationTest {
	@Autowired
    HikariDataSource ds;
    DatabaseInfo info;

    @Value("${template.output.path}")
    String outputPath;
    
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
        TableInfo tableInfo = GlobalConfiguration.handleTableInfo(info, "表名");


        /*封装包相关信息*/
        PackageInfo pkgInfo = new PackageInfo();
        pkgInfo.setBasePackageName("包名");
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
```

## 维护者

- [大栋](http://www.b3a4a.com)
- 博客后面续费后重做
- 代码只是demo,后期会慢慢的功能优化,先把基本功能实现,后续会把逻辑整合在页面中方便操作