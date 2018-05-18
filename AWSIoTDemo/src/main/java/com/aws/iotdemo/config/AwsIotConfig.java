package com.aws.iotdemo.config;

import org.socialsignin.spring.data.dynamodb.core.DynamoDBOperations;
import org.socialsignin.spring.data.dynamodb.core.DynamoDBTemplate;
import org.socialsignin.spring.data.dynamodb.mapping.event.ValidatingDynamoDBEventListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

@Configuration
public class AwsIotConfig {
	@Value("${amazon.dynamodb.endpoint}")
	private String amazonDynamoDBEndpoint;

	@Value("${amazon.aws.accesskey}")
	private String amazonAWSAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String amazonAWSSecretKey;

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(
				amazonAWSCredentials());
		
		
		if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
			amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
		} else {
			amazonDynamoDB.setEndpoint("dynamodb.ap-south-1.amazonaws.com");
		}
		return amazonDynamoDB;
	}
	
	
	@Bean
	public DynamoDBOperations dynamoDBOperations()
	{
		return new DynamoDBTemplate(amazonDynamoDB());
	}

	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
	}
	
	/** The following validation-related beans are optional - only
	 * required if JSR 303 validation is desired.  For validation to 
	 * work, the @EnableDynamoDBRepositories must be configured with
	 * a reference to DynamoDBOperations bean, rather than with
	 * reference to AmazonDynamoDB client
	 * */
	
	@Bean 
	public LocalValidatorFactoryBean validator()
	{
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public ValidatingDynamoDBEventListener validatingDynamoDBEventListener()
	{
		return new ValidatingDynamoDBEventListener(validator());
	}
}
