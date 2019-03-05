package com.github.axonsimple;

import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.axonframework.springboot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class,
		JpaEventStoreAutoConfiguration.class, JdbcAutoConfiguration.class})
public class AxonSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxonSimpleApplication.class, args);
	}

}
