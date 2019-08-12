package com.su.dontcare.service.entity;

/**
*
*@Description: Dto 生成信息类
*@Author: guanzhou.su
*@Date: 2019/8/10
 *
**/
public class ServiceVo extends GeneratorCodeInfo {
    private String packName; // 包地址
    private String className; // 类名
    private String attrStr; // 类属性
    private String mapperClass; // service Mapper类
    private String mapperName; // mapper 名称
    private String respVo; // resp 名称
    private String listSearchVo; // 查询list 的时候的参数变量
    private String genericFiledGeter; // 泛型字段getter方法
    private String genericFiledSeter; // 泛型字段setter方法

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

    public String getMapperClass() {
        return mapperClass;
    }

    public void setMapperClass(String mapperClass) {
        this.mapperClass = mapperClass;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getRespVo() {
        return respVo;
    }

    public void setRespVo(String respVo) {
        this.respVo = respVo;
    }

    public String getListSearchVo() {
        return listSearchVo;
    }

    public void setListSearchVo(String listSearchVo) {
        this.listSearchVo = listSearchVo;
    }

    public String getGenericFiledGeter() {
        return genericFiledGeter;
    }

    public void setGenericFiledGeter(String genericFiledGeter) {
        this.genericFiledGeter = genericFiledGeter;
    }

    public String getGenericFiledSeter() {
        return genericFiledSeter;
    }

    public void setGenericFiledSeter(String genericFiledSeter) {
        this.genericFiledSeter = genericFiledSeter;
    }
}
