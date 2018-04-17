package com.dymao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan // 扫描servlet
@EnableTransactionManagement // 控制事务
public class DymaoBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DymaoBlogApplication.class, args);
	}
}
