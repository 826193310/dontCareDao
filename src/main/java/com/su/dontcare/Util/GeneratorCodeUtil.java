package com.su.dontcare.Util;

import com.su.dontcare.Enum.TypeClassEnum;
import com.su.dontcare.service.entity.ControllerVo;
import com.su.dontcare.service.entity.FieldInfo;
import com.su.dontcare.service.entity.GeneratorCodeInfo;
import com.su.dontcare.service.entity.ServiceVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
*
*@Description: 生成代码工具类
*@Author: guanzhou.su
*@Date: 2019/8/11
**/
@Component
public class GeneratorCodeUtil {

    /**
     *
     *@Description: dto 对象 import 的 class 处理
     *@Param: [fields]
     *@Author: guanzhou.su
     *@Date: 2019/8/11
     *@return: java.util.List<java.lang.String>
     *
     **/
    public  List<String> getImportClass(GeneratorCodeInfo codeInfo) {
        List<String> list = new ArrayList<>();
        for (FieldInfo fieldInfo : codeInfo.getTableInfo().getFields()) {
            list.add(getImportClassByJavaType(fieldInfo.getJavaType()));
        }
        if (codeInfo.getDtoExtendClass() != null) {
            String extendClass = codeInfo.getDtoExtendClass();
            list.add(extendClass);
            // 设置Dto 继承的类名
            //codeInfo.setExtendsClassName(extendClass.substring(extendClass.lastIndexOf(".") + 1, extendClass.length()));
        }
        return list;
    }

    public void deleteSameFieldFromExtend(GeneratorCodeInfo codeInfo) {
        List<FieldInfo> fields = codeInfo.getTableInfo().getFields();
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(codeInfo.getDtoExtendsClassFields().replace(" ","").split(",")));
        for (int i = 0; i < fields.size(); i++) {
            if (arrayList.contains(fields.get(i).getName())) {
                fields.remove(i);
                i--;
            }
        }
    }

    public String getImportClassByJavaType(String javaType) {
        try {
           return TypeClassEnum.findImportByType(javaType);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
        return null;
    }

    /**
     *
     *@Description: Mapper 对象 import 的 class 处理
     *@Param: [fields]
     *@Author: guanzhou.su
     *@Date: 2019/8/11
     *@return: java.util.List<java.lang.String>
     *
     **/
    public  List<String> getMapperImportClass(GeneratorCodeInfo codeInfo) {
        List<String> list = new ArrayList<>();
        // 查询列表所需
        list.add("java.util.List");
        // DTO 包所在
        list.add(codeInfo.getDtoPath() + "." + StringUtil.firstCharUpper(codeInfo.getTableInfo().getTableName()));
        list.add(getPrimaryImportClass(codeInfo.getTableInfo().getFields()));
        if(codeInfo.getMapperExtendClass() != null && codeInfo.getMapperExtendClass().trim() != "") {
            list.add(codeInfo.getMapperExtendClass());
        }
        return list;
    }

    /**
     *
     *@Description: Mapper 对象 import 的 class 处理
     *@Param: [fields]
     *@Author: guanzhou.su
     *@Date: 2019/8/11
     *@return: java.util.List<java.lang.String>
     *
     **/
    public  List<String> getServiceImportClass(ServiceVo codeInfo) {
        List<String> list = new ArrayList<>();
        // 查询列表所需
        list.add("java.util.List");
        list.add("org.springframework.transaction.annotation.Transactional");
        list.add("org.springframework.stereotype.Service");
        list.add("org.springframework.beans.factory.annotation.Autowired");
        list.add(codeInfo.getRespClass());
        list.add(codeInfo.getDtoPath() + "." + codeInfo.getDtoName());
        if (codeInfo.isEnablePageHelper()) {
            list.add("com.github.pagehelper.PageHelper");
            list.add("com.github.pagehelper.PageInfo");
            if (codeInfo.isPageRespGeneric()) list.add(codeInfo.getPageRespClass());
        }
        if(codeInfo.getServiceExtendClass() != null && codeInfo.getServiceExtendClass().trim() != "") {
            list.add(codeInfo.getServiceExtendClass());
        }
        return list;
    }

    /**
     *
     *@Description: Mapper 对象 import 的 class 处理
     *@Param: [fields]
     *@Author: guanzhou.su
     *@Date: 2019/8/11
     *@return: java.util.List<java.lang.String>
     *
     **/
    public  List<String> getControllerImportClass(ControllerVo codeInfo) {
        List<String> list = new ArrayList<>();
        // 查询列表所需
        list.add("java.util.List");
        list.add("org.springframework.beans.factory.annotation.Autowired");
        list.add("org.springframework.web.bind.annotation.PathVariable");
        list.add("org.springframework.web.bind.annotation.RequestMapping");
        list.add("org.springframework.web.bind.annotation.RequestMethod");
        list.add("org.springframework.web.bind.annotation.RestController");
        list.add(codeInfo.getServicePath() + "." + StringUtil.firstCharUpper(codeInfo.getTableInfo().getTableName()) + "Service");
        list.add(codeInfo.getRespClass());
        list.add(codeInfo.getDtoPath() + "." + codeInfo.getDtoName());
        if (codeInfo.isEnablePageHelper()) {
            if (codeInfo.isPageRespGeneric()) list.add(codeInfo.getPageRespClass());
        }
        if(codeInfo.getControllerExtendClass() != null && codeInfo.getControllerExtendClass().trim() != "") {
            list.add(codeInfo.getControllerExtendClass());
        }
        return list;
    }

    /**
     *
     *@Description: 获取主键的 class
     *@Param: [fieldInfos]
     *@Author: guanzhou.su
     *@Date: 2019/8/11
     *@return: java.lang.String
     *
     **/
    public String getPrimaryImportClass(List<FieldInfo> fieldInfos) {
        // 主键ID 查询所需
        try {
            for (FieldInfo field : fieldInfos) {
                if (field.getPrimaryKey()) return TypeClassEnum.findImportByType(field.getJavaType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     *@Description: 获取主键的 class
     *@Param: [fieldInfos]
     *@Author: guanzhou.su
     *@Date: 2019/8/11
     *@return: java.lang.String
     *
     **/
    public String getPrimaryType(List<FieldInfo> fieldInfos) {
        // 主键ID 查询所需
        try {
            for (FieldInfo field : fieldInfos) {
                if (field.getPrimaryKey()) return field.getJavaType();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
