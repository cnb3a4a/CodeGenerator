package com.b3a4a.groups.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class GeneratorApplication {


	public static void main(String[] args) {
		SpringApplication.run(GeneratorApplication.class, args);
	}

}
