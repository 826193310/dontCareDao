package com.su.dontcare.service.entity;

import java.util.List;

/**
*
*@Description: Dto 生成信息类
*@Author: guanzhou.su
*@Date: 2019/8/10
 *
**/
public class ControllerVo extends GeneratorCodeInfo {
    private String packName; // 包地址
    private String className; // 类名
    private String attrStr; // 类属性
    private String serviceClass; // service 类
    private String serviceName; // service  名称
    private String respVo; // resp 名称
    private String pageRespVo; // 分页返回类
    private String paramVo; // 查询的时候的参数变量
    private String genericFiledGeter; // 泛型字段getter方法
    private String genericFiledSeter; // 泛型字段setter方法
    private List<String> controllerImportClass; // service 层引进的class

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

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRespVo() {
        return respVo;
    }

    public void setRespVo(String respVo) {
        this.respVo = respVo;
    }

    public String getParamVo() {
        return paramVo;
    }

    public void setParamVo(String paramVo) {
        this.paramVo = paramVo;
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

    public String getPageRespVo() {
        return pageRespVo;
    }

    public void setPageRespVo(String pageRespVo) {
        this.pageRespVo = pageRespVo;
    }

    public List<String> getControllerImportClass() {
        return controllerImportClass;
    }

    public void setControllerImportClass(List<String> controllerImportClass) {
        this.controllerImportClass = controllerImportClass;
    }
}
