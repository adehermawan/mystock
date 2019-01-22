package com.adhe.mystock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = { 
		MystockApplication.class,
		Jsr310JpaConverters.class 
})
public class MystockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MystockApplication.class, args);
	}

}

