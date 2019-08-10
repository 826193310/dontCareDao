package com.su.dontcare.service.entity;

/**
*
*@Description: Dto 生成信息类
*@Author: guanzhou.su
*@Date: 2019/8/10
 *
**/
public class DtoVo extends GeneratorCodeInfo {
    private String packName; // 包地址
    private String className; // 类名
    private String attrStr; // 类属性
    private String getterAndSetters; // 字段的getter 和 setter


    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAttrStr() {
        return attrStr;
    }

    public void setAttrStr(String attrStr) {
        this.attrStr = attrStr;
    }

    public String getGetterAndSetters() {
        return getterAndSetters;
    }

    public void setGetterAndSetters(String getterAndSetters) {
        this.getterAndSetters = getterAndSetters;
    }
}
