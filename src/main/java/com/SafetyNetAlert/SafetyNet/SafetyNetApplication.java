package com.SafetyNetAlert.SafetyNet;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SafetyNetApplication {

	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}
}