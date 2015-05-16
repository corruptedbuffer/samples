package com.jmg.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableMongoRepositories
public class SampleSpringMongodbVaadinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringMongodbVaadinApplication.class, args);
	}
}
