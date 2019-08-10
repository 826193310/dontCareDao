package com.su.dontcare;

import com.su.dontcare.service.GeneratorService;
import com.su.dontcare.service.entity.GeneratorCodeInfo;
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
	public void testJDBC() {
		GeneratorCodeInfo codeInfo = new GeneratorCodeInfo();
		TableInfo tableInfo = new TableInfo();
		tableInfo.setTableName("tbluser");
		codeInfo.setMapperPath("com/su/dontcare/dao");
		codeInfo.setOutputPath("D:/job/self/dontcaredao/dontCareDao/src/main/java");
		codeInfo.setTableInfo(tableInfo);
		generatorService.generatorBySingleTable(codeInfo);
	}

}
