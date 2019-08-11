package com.su.dontcare.Enum;

/**
*
*@Description: mysql字段转Java 字段
*@Author: guanzhou.su
*@Date: 2019/8/10
**/
public enum MysqlFieldToJavaEnum {
    VARCHAR("VARCHAR", "String"),
    CHAR("CHAR", "String"),
    BINARY("BINARY", "byte[]"),
    FLOAT("FLOAT", "Float"),
    DATE("DATE", "Date"),
    INTEGER("INTEGER", "Integer"),
    BIT("BIT", "Integer"),
    INT("INT", "Integer");
    private String mysqlType;
    private String javaType;
    MysqlFieldToJavaEnum(String mysqlType, String javaType) {
        this.mysqlType = mysqlType;
        this.javaType = javaType;
    }

    public static String toJavaType(String mysqlType) throws Exception {
        for (MysqlFieldToJavaEnum value : values()) {
            if (mysqlType.equalsIgnoreCase(value.getMysqlType())) return value.javaType;
        }
        throw new Exception("not found your mysqlType [" + mysqlType + "] in MysqlFieldToJavaEnum");
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
