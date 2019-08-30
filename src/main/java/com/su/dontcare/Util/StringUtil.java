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
        if (str == null || str.trim().equals("")) return str;
        char[] cs=str.toCharArray();
        if (Character.isUpperCase(cs[0])) return String.valueOf(cs);

        cs[0]-=32;
        return String.valueOf(cs);
    }

    /**
     *@Description: 含有下划线的字段移除下划线
     *@Param: [str]
     *@Author: guanzhou.su,  dont know what is  mean? contact me at QQ:838951396, wechat:13824866769
     *@Date: 2019/8/29
     *@return: void
     *
     **/
    public static String removeUnderline(String str) {
        if (str.indexOf("_") > -1) {
            StringBuffer  sb = new StringBuffer();
            String[] strs = str.split("_");
            for (int i = 0; i < strs.length; i++) {
                if (i == 0) {
                    System.out.println(strs[i].toLowerCase());
                    sb.append(strs[i].toLowerCase());
                } else {
                    String fStr = strs[i].toLowerCase();
                    sb.append(firstCharUpper(fStr));
                }
            }
            return sb.toString();
        }
        return new String(str);
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
