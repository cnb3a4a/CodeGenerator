package com.b3a4a.groups.generator.engine;

import com.b3a4a.groups.generator.global.constant.AppConstant;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Properties;

public class VelocityEngineTemplate {

    protected static final Logger logger = LoggerFactory.getLogger(VelocityEngineTemplate.class);

    private static final String VM_SUFFIX = ".vm";
    private VelocityEngine velocityEngine;

    public VelocityEngineTemplate init() {
        if (null == velocityEngine) {
            Properties p = new Properties();
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
            p.setProperty(Velocity.ENCODING_DEFAULT, AppConstant.UTF8);
            p.setProperty(Velocity.INPUT_ENCODING, AppConstant.UTF8);
            p.setProperty("file.resource.loader.unicode", "true");
            velocityEngine = new VelocityEngine(p);
        }
        return this;
    }

    /**
     *
     * @param objectMap     数据
     * @param templatePath  模板路径
     * @param outputFile    输出路径
     * @throws Exception
     */
    public void generator(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
        Template template = velocityEngine.getTemplate(templatePath, AppConstant.UTF8);
        try (FileOutputStream fos = new FileOutputStream(outputFile);
             OutputStreamWriter ow = new OutputStreamWriter(fos, AppConstant.UTF8);
             BufferedWriter writer = new BufferedWriter(ow)) {
             template.merge(new VelocityContext(objectMap), writer);
        }
        logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
    }

    public String templateFilePath(String filePath) {
        if (VM_SUFFIX.endsWith(filePath)) {
            return filePath;
        }
        return filePath + VM_SUFFIX;
    }
}
