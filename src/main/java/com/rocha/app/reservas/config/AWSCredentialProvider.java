package com.rocha.app.reservas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

@Configuration
public class AWSCredentialProvider {
	
	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;
	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;

  @Bean
  @Primary
  public AWSCredentialsProvider buildAWSCredentialsProvider() {
    AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
    return new AWSStaticCredentialsProvider(awsCredentials);
  }
}
