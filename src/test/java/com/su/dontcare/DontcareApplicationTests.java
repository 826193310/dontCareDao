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
		TableInfo tableInfo = new TableInfo();
		codeInfo.setOutputPath("D:/job/self/dontcaredao/dontCareDao/src/main/java");
		tableInfo.setTableName("tblfasfdfssaveinfo");// 要生成的表
		codeInfo.setMapperPath("com.su.dontcare.dao"); // mapper 接口包地
		codeInfo.setMapperXmlPath("com.su.dontcare.dao.Mapper"); // 生成 Mapper.xml 到该包下
		codeInfo.setDtoPath("com.su.dontcare.dto"); // 生成 Dto 到该包下
		codeInfo.setDtoExtendClass("com.su.dontcare.service.entity.BaseResp"); // dto 继承类
		codeInfo.setDtoExtendsClassFields("cUser, cTime, uUser, uDate"); // dto 继承类所含字段
		codeInfo.setMapperExtendClass("com.su.dontcare.mapper.BaseMapper"); // Mapper继承类
		codeInfo.setGeneratorService(true); // 是否生成service层
		codeInfo.setServicePath("com.su.dontcare.service"); // serivce 接口生成包
		codeInfo.setRespClass("com.su.dontcare.service.entity.Resp"); // 通用返回类
		codeInfo.setRespGeneric(true); // 返回类是否泛型， 作用于Service 层和 controller 层
		codeInfo.setGenericFiled("data");
		codeInfo.setServiceExtendClass("com.su.dontcare.service.BaseService");
		// 分页相关属性
		codeInfo.setEnablePageHelper(true); // 是否开启生成分页方法
		codeInfo.setPageRespClass("com.su.dontcare.service.entity.PageResp");// 分页返回类
		codeInfo.setPageRespGeneric(true); // 分页返回类是否泛型

		PageVoAttr pageVoAttr = new PageVoAttr(); // 分页返回类属性
		pageVoAttr.setListToPageMethod("setPageInfo"); // 分页返回类组装 PageInfo 的方法
		codeInfo.setPageVoAttr(pageVoAttr);
		codeInfo.setTableInfo(tableInfo);


		// 生成controller 相关属性 com.su.dontcare.Controller.BaseController
		codeInfo.setGenerController(true); // 是否生成controller
		codeInfo.setControllerPath("com.su.dontcare.Controller"); // controller 包名
		codeInfo.setControllerExtendClass("com.su.dontcare.Controller.BaseController");
		generatorService.generatorBySingleTable(codeInfo);
	}

}
