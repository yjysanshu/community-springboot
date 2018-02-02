package com.lxl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.lxl.common.mapper", "com.lxl.generator.mapper"})
public class Application {

	public static void main(String[] args) {
		//System.out.println("111111");
		SpringApplication.run(Application.class, args);
	}
}
