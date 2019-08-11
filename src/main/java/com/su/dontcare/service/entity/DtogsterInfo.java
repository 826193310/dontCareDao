package com.su.dontcare.service.entity;

/**
*
*@Description: dto getter 和 setter 方法
*@Author: guanzhou.su
*@Date: 2019/8/11
**/
public class DtogsterInfo extends FieldInfo{
    private String getterName;
    private String setterName;

    public String getGetterName() {
        return getterName;
    }

    public void setGetterName(String getterName) {
        this.getterName = getterName;
    }

    public String getSetterName() {
        return setterName;
    }

    public void setSetterName(String setterName) {
        this.setterName = setterName;
    }
} 
