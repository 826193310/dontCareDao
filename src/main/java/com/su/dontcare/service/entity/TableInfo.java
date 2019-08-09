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
    private String tableName; // 表明
    List<FieldInfo> fields; // 字段信息

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
}
