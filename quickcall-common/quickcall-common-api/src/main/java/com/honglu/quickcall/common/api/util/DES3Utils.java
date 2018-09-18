package com.honglu.quickcall.common.api.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

public class DES3Utils {

	// 定义加密算法，DESede即3DES
	private static final String Algorithm = "DESede";

	private DES3Utils() {
	}
	
    // MD5
    public static String MD5Encoding(String source) throws NoSuchAlgorithmException {
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        byte[] input = source.getBytes();
        mdInst.update(input);
        byte[] output = mdInst.digest();

        int i = 0;

        StringBuilder buf = new StringBuilder();

        for (int offset = 0; offset < output.length; offset++) {
            i = output[offset];

            if (i < 0) {
                i += 256;
            }

            if (i < 16) {
                buf.append('0');
            }

            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }
	/**
	 * 加密方法
	 *
	 * @param src
	 *            源数据的字节数组
	 * @return
	 */
	public static String encryptMode(String src ,String key) {
		try {
			byte [] targetSrc = src.getBytes("utf-8") ;
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(build3DesKey(key),
					Algorithm);
			// 实例化Cipher
			Cipher cipher = Cipher.getInstance(Algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, deskey);
			return Base64Util.encode(cipher.doFinal(targetSrc));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密函数
	 *
	 * @param src
	 *            密文的字节数组
	 * @return
	 */
	public static String decryptMode(String src ,String key) {
		try {
			byte [] targetSrc = Base64Util.decode(src) ;
			SecretKey deskey = new SecretKeySpec(build3DesKey(key),
					Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return new String(c1.doFinal(targetSrc) ,Charset.forName("UTF-8"));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}
	 /**
     * 生成MD5数据签名
     * @param jsonStr
     * 			数据体的JSON格式的字符串,不能是JSONARRAY类型字符串
     * @return
     * @throws NoSuchAlgorithmException
     */
    @SuppressWarnings("unchecked")
	public static String getSign(String jsonStr) {
		// 判断需要生成的签名字符串是否为空
		if (jsonStr != null && jsonStr.trim().length() > 0) {
			// 将JSON格式字符串转换成TREEMAP进行属性KEY值升序排列
			TreeMap<String, Object> jsonMap = JSONObject.parseObject(jsonStr, TreeMap.class);
			// 瘵签名数据进午签明前格式接装key=value且用&连接
			if (jsonMap != null && jsonMap.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (String key : jsonMap.keySet()) {
					sb.append(key).append("=").append(jsonMap.get(key)).append("&");
				}
				String md5Sign = sb.substring(0, sb.length() - 1);
				// 进行MD5签名
				try {
					return MD5Encoding(md5Sign);
				} catch (NoSuchAlgorithmException e) {
					throw new RuntimeException("数据签名发生异常 : " + e.getMessage());
				}
			}
		}
		return null;
	}
	 /**
     * 对数据进行验签
     * @param JsonData
     * 			JSON格式的字符串
     * @param sign
     * 			原签名字符串
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public synchronized static boolean checkSign(String JsonData, String sign) throws UnsupportedEncodingException, Exception {
		if (JsonData == null) {
			throw new RuntimeException("签名内容不能为空");
		}
		if (sign == null) {
			throw new RuntimeException("原签名内容不能为空");
		}
		TreeMap<String, Object> testMap = JSONObject.parseObject(JsonData, TreeMap.class);
		String checkData = testMap.get("data").toString();
		TreeMap<String, Object> tescheckDataMap = JSONObject.parseObject(checkData, TreeMap.class);
		// 对签名的数据体做拼接
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : tescheckDataMap.keySet()) {
			stringBuilder.append(key).append("=").append(tescheckDataMap.get(key)).append("&");
		}
		String checkMd5Sign = stringBuilder.substring(0, stringBuilder.length() - 1);
		//进行MD5加密，并与原签名进行比对
		String signStr = MD5Encoding(checkMd5Sign);
		return StringUtils.equals(signStr, sign);
	}
	/**
	 * 根据字符串生成密钥24位的字节数组
	 *
	 * @param keyStr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] build3DesKey(String keyStr)
			throws UnsupportedEncodingException {
		byte[] key = new byte[24];
		byte[] temp = keyStr.getBytes("UTF-8");

		if (key.length > temp.length) {
			System.arraycopy(temp, 0, key, 0, temp.length);
		} else {
			System.arraycopy(temp, 0, key, 0, key.length);
		}
		return key;
	}
	
	/**
	 * 根据字符串生成密钥24位的字节数组
	 *
	 * @param keyStr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String build3DesKeyToStr(String keyStr)
			throws UnsupportedEncodingException {
		byte[] key = build3DesKey(keyStr);
		return new String(key);
	}
	public static void main(String[] args) {

		String data = "{\"pne\":\"18503041565\"}";
		String sign = getSign(data);
		String str = "{\"phoneNum\":\"15660707630\",\"password\":\"123456\",\"phoneType\":\"vivo511\",\"deviceId\":\"45121\"}";
		System.out.println(str);
		System.out.println(encryptMode(str, ConstantUtils.THREEDES_KEY));
 //System.out.println(decryptMode("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", ConstantUtils.THREEDES_KEY));
	}
}
