package com.su.dontcare.Enum;

/**
*
*@Description: java 字段和引进
*@Author: guanzhou.su
*@Date: 2019/8/10
**/
public enum TypeClassEnum {
    STRING("String","java.lang.String"),
    INTEGER("Integer","java.lang.Integer"),
    DATE("Date", "java.sql.Date"),
    LONG("Long", "java.lang.Long"),
    BIT("BIT", "java.lang.Integer");
    private String typeName;
    private String typeImportClass;
    TypeClassEnum(String typeName, String typeImportClass) {
        this.typeName = typeName;
        this.typeImportClass = typeImportClass;
    }

    public static String findImportByType(String typeName) throws Exception{
        for (TypeClassEnum value : values()) {
            if (value.getTypeName().equals(typeName)) {
                return value.getTypeImportClass();
            }
        }
        throw new Exception("cannot find import class for you typeName:" + typeName);
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeImportClass() {
        return typeImportClass;
    }

    public void setTypeImportClass(String typeImportClass) {
        this.typeImportClass = typeImportClass;
    }
}
