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

    /**
     *
     *@Description: 首字母小写
     *@Param: [s]
     *@Author: guanzhou.su
     *@Date: 2019/8/10
     *@return: void
     *
     **/
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0))) return s;
        else  return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String pageFormatToFilePath(String packageName) {
        return packageName.replace(".", "/");
    }
}
