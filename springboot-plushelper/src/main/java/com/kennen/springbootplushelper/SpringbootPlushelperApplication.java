package com.kennen.springbootplushelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringbootPlushelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPlushelperApplication.class, args);
	}

}
