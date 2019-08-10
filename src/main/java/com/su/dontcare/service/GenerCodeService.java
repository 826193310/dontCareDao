package com.su.dontcare.service;

import com.su.dontcare.Util.StringUtil;
import com.su.dontcare.service.entity.GeneratorCodeInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
*
*@Description: 生成code service 类
*@Author: guanzhou.su
*@Date: 2019/8/10
 *
**/
@Service
public class GenerCodeService {

    private final static String MAPPER_TEMPLATE_PATH = "static/template";
    /**
     *
     *@Description: 生成代码
     *@Param: [codeInfo]
     *@Author: guanzhou.su
     *@Date: 2019/8/10
     *@return: void
     *
     **/
    public void generCode(GeneratorCodeInfo codeInfo) {
        // 先生成Mapper 文件
        generMapper(codeInfo);
    }

    public void generMapper(GeneratorCodeInfo codeInfo) {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        Writer out = null;
        try{
            Resource resource = new ClassPathResource(MAPPER_TEMPLATE_PATH);
            File sourceFile = resource.getFile();
            configuration.setDirectoryForTemplateLoading(sourceFile);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("tableInfo", codeInfo.getTableInfo());
            String tableName = codeInfo.getTableInfo().getTableName();
            String MapperName = StringUtil.firstCharUpper(tableName) + "Mapper.xml";
            Template template = configuration.getTemplate("mapper.ftl");
            System.out.println(codeInfo.getOutputPath() + "/" + codeInfo.getMapperPath() + "/" +  MapperName);
            File docFile = new File(codeInfo.getOutputPath() + "/" + codeInfo.getMapperPath() + "/" +  MapperName);

            if (docFile.getParentFile() != null || !docFile.getParentFile().isDirectory()) {
                //创建文件
                docFile.getParentFile().mkdirs();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^" + MapperName + "文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
