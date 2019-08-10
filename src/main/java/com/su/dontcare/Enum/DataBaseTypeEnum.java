package com.su.dontcare.Enum;

/**
*
*@Description: mysql字段转Java 字段
*@Author: guanzhou.su
*@Date: 2019/8/10
**/
public enum DataBaseTypeEnum {
    MYSQL("com.mysql.jdbc.Driver", "MYSQL", "SELECT * FROM TABLE LIMIT 1");


    private String driverClass;
    private String dataBaseName;
    private String selectSql; // 基础查询语句
    DataBaseTypeEnum(String dataBaseName, String driverClass) {
        this.dataBaseName = dataBaseName;
        this.driverClass = driverClass;
    }

    DataBaseTypeEnum(String dataBaseName, String driverClass, String selectSql) {
        this.dataBaseName = dataBaseName;
        this.driverClass = driverClass;
        this.selectSql = selectSql;
    }

    public static String findSql(String driverClass) {
        for (DataBaseTypeEnum typeEnum : values()) {
            if (typeEnum.getDriverClass().equals(driverClass)) {
                return typeEnum.getSelectSql();
            }

        }
        throw new IllegalArgumentException("no DataBaseTypeEnum found for [" + driverClass + "]");
    }
    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getSelectSql() {
        return selectSql;
    }

    public void setSelectSql(String selectSql) {
        this.selectSql = selectSql;
    }
}
