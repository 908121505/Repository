package com.calf.cn.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;

public class SmsUtil {

	//private static final String sn = "SDK-BJR-010-00758";
	//private static final String password = "c4AAd-bc1A-";
    private static final String ext = "1";//贷款管家
    private static final String serviceURL = "http://sdk.entinfo.cn:8060/webservice.asmx";
    private static final String sn = "SDK-BJR-010-01105";// 序列号
    private static final String password = "57$-72e$-40";
    
    
    
    public static String getMD5(String sourceStr){
        String resultStr = "";
        try {
            byte[] temp = sourceStr.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(temp);
            // resultStr = new String(md5.digest());
            byte[] b = md5.digest();
            for (int i = 0; i < b.length; i++) {
                char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                        '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                char[] ob = new char[2];
                ob[0] = digit[(b[i] >>> 4) & 0X0F];
                ob[1] = digit[b[i] & 0X0F];
                resultStr += new String(ob);
            }
            return resultStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

   /* public static String getSms(String mobile, String content) {
        String result = "";
        String soapAction = "http://tempuri.org/mdSmsSend_u";
        //String soapAction = "http://entinfo.cn/mdsmssend";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        try {
            content = java.net.URLEncoder.encode(content + "【贷款管家】", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "-1";
        }
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<mdSmsSend_u xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + getMD5(sn + password) + "</pwd>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + content + "</content>";
        xml += "<ext>" + ext + "</ext>";
        xml += "<stime>" + "" + "</stime>";
        xml += "<rrid>" + ""+ "</rrid>";
        xml += "</mdSmsSend_u>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        ByteArrayOutputStream bout = null;
        OutputStream out = null;
        InputStreamReader isr = null;
        BufferedReader in = null;
        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes("GBK"));
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            isr = new InputStreamReader(httpconn
                    .getInputStream());
            in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern.compile("<mdsmssendResult>(.*)</mdsmssendResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        } finally {
            try {
                if (bout != null) {
                    bout.close();
                }
                if (out != null) {
                    out.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
    
    /*
     * 方法名称：mdSmsSend_u
     * 功    能：发送短信 ,传多个手机号就是群发，一个手机号就是单条提交
     * 参    数：mobile,content,ext,stime,rrid(手机号，URL_UT8编码内容，扩展码，定时时间，唯一标识)
     * 返 回 值：唯一标识，如果不填写rrid将返回系统生成的
     */
    public static String mdSmsSend_u(String mobile, String content) {
        String result = "";
        String soapAction = "http://tempuri.org/mdSmsSend_u";
        try {
            content = java.net.URLEncoder.encode("【琰璟网络】"+content , "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "-1";
        }
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<mdSmsSend_u xmlns=\"http://tempuri.org/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + getMD5(sn + password) + "</pwd>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + content + "</content>";
        xml += "<ext>" + ext + "</ext>";
        xml += "<stime>" + "" + "</stime>";
        xml += "<rrid>" + "" + "</rrid>";
        xml += "</mdSmsSend_u>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";
        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes("GBK"));
            //如果您的系统是utf-8,这里请改成bout.write(xml.getBytes("GBK"));

            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern.compile("<mdSmsSend_uResult>(.*)</mdSmsSend_uResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    
 public static void main(String[] args) {
	 mdSmsSend_u("18217583747", "你好帅");
	 
}
  

}
