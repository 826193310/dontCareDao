package com.su.dontcare.service.entity;

/**
*
*@Description: 生成分页时的分页属性参数
*@Author: guanzhou.su
*@Date: 2019/8/13
**/
public class PageVoAttr {
    private String currentPageAttrName; // 当前页属性名称
    private String pageNumAttrName="pageNum"; // 当前分页
    private String listToPageMethod; //组装page到返回类的方法

    public String getCurrentPageAttrName() {
        return currentPageAttrName;
    }

    public void setCurrentPageAttrName(String currentPageAttrName) {
        this.currentPageAttrName = currentPageAttrName;
    }

    public String getPageNumAttrName() {
        return pageNumAttrName;
    }

    public void setPageNumAttrName(String pageNumAttrName) {
        this.pageNumAttrName = pageNumAttrName;
    }

    public String getListToPageMethod() {
        return listToPageMethod;
    }

    public void setListToPageMethod(String listToPageMethod) {
        this.listToPageMethod = listToPageMethod;
    }
}
