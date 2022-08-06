package com.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {DesafioCwiApplication.class})
public class DesafioCwiApplication {
	public static void main(String[] args) {
		SpringApplication.run(DesafioCwiApplication.class, args);
	}

}
