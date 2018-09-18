package com.calf.cn.utils;


import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

/**
 * Created by admin on 2017/7/6.
 */

public class MD5Utils {
    private static final String TAG = "MD5Utils";
    public static final int MD5_FILE_BUF_LENGTH = 1024 * 100;
    /**
     * Get the md5 for the file. call getMD5(FileInputStream is, int bufLen) inside.
     *
     * @param file
     */
    public static String getMD5(final File file) {
        if (file == null || !file.exists()) {
            return null;
        }

        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            String md5 = getMD5(fin);
            return md5;
        } catch (Exception e) {
            return null;
        } finally {
            closeQuietly(fin);
        }
    }

    /**
     * Closes the given {@code Closeable}. Suppresses any IO exceptions.
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    public static String getMD5(final InputStream is) {
        if (is == null) {
            return null;
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            MessageDigest md = MessageDigest.getInstance("MD5");
            StringBuilder md5Str = new StringBuilder(32);

            byte[] buf = new byte[MD5_FILE_BUF_LENGTH];
            int readCount;
            while ((readCount = bis.read(buf)) != -1) {
                md.update(buf, 0, readCount);
            }

            byte[] hashValue = md.digest();

            for (int i = 0; i < hashValue.length; i++) {
                md5Str.append(Integer.toString((hashValue[i] & 0xff) + 0x100, 16).substring(1));
            }
            return md5Str.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 给指定字符串按照md5算法去加密
     * @param psd	需要加密的密码	加盐处理
     * @return		md5后的字符串
     */
    public static String encoder(String psd) {
        try {
            //加盐处理
            //psd = psd+"mobilesafe";
            //1,指定加密算法类型
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //2,将需要加密的字符串中转换成byte类型的数组,然后进行随机哈希过程
            byte[] bs = digest.digest(psd.getBytes());
//			System.out.println(bs.length);
            //3,循环遍历bs,然后让其生成32位字符串,固定写法
            //4,拼接字符串过程
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bs) {
                int i = b & 0xff;
                //int类型的i需要转换成16机制字符
                String hexString = Integer.toHexString(i);
//				System.out.println(hexString);
                if(hexString.length()<2){
                    hexString = "0"+hexString;
                }
                stringBuffer.append(hexString);
            }
            //5,打印测试
//			System.out.println(stringBuffer.toString());
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /***
	 * 根据字符串生成 32位的 MD5 码
	 * 
	 * @author tmc.sun 2012-11-05
	 * @param str
	 *            待生成 MD5码的字符串
	 * @return 根据字符串生成的 MD5码
	 */
	public static String stringToMD5(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}

		MessageDigest md5 = null;
		StringBuffer value = new StringBuffer();

		try {
			md5 = MessageDigest.getInstance("MD5");

			byte[] md5Bytes = md5.digest(str.getBytes("UTF-8"));

			for (int i = 0; i < md5Bytes.length; i++) {
				int val = (md5Bytes[i]) & 0xff;
				if (val < 16) {
					value.append("0");
				}
				value.append(Integer.toHexString(val));
			}
		} catch (Exception e) {

			return "";
		}

		return value.toString();

	}
    
}
