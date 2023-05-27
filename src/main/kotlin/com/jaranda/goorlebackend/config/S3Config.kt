package com.jaranda.goorlebackend.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config(
    @Value("\${aws.s3.region}")
    val region: String,
    @Value("\${aws.s3.credentials.accessKey}")
    val accessKey: String,
    @Value("\${aws.s3.credentials.secretKey}")
    val secretKey: String,
) {
    @Bean
    fun amazonS3() = AmazonS3ClientBuilder.standard()
        .withRegion(region)
        .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
        .build()!!
}