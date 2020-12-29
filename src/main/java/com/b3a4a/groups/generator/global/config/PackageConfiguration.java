package com.b3a4a.groups.generator.global.config;

import com.b3a4a.groups.generator.global.PackageInfo;
import com.b3a4a.groups.generator.global.constant.StringConstant;
import com.b3a4a.groups.generator.utils.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class PackageConfiguration {

    public Map<String,String> handlePackageInfo(PackageInfo packageInfo) {
        // 包信息
        HashMap packageMap = CollectionUtils.newHashMapWithExpectedSize(7);
        packageMap.put(StringConstant.CLASS_NAME, packageInfo.getClassName());
        packageMap.put(StringConstant.OBJECT_NAME, packageInfo.getObjName());

        packageMap.put(StringConstant.ENTITY_PACKAGE, getPackage(packageInfo.getBasePackageName(), packageInfo.getEntityPackage()));
        packageMap.put(StringConstant.CONTROLLER_PACKAGE, getPackage(packageInfo.getBasePackageName(), packageInfo.getControllerPackage()));
        packageMap.put(StringConstant.SERVICE_PACKAGE, getPackage(packageInfo.getBasePackageName(), packageInfo.getServicePackage()));
        packageMap.put(StringConstant.SERVICE_PACKAGE_IMPL, getPackage(packageInfo.getBasePackageName(), packageInfo.getServiceImplPackage()));
        packageMap.put(StringConstant.MAPPER_PACKAGE, getPackage(packageInfo.getBasePackageName(), packageInfo.getMapperPackage()));

        return packageMap;

    }

    private String getPackage(String parent, String subPackage) {
        return StringUtils.isBlank(parent) ? subPackage : (parent + StringConstant.DOT + subPackage);
    }
}
