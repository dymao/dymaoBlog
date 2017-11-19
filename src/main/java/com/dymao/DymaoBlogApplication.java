package com.dymao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 扫描servlet
public class DymaoBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DymaoBlogApplication.class, args);
	}
}
