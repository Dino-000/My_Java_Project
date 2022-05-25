package com.axonactive.demo2;

import com.axonactive.demo2.entities.Department;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Demo2Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(Demo2Application.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
		Department department = Department.builder()
				.name("IT")
				.build();

		Department department2 = Department.builder()

				.name("HR")
				.build();
	}

}
