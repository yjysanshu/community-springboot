package com.lxl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.lxl.api.mapper")
public class Community2Application {

	public static void main(String[] args) {
		SpringApplication.run(Community2Application.class, args);
	}
}
