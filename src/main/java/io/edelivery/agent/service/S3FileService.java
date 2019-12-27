package io.edelivery.agent.service;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class S3FileService {
	
	private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    
    @PostConstruct
    private void initializeAmazon() {
    	log.info(this.accessKey + "--" + this.secretKey);
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
    		   .withRegion(Regions.US_WEST_2).build();
    }
    
    public void uploadFileTos3bucket(String fileName, File file) {
    	log.info(endpointUrl + "" + bucketName + "" + accessKey + "" + secretKey);
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
}