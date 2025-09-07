package com.framework.utils;
import software.amazon.awssdk.regions.Region; import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest; import software.amazon.awssdk.core.sync.RequestBody;
import java.nio.file.Paths;
public class S3Uploader {
    private final S3Client s3 = S3Client.builder().region(Region.AP_SOUTH_1).build();
    public void upload(String bucket,String key,String filePath){
        PutObjectRequest req = PutObjectRequest.builder().bucket(bucket).key(key).build();
        s3.putObject(req, RequestBody.fromFile(Paths.get(filePath)));
    }
}
