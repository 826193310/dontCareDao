package com.su.dontcare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.*")
public class DontcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(DontcareApplication.class, args);
	}

}
