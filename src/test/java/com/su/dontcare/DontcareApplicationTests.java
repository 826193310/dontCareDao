package com.su.dontcare;

import com.su.dontcare.service.GeneratorService;
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
		generatorService.generatorBySingleTable("tbluser");
	}

}
