package com.su.dontcare.Util;

import com.su.dontcare.Enum.DataBaseTypeEnum;
import com.su.dontcare.Enum.MysqlFieldToJavaEnum;
import com.su.dontcare.service.entity.DtogsterInfo;
import com.su.dontcare.service.entity.FieldInfo;
import com.su.dontcare.service.entity.TableInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
*
*@Description: 数据库字段工具类
*@Author: guanzhou.su
*@Date: 2019/8/10
**/
@Component
public class FieldUtil {

    public static String convetTypeToJava(String driverClass, String mysqlType) {
        if (driverClass.equals(DataBaseTypeEnum.MYSQL.getDriverClass())) {
            try {
                return MysqlFieldToJavaEnum.toJavaType(mysqlType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void convertTypeToJavaByFieldList(TableInfo info) {
        String driverClass = info.getDriverClass();
        for (int i = 0; i < info.getFields().size(); i++) {
            FieldInfo field = info.getFields().get(i);
            String javaType = convetTypeToJava(driverClass, field.getType());
            if (field.getPrimaryKey() && javaType.equals("Integer")) {
                javaType = "Long";
            }
            info.getFields().get(i).setJavaType(javaType);
        }
    }

    /**
     *
     *@Description: 字段List 转 含有getter 和setter 方法名的 List<DtogsterInfo>
     *@Param: [list]
     *@Author: guanzhou.su
     *@Date: 2019/8/11
     *@return: java.util.List<com.su.dontcare.service.entity.DtogsterInfo>
     *
     **/
    public List<DtogsterInfo> generatorGsMethod(List<FieldInfo> list) {
        DtogsterInfo geterInfo = null;
        List<DtogsterInfo> dtogsterInfoList = new ArrayList<>();
        for (FieldInfo fieldInfo : list) {
            geterInfo = new DtogsterInfo();
            BeanUtils.copyProperties(fieldInfo, geterInfo);
            geterInfo.setGetterName("get" + StringUtil.firstCharUpper(fieldInfo.getName()));
            geterInfo.setSetterName("set" + StringUtil.firstCharUpper(fieldInfo.getName()));
            dtogsterInfoList.add(geterInfo);
        }
        return dtogsterInfoList;
    }
}
