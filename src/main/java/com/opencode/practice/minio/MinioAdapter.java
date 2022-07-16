
package com.opencode.practice.minio;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class MinioAdapter {

    @Autowired
    MinioClient minioClient;

    @Value("${minio.buckek.name}")
    String defaultBucketName;

    @Value("${minio.default.folder}")
    String defaultBaseFolder;

    public List<Bucket> getAllBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    public void uploadFile(String name, byte[] content) {
        File file = new File("/C:/Users/osagr/Downloads/" + name);
        file.canWrite();
        file.canRead();
        try {
            FileOutputStream iofs = new FileOutputStream(file);
            iofs.write(content);
            UploadObjectArgs.Builder builder = UploadObjectArgs.builder().bucket(defaultBucketName)
                    .object(defaultBaseFolder + name).filename(file.getAbsolutePath().toString());
            minioClient.uploadObject(builder.build());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public byte[] getFile(String key) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(defaultBucketName)
                        .object(defaultBaseFolder + "/" + key)
                        .build())) {

            byte[] content = IOUtils.toByteArray(stream);
            stream.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @PostConstruct
    public void init() {
    }
}
