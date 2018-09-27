package com.honglu.quickcall.common.third.OSS;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class OSSUtil {

    private static Logger logger = LoggerFactory.getLogger(OSSUtil.class);

   
    private static String endPoint =   ResourceBundle.getBundle("thirdconfig").getString("endPoint");
	private static String accessKeyId =   ResourceBundle.getBundle("thirdconfig").getString("accessKeyId");
	private static String accessKeySecret =   ResourceBundle.getBundle("thirdconfig").getString("accessKeySecret");
	private static String bucketName =   ResourceBundle.getBundle("thirdconfig").getString("bucketName");
	public static String ossUrl =   ResourceBundle.getBundle("thirdconfig").getString("ossUrl");

    public OSSUtil() {
    }
/*

    static {
        InputStream is = null;
        try {
            Properties constant = new Properties();
            is = OSSUtil.class.getClassLoader().getResourceAsStream("config.properties");
            if (is != null) {
                constant.load(is);
            }
            endPoint = constant.getProperty("endPoint");
            accessKeyId = constant.getProperty("accessKeyId");
            accessKeySecret = constant.getProperty("accessKeySecret");
            bucketName = constant.getProperty("bucketName");
            ossUrl = constant.getProperty("ossUrl");

        } catch (IOException e) {
            logger.error("读取ftp配置文件出错", e);
        } catch (Exception e) {
            logger.error("OSSUtil init error", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("关闭ftp配置文件流出错", e);
                }
            }
        }
    }
*/


    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        OSSUtil.accessKeyId = accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        OSSUtil.accessKeySecret = accessKeySecret;
    }

    public static String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        OSSUtil.endPoint = endPoint;
    }


    public static String getBucketName() {
        return bucketName;
    }

    public static void setBucketName(String bucketName) {
        OSSUtil.bucketName = bucketName;
    }

    public static OSSClient getOSSClient() {
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        return ossClient;
    }

    /**
     * 新建Bucket --Bucket权限:私有
     *
     * @param bucketName bucket名称
     * @return true 新建Bucket成功
     */
    public static final boolean createBucket(OSSClient client, String bucketName) {
        Bucket bucket = client.createBucket(bucketName);
        return bucketName.equals(bucket.getName());
    }

    /**
     * 删除Bucket
     *
     * @param bucketName bucket名称
     */
    public static final void deleteBucket(OSSClient client, String bucketName) {
        client.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 向阿里云的OSS存储中存储文件 --file也可以用InputStream替代
     *
     * @param client   OSS客户端
     * @param file     上传文件
     * @param diskName 上传文件的目录 --bucket下文件的路径
     * @return String 唯一MD5数字签名
     */
    public static final boolean uploadObject2OSS(OSSClient client, File file, String diskName) throws FileNotFoundException {
        InputStream is = new FileInputStream(file);
        try {
            String fileName = file.getName();
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(is.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("UTF-8");
            metadata.setContentType(getContentType(fileName));
            // 上传文件
            client.putObject(bucketName, diskName + "/" + fileName, is, metadata);
        } catch (Exception e) {
            logger.error("上传文件到OSS失败", e);
            return false;
        } finally {
            if (null != is) {
                logger.info("关闭文件的输入流！");
                try {
                    is.close();
                } catch (Exception e) {
                    logger.error("关闭文件的输入流异常", e);
                }
            }
        }
        return true;
    }

    /**
     * 向阿里云的OSS存储中存储文件 --file也可以用InputStream替代
     *
     * @param client   OSS客户端
     * @param fileName 上传文件
     * @param diskName 上传文件的目录 --bucket下文件的路径
     * @return String 唯一MD5数字签名
     */
    public static final boolean uploadInputStreamObject2OSS(OSSClient client, InputStream is, String fileName, String diskName) throws FileNotFoundException {
        try {
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(is.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("UTF-8");
            metadata.setContentType(getContentType(fileName));
            // 上传文件
            PutObjectResult ddd = client.putObject(bucketName, diskName + "/" + fileName, is, metadata);
            logger.info("上传文件 result : " + JSONObject.toJSONString(ddd));
        } catch (Exception e) {
            logger.error("上传文件到OSS失败", e);
            return false;
        } finally {
            if (null != is) {
                logger.info("关闭文件的输入流！");
                try {
                    is.close();
                } catch (Exception e) {
                    logger.error("关闭文件的输入流异常", e);
                }
            }
        }
        return true;
    }

    /**
     * 根据key获取OSS服务器上的文件到本地
     *
     * @param client        OSS客户端
     * @param bucketName    bucket名称
     * @param yourLocalFile 文件路径
     * @param key           Bucket下的文件的路径名+文件名
     */
    public static final void getOSS2LocalFile(OSSClient client, String bucketName, String yourLocalFile, String key) {
        client.getObject(new GetObjectRequest(bucketName, key), new File(yourLocalFile));
    }

    /**
     * 根据key获取OSS服务器上的文件输入流
     *
     * @param client     OSS客户端
     * @param bucketName bucket名称
     * @param diskName   文件路径
     * @param key        Bucket下的文件的路径名+文件名
     */
    public static final InputStream getOSS2InputStream(OSSClient client, String bucketName, String diskName, String key) {
        OSSObject ossObj = client.getObject(bucketName, diskName + key);
        return ossObj.getObjectContent();
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param client     OSS客户端
     * @param bucketName bucket名称
     * @param diskName   文件路径
     * @param key        Bucket下的文件的路径名+文件名
     */
    public static void deleteFile(OSSClient client, String bucketName, String diskName, String key) {
        client.deleteObject(bucketName, diskName + key);
        logger.info("删除" + bucketName + "下的文件" + diskName + key + "成功");
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static final String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("bmp".equalsIgnoreCase(fileExtension))
            return "image/bmp";
        if ("gif".equalsIgnoreCase(fileExtension))
            return "image/gif";
        if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension) || "png".equalsIgnoreCase(fileExtension))
            return "image/jpeg";
        if ("html".equalsIgnoreCase(fileExtension))
            return "text/html";
        if ("txt".equalsIgnoreCase(fileExtension))
            return "text/plain";
        if ("vsd".equalsIgnoreCase(fileExtension))
            return "application/vnd.visio";
        if ("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension))
            return "application/vnd.ms-powerpoint";
        if ("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension))
            return "application/msword";
        if ("xml".equalsIgnoreCase(fileExtension))
            return "text/xml";
        return "text/html";
    }

}
