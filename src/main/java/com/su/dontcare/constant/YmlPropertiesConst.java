package com.su.dontcare.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
*
*@Description:  读取 yml 变量类
*@Author: guanzhou.su
*@Date: 2019/8/11
**/
@Component
public class YmlPropertiesConst {

    @Value("${spring.datasource.driver-class-name}")
    public String driverClass;

}
