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
		tableInfo.setTableName("tblfasfdfssaveinfo");
		codeInfo.setMapperPath("com.su.dontcare.dao"); // mapper 接口包地
		codeInfo.setMapperXmlPath("com.su.dontcare.dao.xml");
		codeInfo.setGeneratorService(true); // 是否生成service层
		codeInfo.setServicePath("com.su.dontcare.service"); // serivce 接口生成包
		codeInfo.setDtoPath("com.su.dontcare.dto");
		codeInfo.setRespClass("com.su.dontcare.service.entity.Resp"); // 通用返回类
		codeInfo.setRespGeneric(true); // 返回类是否泛型， 作用于Service 层和 controller 层
		codeInfo.setOutputPath("D:/job/self/dontcaredao/dontCareDao/src/main/java");
		codeInfo.setGenericFiled("data");

		// 分页相关属性
		codeInfo.setEnablePageHelper(true);
		codeInfo.setPageRespClass("com.su.dontcare.service.entity.PageResp");
		codeInfo.setPageRespGeneric(true);
		PageVoAttr pageVoAttr = new PageVoAttr();
		pageVoAttr.setListToPageMethod("setPageInfo");
		codeInfo.setPageVoAttr(pageVoAttr);
		codeInfo.setTableInfo(tableInfo);

		// 生成controller 相关属性
		codeInfo.setGenerController(true);
		codeInfo.setControllerPath("com.su.dontcare.Controller");
		generatorService.generatorBySingleTable(codeInfo);
	}

}
