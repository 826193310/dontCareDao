package com.su.dontcare.service.entity;

import java.util.List;

/**
*
*@Description: 生成代码信息配置类
*@Author: guanzhou.su
*@Date: 2019/8/10
 *
**/
public class MapperXmlVo extends GeneratorCodeInfo{
    private String mapperNameSpace;
    private String insertDtoParamType; // 生成 mapper.xml 的时候的 paramType
    private List<FieldInfo> fieldsNotContainId; // 不包含ID的字段列表

    public String getMapperNameSpace() {
        return mapperNameSpace;
    }

    public void setMapperNameSpace(String mapperNameSpace) {
        this.mapperNameSpace = mapperNameSpace;
    }

    public String getInsertDtoParamType() {
        return insertDtoParamType;
    }

    public void setInsertDtoParamType(String insertDtoParamType) {
        this.insertDtoParamType = insertDtoParamType;
    }

    public List<FieldInfo> getFieldsNotContainId() {
        return fieldsNotContainId;
    }

    public void setFieldsNotContainId(List<FieldInfo> fieldsNotContainId) {
        this.fieldsNotContainId = fieldsNotContainId;
    }
}
