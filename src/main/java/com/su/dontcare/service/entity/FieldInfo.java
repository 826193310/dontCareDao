package com.su.dontcare.service.entity;

/**
*
*@Description: 字段信息类
*@Param:
*@Author: guanzhou.su
*@Date: 2019/8/9
*@return:
 *
**/
public class FieldInfo {
    private String name; // 字段名称
    private String type; // 字段类型
    private boolean isNull; // 是否允许为空
    private boolean primaryKey; // 是否为ID
    private String commons; // 字段备注
    private String javaType; // java 字段类型

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    public boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getCommons() {
        return commons;
    }

    public void setCommons(String commons) {
        this.commons = commons;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
