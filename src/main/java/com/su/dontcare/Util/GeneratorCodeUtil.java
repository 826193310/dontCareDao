package com.su.dontcare.Util;

import com.su.dontcare.Enum.TypeClassEnum;
import com.su.dontcare.service.entity.FieldInfo;
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
}
