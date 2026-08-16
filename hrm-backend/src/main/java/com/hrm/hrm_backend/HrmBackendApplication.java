package com.hrm.hrm_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hrm.hrm_backend.mapper") // 自動掃描並注入Mapper
public class HrmBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmBackendApplication.class, args);
	}

}
