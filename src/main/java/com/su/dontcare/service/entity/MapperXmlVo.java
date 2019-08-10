package com.su.dontcare.service.entity;

/**
*
*@Description: 生成代码信息配置类
*@Author: guanzhou.su
*@Date: 2019/8/10
 *
**/
public class MapperXmlVo {
    private String outputPath; // 输出的目录
    private String packagePath; // 包路径
    private boolean generService; // 是否生成 service层代码
    private boolean generController; // 是否生成 controller 层面的代码
    private String respVo; // 返回类， 请写完整报名
    private TableInfo tableInfo; // 表信息
    private String mapperPath; // Mapper 生成路径
    private String servicePath; // service 生成路径
    private String controllerPath; // controller 生成路径
    private String mapperNameSpace; //
    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public boolean isGenerService() {
        return generService;
    }

    public void setGenerService(boolean generService) {
        this.generService = generService;
    }

    public boolean isGenerController() {
        return generController;
    }

    public void setGenerController(boolean generController) {
        this.generController = generController;
    }

    public String getRespVo() {
        return respVo;
    }

    public void setRespVo(String respVo) {
        this.respVo = respVo;
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public String getMapperPath() {
        return mapperPath;
    }

    public void setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }

    public String getMapperNameSpace() {
        return mapperNameSpace;
    }

    public void setMapperNameSpace(String mapperNameSpace) {
        this.mapperNameSpace = mapperNameSpace;
    }
}
