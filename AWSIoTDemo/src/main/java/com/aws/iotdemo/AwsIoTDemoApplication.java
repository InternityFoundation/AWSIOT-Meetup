package com.aws.iotdemo;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = "com.aws.iotdemo")
@EnableDynamoDBRepositories("com.aws.iotdemo.repository")
@SpringBootApplication
@EnableScheduling
public class AwsIoTDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsIoTDemoApplication.class, args);
	}
}
