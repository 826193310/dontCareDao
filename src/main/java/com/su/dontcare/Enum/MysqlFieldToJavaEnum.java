package com.su.dontcare.Enum;

/**
*
*@Description: mysql字段转Java 字段
*@Author: guanzhou.su
*@Date: 2019/8/10
**/
public enum MysqlFieldToJavaEnum {
    String_VARCHAR("getByPrimaryKey", "根据主键获取数据"),
    FIND_LIST("findList", "列表查询"),
    INSERT("insertAll", "插入"),
    INSERT_DYNAMIC("insertDynamic", "动态插入，只插入存在值的数据");

    private String mysqlType;
    private String javaType;
    MysqlFieldToJavaEnum(String mysqlType, String javaType) {
        this.mysqlType = mysqlType;
        this.javaType = javaType;
    }

    public String getMysqlType() {
        return mysqlType;
    }

    public void setMysqlType(String mysqlType) {
        this.mysqlType = mysqlType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
