package com.su.dontcare.Util;

/**
*
*@Description: 字符串处理相关类
*@Author: guanzhou.su
*@Date: 2019/8/10
**/
public class StringUtil {

    /**
     *
     *@Description: 首字母大写
     *@Param: [str]
     *@Author: guanzhou.su
     *@Date: 2019/8/10
     *@return: void
     *
     **/
    public static String firstCharUpper(String str) {
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

}
