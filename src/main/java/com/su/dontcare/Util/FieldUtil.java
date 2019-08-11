package com.su.dontcare.Util;

import com.su.dontcare.Enum.DataBaseTypeEnum;
import com.su.dontcare.Enum.MysqlFieldToJavaEnum;
import com.su.dontcare.service.entity.FieldInfo;
import com.su.dontcare.service.entity.TableInfo;

/**
*
*@Description: 数据库字段工具类
*@Author: guanzhou.su
*@Date: 2019/8/10
**/
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
}
