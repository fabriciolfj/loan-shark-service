package com.github.loanshark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.github.loanshark"})
public class LoanSharkServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanSharkServiceApplication.class, args);
	}

}
