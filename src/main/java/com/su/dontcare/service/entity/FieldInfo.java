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
    private boolean isId; // 是否为ID

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

    public boolean isId() {
        return isId;
    }

    public void setId(boolean id) {
        isId = id;
    }
}
