package com.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
		
		// command to run project on sonarqube dashboard : 
		// mvn clean verify sonar:sonar -Dsonar.projectKey=spring-sec-demo -Dsonar.projectName=spring-sec-demo -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_865e1cf59b6d708a1d2bbd411e60527e15efafc2

	}

}
