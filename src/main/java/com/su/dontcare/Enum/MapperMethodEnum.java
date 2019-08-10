package com.su.dontcare.Enum;

/**
*
*@Description: mapper 方法枚举（自动生成的代码中所含的方法）
*@Author: guanzhou.su
*@Date: 2019/8/10
**/
public enum MapperMethodEnum {
    GET_BY_PRIMARY("getByPrimaryKey", "根据主键获取数据"),
    FIND_LIST("findList", "列表查询"),
    INSERT("insertAll", "插入"),
    INSERT_DYNAMIC("insertDynamic", "动态插入，只插入存在值的数据");

    private String methodName;
    private String methodDesc;
    MapperMethodEnum(String methodName, String methodDesc) {
        this.methodDesc = methodDesc;
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }
}
