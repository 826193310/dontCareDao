package com.su.dontcare.service.entity;

import java.util.List;

/**
*
*@Description: 生成代码信息配置类
*@Author: guanzhou.su
*@Date: 2019/8/10
 *
**/
public class GeneratorCodeInfo {
    private String outputPath; // 输出的目录
    private String packagePath; // 包路径
    private boolean generService; // 是否生成 service层代码
    private boolean generController; // 是否生成 controller 层面的代码
    private String respVo; // 返回类， 请写完整报名
    private TableInfo tableInfo; // 表信息
    private String mapperPath; // Mapper 生成路径
    private String servicePath; // service 生成路径
    private String controllerPath; // controller 生成路径
    private String dtoPath; // dto 输出路径
    private String projectPath; // 项目路径
    private String primaryKeyType; // 主键类型
    private String dtoName; // dto 对象名称
    private List<String> mapperImportClass; // mapper 需要引入的 class
    private List<String> dtoImportClass; // dto 需要引入的 class
    private boolean generatorService; // 是否生成service 层
    private boolean generatorController; // 是否生成 controller 层
    private boolean generatorSwagger; // 是否生成swagger 接口
    private String dtoExtendClass; // dto 接口集成类
    private String respClass; // controller 和 service 层返回处理, 包的全路径
    private boolean respGeneric; // 返回类是否为泛型
    private String genericFiled; // resp 类型的泛型字段

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

    public String getDtoPath() {
        return dtoPath;
    }

    public void setDtoPath(String dtoPath) {
        this.dtoPath = dtoPath;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getPrimaryKeyType() {
        return primaryKeyType;
    }

    public void setPrimaryKeyType(String primaryKeyType) {
        this.primaryKeyType = primaryKeyType;
    }

    public String getDtoName() {
        return dtoName;
    }

    public void setDtoName(String dtoName) {
        this.dtoName = dtoName;
    }

    public List<String> getMapperImportClass() {
        return mapperImportClass;
    }

    public void setMapperImportClass(List<String> mapperImportClass) {
        this.mapperImportClass = mapperImportClass;
    }

    public List<String> getDtoImportClass() {
        return dtoImportClass;
    }

    public void setDtoImportClass(List<String> dtoImportClass) {
        this.dtoImportClass = dtoImportClass;
    }

    public boolean isGeneratorService() {
        return generatorService;
    }

    public void setGeneratorService(boolean generatorService) {
        this.generatorService = generatorService;
    }

    public boolean isGeneratorController() {
        return generatorController;
    }

    public void setGeneratorController(boolean generatorController) {
        this.generatorController = generatorController;
    }

    public boolean isGeneratorSwagger() {
        return generatorSwagger;
    }

    public void setGeneratorSwagger(boolean generatorSwagger) {
        this.generatorSwagger = generatorSwagger;
    }

    public String getDtoExtendClass() {
        return dtoExtendClass;
    }

    public void setDtoExtendClass(String dtoExtendClass) {
        this.dtoExtendClass = dtoExtendClass;
    }

    public String getRespClass() {
        return respClass;
    }

    public void setRespClass(String respClass) {
        this.respClass = respClass;
    }

    public boolean isRespGeneric() {
        return respGeneric;
    }

    public void setRespGeneric(boolean respGeneric) {
        this.respGeneric = respGeneric;
    }

    public String getGenericFiled() {
        return genericFiled;
    }

    public void setGenericFiled(String genericFiled) {
        this.genericFiled = genericFiled;
    }
}
