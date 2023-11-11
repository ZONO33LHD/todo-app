package com.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.project.dao")
public class TodoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

}
