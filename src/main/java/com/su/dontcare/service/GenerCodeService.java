package com.su.dontcare.service;

import com.su.dontcare.Util.FieldUtil;
import com.su.dontcare.Util.GeneratorCodeUtil;
import com.su.dontcare.Util.StringUtil;
import com.su.dontcare.service.entity.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
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
    private static Configuration configuration = null;

    @Autowired
    private FieldUtil fieldUtil;

    @Autowired
    private GeneratorCodeUtil generatorCodeUtil;

    static {
        try {
            configuration = new Configuration(Configuration.VERSION_2_3_23);
            Resource resource = new ClassPathResource(MAPPER_TEMPLATE_PATH);
            File sourceFile = resource.getFile();
            configuration.setDirectoryForTemplateLoading(sourceFile);
        } catch (Exception e) {
            System.out.println("构建 freemarker 模板类 Configuration 异常：");
            e.printStackTrace();
        }
    }
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
        generMapperInterFace(codeInfo);
        generDtoVo(codeInfo);

    }

    public void generMapper(GeneratorCodeInfo codeInfo) {
        MapperXmlVo codeVo = new MapperXmlVo();
        BeanUtils.copyProperties(codeInfo, codeVo);
        Map<String, Object> dataMap = new HashMap<>();

        String tableName = codeInfo.getTableInfo().getTableName();
        codeVo.setInsertDtoParamType(codeInfo.getDtoPath() + "." + StringUtil.firstCharUpper(tableName));
        String mapperName = StringUtil.firstCharUpper(tableName) + "Mapper";
        codeVo.setMapperNameSpace((codeInfo.getMapperPath() + "." + mapperName));
        codeVo.setFieldsNotContainId(fieldUtil.getFieldsExCludPrimary(codeVo.getTableInfo().getFields()));
        dataMap.put("info", codeVo);
        String mapperXmlName = mapperName + ".xml";
        String outputPath = codeInfo.getOutputPath() + "/" + StringUtil.pageFormatToFilePath(codeInfo.getMapperPath()) + "/" +  mapperXmlName;
        outPutTemplateContent(outputPath, "mapper.ftl",dataMap);
    }

    /**
     *
     *@Description: 生成Mapper 接口
     *@Param: [codeInfo]
     *@Author: guanzhou.su
     *@Date: 2019/8/10
     *@return: void
     *
     **/
    public void generMapperInterFace(GeneratorCodeInfo codeInfo) {
        MapperJavaVo codeVo = new MapperJavaVo();
        BeanUtils.copyProperties(codeInfo, codeVo);
        Map<String, Object> dataMap = new HashMap<>();
        String tableName = codeInfo.getTableInfo().getTableName();
        String mapperName = StringUtil.firstCharUpper(tableName) + "Mapper";
        codeVo.setPackName(codeInfo.getMapperPath());
        codeVo.setClassName(mapperName);
        FieldUtil.convertTypeToJavaByFieldList(codeVo.getTableInfo());

        dataMap.put("info", codeVo);

        // 生成的 mapper interface 文件名
        String mapperJavaName = mapperName + ".java";
        // 输出 mapper interface 文件路径
        String outputPath = codeInfo.getOutputPath() + "/" + StringUtil.pageFormatToFilePath(codeInfo.getMapperPath()) + "/" +  mapperJavaName;
        outPutTemplateContent(outputPath, "javaMapper.ftl",dataMap);
    }

    /**
     *
     *@Description: 生成  dto
     *@Param: [codeInfo]
     *@Author: guanzhou.su
     *@Date: 2019/8/10
     *@return: void
     *
     **/
    private void generDtoVo(GeneratorCodeInfo codeInfo) {

        DtoVo dtoVo = new DtoVo();
        BeanUtils.copyProperties(codeInfo, dtoVo);
        Map<String, Object> dataMap = new HashMap<>();
        String tableName = codeInfo.getTableInfo().getTableName();
        String className = StringUtil.firstCharUpper(tableName);
        String classFileName = className + ".java";
        dtoVo.setPackName(codeInfo.getDtoPath());
        dtoVo.setClassName(className);
        dataMap.put("info", dtoVo);
        dataMap.put("gsters", fieldUtil.generatorGsMethod(dtoVo.getTableInfo().getFields()));

        String outputPath = codeInfo.getOutputPath() + "/" + StringUtil.pageFormatToFilePath(codeInfo.getDtoPath()) + "/" + classFileName;
        outPutTemplateContent(outputPath, "dtoVo.ftl",dataMap);
    }

    private void outPutTemplateContent(String outPutPath, String templateName, Map<String, Object> dataMap) {
        Writer out = null;
        try{
            Template template = this.configuration.getTemplate(templateName);
            File docFile = new File(outPutPath);
            if (docFile.getParentFile() != null || !docFile.getParentFile().isDirectory()) {
                //创建文件
                docFile.getParentFile().mkdirs();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            String fileName = docFile.getName();
            System.out.println("文件：" + fileName + "文件生成成功 !生成路径" + outPutPath.substring(0, outPutPath.lastIndexOf("/")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     *@Description: 把数据库类型转JAVA类型
     *@Param: [dataBaseType, fields]
     *@Author: guanzhou.su
     *@Date: 2019/8/10
     *@return: void
     *
     **/
    private void convertJavaType(String dataBaseType, List<FieldInfo> fields) {
        for (FieldInfo field : fields) {

        }
    }
}
