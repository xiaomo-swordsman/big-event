package com.xiaomo.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/*
    阿里云 oss存储
    桶列表地址：https://oss.console.aliyun.com/bucket
    账号：xiaomo_swordsman@163.com
    密码：yangteng024680,.
    手机：1327706927

    sdk地址：https://oss.console.aliyun.com/sdk
    accessKey地址 ： https://ram.console.aliyun.com/profile/access-keys?spm=5176.8465980.console-base_top-nav.dak.3beb14505Lmfty
 */
@Component
public class AliOssUtil {

    private final static String END_POINT = "https://oss-cn-beijing.aliyuncs.com";
    private final static String BUCKET_NAME = "big-event-bucket-demo";
    private final static String ACCES_KEY_ID = "LTAI5t9xytiZMBAGN1S61Uv4";
    private final static String ACCES_KEY_SECRET = "GGlKNjhu29PRDV5e7VettaNtb4XiNd";

    public static String uploadFile(String objectName, InputStream inputStream){
        String url = "";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(END_POINT,ACCES_KEY_ID,ACCES_KEY_SECRET);

        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME,objectName,inputStream);

            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传字符串。
            PutObjectResult result = ossClient.putObject(putObjectRequest);

            // 返回文件url地址
            // https://bucketname.oss-cn-beijing.aliyuncs.com/objectName
            // https://big-event-bucket-demo.oss-cn-beijing.aliyuncs.com/001.png
            url = "https://" + BUCKET_NAME + "." + END_POINT.substring(END_POINT.lastIndexOf("/") + 1) + "/" + objectName;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return url;
    }

}
