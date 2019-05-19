package com.springboothc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.springboothc.demo.mapper")
public class SpringboothcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringboothcApplication.class, args);
	}

}
