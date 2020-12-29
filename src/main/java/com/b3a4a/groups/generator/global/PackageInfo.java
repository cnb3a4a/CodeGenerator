package com.b3a4a.groups.generator.global;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class PackageInfo {
    /**
     * 转化后的类名(首字母大写)
     */
    private String className;
    /**
     * 类名首字母小写
     */
    private String objName;
    /**
     * 包路径
     */
    private String basePackageName;

    /**
     * 实体名称
     */
    private String entityName;
    /**
     * 实体名称
     */
    private String entityPackage = "entity";
    /**
     * web包名
     */
    private String controllerPackage = "web";
    /**
     * service包名
     */
    private String servicePackage = "service";
    /**
     * Service实现类包名
     */
    private String serviceImplPackage = "service.impl";
    /**
     * Mapper包名
     */
    private String mapperPackage = "mapper";

}
