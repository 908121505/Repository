package com.honglu.quickcall.common.api.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DES3UtilsToll {

    // 定义加密算法，DESede即3DES
    private static final String Algorithm = "DESede";

    private DES3UtilsToll() {
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
     * @param src 源数据的字节数组
     * @return
     */
    public static String encryptMode(String src, String key) {
        try {
            byte[] targetSrc = src.getBytes("utf-8");
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
     * @param src 密文的字节数组
     * @return
     */
    public static String decryptMode(String src, String key) {
        try {
            byte[] targetSrc = Base64Util.decode(src);
            SecretKey deskey = new SecretKeySpec(build3DesKey(key),
                    Algorithm);
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return new String(c1.doFinal(targetSrc), Charset.forName("UTF-8"));
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
     *
     * @param jsonStr 数据体的JSON格式的字符串,不能是JSONARRAY类型字符串
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
     *
     * @param JsonData JSON格式的字符串
     * @param sign    原签名字符串
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

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type","application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        String user_local = "http://192.168.1.209:8080/userCenter";
        String credit_local = "http://192.168.1.209:8081/creditCenter";
        String loan_local = "http://192.168.1.209:8082/loanCenter";
        String user_test = "http://testuser.xnsudai.com/userCenter";
        String credit_test = "http://testcredit.xnsudai.com/creditCenter";
        String loan_test = "http://testloan.xnsudai.com/loanCenter";
        ///
        String loan_test2 = " http://testcommunity.xnsudai.com/communityCenter/articleloan/articleDetailsTypeList";
        String loan_test3 = " http://testcommunity.xnsudai.com/communityCenter/articleloan/articleTypeDetail";
        String loan_test4= "http://testcommunity.xnsudai.com/communityCenter/articleloan/queryArticleDouble";
        String loan_test5 = "http://testcommunity.xnsudai.com/communityCenter/articlePraiseloan/articlePraiseTypeThumbs";
        String loan_test7 = " http://testcommunity.xnsudai.com/communityCenter/articleloan/articlePublishType";
        String loan_test6 = "http://testcommunity.xnsudai.com/communityCenter/articlePraiseloan/articlePraiseTypeCancel";
        String loan_test8= "http://testcommunity.xnsudai.com/communityCenter/articleCommentPraiseloan/articleCommentPraiseTypeCancelH5";
        String loan_test9= "http://testloan.xnsudai.com/loanCenter";
        Map<String, Object> packet = new HashMap<>();
        //注册获取验证码
//        String url = user_local + "/account/getSmsCode";
//        String url = user_test + "/account/getSmsCode";
//		packet.put("phoneNum","17212341234");
//		packet.put("codeType","2");

//		忘记密码获取验证码
//        String url = user_local + "/account/getSmsCode";
//        String url = user_test + "/account/getSmsCode";
//		packet.put("phoneNum","13127905469");
//		packet.put("codeType","1");

        //注册
//        String url = user_local + "/account/register";
//        String url = user_test + "/account/register";
//		packet.put("phoneNum", "13127905469");
//		String password = MD5Util.MD5("123456");
//		packet.put("password",password);
//		packet.put("verifyCode", "627529");
//		packet.put("source", "1");
//		packet.put("phoneType", "iphone6");
//		packet.put("deviceId", "A100004FDB8B88");

        //登陆
//        String url = user_local + "/account/login";
//        String url = user_test + "/account/login";
//		packet.put("phoneNum", "13127905469");
//		String password = MD5Util.MD5("12345678");
//        packet.put("password",password);
//		packet.put("phoneType", "iphone6");
//		packet.put("deviceId", "A100004FDB8B88");
//
        //登出
//        String url = user_local + "/account/logout";
//        String url = user_test + "/account/logout";
//		packet.put("token", "b56e6fa5af648ea7b65e152ba2732d00");

        //修改密码
//        String url = user_local + "/account/modifyPassword";
//        String url = user_test + "/account/modifyPassword";
//		packet.put("phoneNum", "13127905469");
//		String password = MD5Util.MD5("12345678");
//        packet.put("password",password);
//		packet.put("source", "1");
//		packet.put("token", "b56e6fa5af648ea7b65e152ba2732d00");

        //忘记密码
//        String url = user_local + "/account/modifyPassword";
//        String url = user_test + "/account/modifyPassword";
//		packet.put("verifyCode", "556759");
//		packet.put("phoneNum", "13127905469");
//		String password = MD5Util.MD5("1234567");
//		packet.put("password",password);
//		packet.put("source", "2");

        //我的消息
//        String url = user_local + "/message/list";
//        String url = user_test + "/message/list";
//        packet.put("timestamp", "1494038081");

        //app版本更新
//        String url = user_local + "/appVersion/query";
//        String url = user_test + "/appVersion/query";
//        packet.put("appType", "1");
//        packet.put("versionType", "3");

        //帮助中心
//        String url = user_local + "/help/center";
//        String url = user_test + "/help/center";
//        packet.put("timestamp", "1494038081");

        //计算器下拉列表
//        String url = loan_local + "/loanProduct/list";
//        String url = loan_test + "/loanProduct/list";
//        packet.put("timestamp", "1494038081");


		//信用卡首页
//        String url = credit_local + "/credit/list";
//        String url = credit_test + "/credit/list";
//        packet.put("timestamp", "1494038081");

        //信用卡更多
//        String url = credit_local + "/credit/more";
        String url = credit_test + "/credit/more";
        packet.put("timestamp", "1494038081");
		packet.put("page","1");
		packet.put("pageSize","18");

        String str = "{\"data\":" + JSON.toJSONString(packet) + ",\"sign\":\"" + getSign(JSON.toJSONString(packet)) + "\"}";
        String encryptStr = encryptMode(str, ConstantUtils.THREEDES_KEY);
        System.out.println("明文请求参数为:" + str);
        System.out.println("密文请求参数为:" + encryptStr);

        String json = sendPost(url, encryptStr);
        if (StringUtils.isNotBlank(json)){
            HashMap<String, Object> result = JSON.parseObject(json, HashMap.class);
            if (result.get("returnCode").toString().equals("200")){
                System.out.println("data信息为:" +decryptMode(result.get("data").toString(), ConstantUtils.THREEDES_KEY));
                System.out.println("errorMsg信息为:" + result.get("errorMsg").toString());
            }else{
                System.out.println("错误信息为:" + result.get("errorMsg").toString());
            }
        }else {
            System.out.println("这是一个错误的请求");
        }
    }

}

