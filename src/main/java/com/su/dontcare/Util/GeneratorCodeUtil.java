package com.su.dontcare.Util;

import com.su.dontcare.Enum.TypeClassEnum;
import com.su.dontcare.service.entity.FieldInfo;
import com.su.dontcare.service.entity.GeneratorCodeInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    public  List<String> getImportClass(List<FieldInfo> fields) {
        List<String> list = new ArrayList<>();
        for (FieldInfo fieldInfo : fields) {
            list.add(getImportClassByJavaType(fieldInfo.getJavaType()));
        }
        return list;
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
