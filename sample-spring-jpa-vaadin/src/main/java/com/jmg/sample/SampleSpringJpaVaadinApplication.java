package com.jmg.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableJpaRepositories
public class SampleSpringJpaVaadinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringJpaVaadinApplication.class, args);
	}
}
