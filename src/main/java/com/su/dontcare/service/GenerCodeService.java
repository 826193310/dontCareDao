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
        generService(codeInfo);
        generController(codeInfo);
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
        String outputPath = codeInfo.getOutputPath() + "/" + StringUtil.pageFormatToFilePath(codeInfo.getMapperXmlPath()) + "/" +  mapperXmlName;
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
        if (codeInfo.getMapperExtendClass() != null && codeInfo.getMapperExtendClass().trim() != "") {
            String extendClass = codeInfo.getMapperExtendClass();
            // 设置Dto 继承的类名
            codeVo.setExtendsClassName(extendClass.substring(extendClass.lastIndexOf(".") + 1, extendClass.length()));
        }
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
        if (codeInfo.getDtoExtendClass() != null) {
            String extendClass = codeInfo.getDtoExtendClass();
            // 设置Dto 继承的类名
            dtoVo.setExtendsClassName(extendClass.substring(extendClass.lastIndexOf(".") + 1, extendClass.length()));
            if (codeInfo.getDtoExtendsClassFields() != null) {
                generatorCodeUtil.deleteSameFieldFromExtend(dtoVo);
            }
        }

        String classFileName = className + ".java";
        dtoVo.setPackName(codeInfo.getDtoPath());
        dtoVo.setClassName(className);
        dataMap.put("info", dtoVo);
        dataMap.put("gsters", fieldUtil.generatorGsMethod(dtoVo.getTableInfo().getFields()));

        String outputPath = codeInfo.getOutputPath() + "/" + StringUtil.pageFormatToFilePath(codeInfo.getDtoPath()) + "/" + classFileName;
        outPutTemplateContent(outputPath, "dtoVo.ftl",dataMap);
    }

    /**
     *
     *@Description: 生成  service 文件
     *@Param: [codeInfo]
     *@Author: guanzhou.su
     *@Date: 2019/8/10
     *@return: void
     *
     **/
    private void generService(GeneratorCodeInfo codeInfo) {
        if (!codeInfo.isGeneratorService()) return;

        ServiceVo serviceVo = new ServiceVo();
        BeanUtils.copyProperties(codeInfo, serviceVo);
        Map<String, Object> dataMap = new HashMap<>();
        String tableName = codeInfo.getTableInfo().getTableName();
        String className = StringUtil.firstCharUpper(tableName) + "Service";
        serviceVo.setMapperClass(StringUtil.firstCharUpper(tableName) + "Mapper");
        serviceVo.setMapperName(tableName + "Mapper");
        String classFileName = className + ".java";
        String respClass = serviceVo.getRespClass();
        String respPageClass = serviceVo.getPageRespClass();
        serviceVo.setRespVo(respClass.substring(respClass.lastIndexOf(".") + 1, respClass.length()));
        if (respPageClass != null) {
            serviceVo.setPageRespVo(respPageClass.substring(respPageClass.lastIndexOf(".") + 1, respPageClass.length()));
        }

        serviceVo.setPackName(codeInfo.getServicePath());
        serviceVo.setClassName(className);
        serviceVo.setListSearchVo(codeInfo.getDtoName());
        serviceVo.setGenericFiledSeter("set" + StringUtil.firstCharUpper(serviceVo.getGenericFiled()));
        List<String> classes = generatorCodeUtil.getServiceImportClass(serviceVo);
        serviceVo.setServiceImportClass(classes);
        if (codeInfo.getDtoExtendClass() != null) {
            String extendClass = codeInfo.getServiceExtendClass();
            // 设置Dto 继承的类名
            serviceVo.setExtendsClassName(extendClass.substring(extendClass.lastIndexOf(".") + 1, extendClass.length()));
        }
        //setgenerServiceInfo(codeInfo, serviceVo);

        dataMap.put("info", serviceVo);

        String outputPath = codeInfo.getOutputPath() + "/" + StringUtil.pageFormatToFilePath(codeInfo.getServicePath()) + "/" + classFileName;
        outPutTemplateContent(outputPath, "service.ftl",dataMap);
    }

    /**
     *
     *@Description: 生成  controller 文件
     *@Param: [codeInfo]
     *@Author: guanzhou.su
     *@Date: 2019/8/10
     *@return: void
     *
     **/
    private void generController(GeneratorCodeInfo codeInfo) {
        if (!codeInfo.isGenerController()) return;

        ControllerVo controllerVo = new ControllerVo();
        BeanUtils.copyProperties(codeInfo, controllerVo);
        Map<String, Object> dataMap = new HashMap<>();
        //String dtoName = codeInfo.getDtoName();
        String tableName = codeInfo.getTableInfo().getTableName();
        String className = StringUtil.firstCharUpper(tableName) + "Controller";
        controllerVo.setServiceClass(StringUtil.firstCharUpper(tableName) + "Service");
        controllerVo.setServiceName(tableName + "Service");
        String classFileName = className + ".java";
        String respClass = controllerVo.getRespClass();
        String respPageClass = controllerVo.getPageRespClass();
        controllerVo.setRespVo(respClass.substring(respClass.lastIndexOf(".") + 1, respClass.length()));
        if (respPageClass != null) {
            controllerVo.setPageRespVo(respPageClass.substring(respPageClass.lastIndexOf(".") + 1, respPageClass.length()));
        }

        controllerVo.setPackName(codeInfo.getControllerPath());
        controllerVo.setClassName(className);
        controllerVo.setParamVo(codeInfo.getDtoName());
        controllerVo.setGenericFiledSeter("set" + StringUtil.firstCharUpper(controllerVo.getGenericFiled()));
        List<String> classes = generatorCodeUtil.getControllerImportClass(controllerVo);
        controllerVo.setControllerImportClass(classes);
        if (codeInfo.getDtoExtendClass() != null) {
            String extendClass = codeInfo.getControllerExtendClass();
            // 设置Dto 继承的类名
            controllerVo.setExtendsClassName(extendClass.substring(extendClass.lastIndexOf(".") + 1, extendClass.length()));
        }
        dataMap.put("info", controllerVo);

        String outputPath = codeInfo.getOutputPath() + "/" + StringUtil.pageFormatToFilePath(codeInfo.getControllerPath()) + "/" + classFileName;
        outPutTemplateContent(outputPath, "controller.ftl",dataMap);
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

    public void setgenerServiceInfo(GeneratorCodeInfo codeInfo, ServiceVo serviceVo) {
        String tableName = codeInfo.getTableInfo().getTableName();
        String className = StringUtil.firstCharUpper(tableName) + "Service";
        serviceVo.setMapperClass(StringUtil.firstCharUpper(tableName) + "Mapper");
        serviceVo.setMapperName(tableName + "Mapper");
        String classFileName = className + ".java";
        String respClass = serviceVo.getRespClass();
        serviceVo.setRespVo(respClass.substring(respClass.lastIndexOf(".") + 1, respClass.length()));
        serviceVo.setPackName(codeInfo.getServicePath());
        serviceVo.setClassName(classFileName);
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
