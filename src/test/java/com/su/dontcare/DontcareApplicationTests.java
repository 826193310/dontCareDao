package com.su.dontcare;

import com.su.dontcare.service.GeneratorService;
import com.su.dontcare.service.entity.GeneratorCodeInfo;
import com.su.dontcare.service.entity.PageVoAttr;
import com.su.dontcare.service.entity.TableInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DontcareApplicationTests {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Autowired
	GeneratorService generatorService;
	@Test
	public void contextLoads() {
	}

	@Test
	public void testGenerator() {
		GeneratorCodeInfo codeInfo = new GeneratorCodeInfo();
		TableInfo tableInfo = new TableInfo();/*,tblAccessUser*/
		codeInfo.setOutputPath("E:/job/华龙微信/网关开发存档/20190829智能盯盘处理/weixin-app-server/src");
		tableInfo.setTableName("TBL_ACCOUNT_TEMPLATE_ALERT, TBL_SMS_SERVICE");// 要生成的表， 多表批量生成用逗号分隔， 如：tbluser,tblfasfdfssaveinfo,stu
		codeInfo.setMapperPath("com.kds2.mapper"); // mapper 接口包地
		codeInfo.setMapperXmlPath("com.kds2.mapper"); // 生成 Mapper.xml 到该包下
		codeInfo.setDtoPath("com.kds2.entity"); // 生成 Dto 到该包下
		codeInfo.setDtoName("AccountTemplateAlert,SmsService");
		//codeInfo.setDtoName(""); // dtoName. 设置这个的话， Mapper, Mapper.xml, Service, Dto, Controller 均按照此命名生成，
									  // 否则按照表名首字符大写形式生成. 如果设多个，以逗号分隔即可。 如User,FasfdfsSaveInfo
		//codeInfo.setDtoExtendClass("com.kds2.entity.base.BaseEntity"); // dto 继承类
		//codeInfo.setDtoExtendsClassFields("id, createBy, lastUpdateBy, createTime, lastUpdateTime, enable"); // dto 继承类所含字段
		//codeInfo.setMapperExtendClass("com.su.dontcare.mapper.BaseMapper"); // Mapper继承类


		/*codeInfo.setGeneratorService(true); // 是否生成service层
		codeInfo.setServicePath("com.kds2.service"); // serivce 接口生成包
		codeInfo.setRespClass("com.kds2.resp.VoResp"); // 通用返回类
		codeInfo.setRespGeneric(true); // 返回类是否泛型， 作用于Service 层和 controller 层
		codeInfo.setGenericFiled("data");
		codeInfo.setServiceExtendClass("com.kds2.service.BaseService");*/


		// 分页相关属性

		/*
		codeInfo.setEnablePageHelper(false); // 是否开启生成分页方法
		codeInfo.setPageRespClass("com.su.dontcare.service.entity.PageResp");// 分页返回类
		codeInfo.setPageRespGeneric(false); // 分页返回类是否泛型

		PageVoAttr pageVoAttr = new PageVoAttr(); // 分页返回类属性
		pageVoAttr.setListToPageMethod("setPageInfo"); // 分页返回类组装 PageInfo 的方法
		codeInfo.setPageVoAttr(pageVoAttr);*/
		codeInfo.setTableInfo(tableInfo);


		// 生成controller 相关属性 com.su.dontcare.Controller.BaseController
		/*codeInfo.setGenerController(true); // 是否生成controller
		codeInfo.setControllerPath("com.kds2.controller"); // controller 包名
		codeInfo.setControllerExtendClass("com.kds2.controller.BaseController");*/
		generatorService.generatorBySingleTable(codeInfo);
	}


	@Test
	public void testGenerator2() {
		GeneratorCodeInfo codeInfo = new GeneratorCodeInfo();
		TableInfo tableInfo = new TableInfo();/*,tblAccessUser*/
		codeInfo.setOutputPath("E:/job/华龙微信/网关开发存档/20190829智能盯盘处理/weixin-app-server/src");
		tableInfo.setTableName("tblWeixinTemplateExtLog");// 要生成的表， 多表批量生成用逗号分隔， 如：tbluser,tblfasfdfssaveinfo,stu
		codeInfo.setMapperPath("com.kds2.mapper"); // mapper 接口包地
		codeInfo.setMapperXmlPath("com.kds2.mapper"); // 生成 Mapper.xml 到该包下
		codeInfo.setDtoPath("com.kds2.entity"); // 生成 Dto 到该包下
		//codeInfo.setDtoName(""); // dtoName. 设置这个的话， Mapper, Mapper.xml, Service, Dto, Controller 均按照此命名生成，
		// 否则按照表名首字符大写形式生成. 如果设多个，以逗号分隔即可。 如User,FasfdfsSaveInfo
		codeInfo.setDtoExtendClass("com.kds2.entity.base.BaseEntity"); // dto 继承类
		codeInfo.setDtoExtendsClassFields("id, createBy, lastUpdateBy, createTime, lastUpdateTime, enable"); // dto 继承类所含字段
		codeInfo.setTableInfo(tableInfo);
		generatorService.generatorBySingleTable(codeInfo);
	}
}
