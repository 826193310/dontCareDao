package com.su.dontcare.service.entity;

import java.util.List;

/**
* 
*@Description: 表信息类
*@Param: 
*@Author: guanzhou.su
*@Date: 2019/8/9
*@return: 
 * 
**/
public class TableInfo {
    private String tableName; // 表名
    private String dataBaseName; // 数据库名称
    List<FieldInfo> fields; // 字段信息
    private String driverClass; // 数据库驱动类，用以标识数据库类型转JAVA时不同数据之前的处理
    private String primaryKey; //主键ID

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<FieldInfo> getFields() {
        return fields;
    }

    public void setFields(List<FieldInfo> fields) {
        this.fields = fields;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }
}
