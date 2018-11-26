package com.honglu.quickcall.user.service.util;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * IP工具类
 *
 * @author xiangping
 * @date 2018-11-23 15:04
 */
public class IpUtils {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    /**
     * 获取ip具体信息
     *
     * @param IP
     * @return
     */
    public static IpMessage getIpDetailMessage(String IP) {
        IpMessage ipMessage = new IpMessage();
        try {
            String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + IP);

            JSONObject obj = JSONObject.fromObject(str);
            JSONObject obj2 = (JSONObject) obj.get("data");
            Integer code = (Integer) obj.get("code");
            if (code == 0) {
                ipMessage.setIp((String) obj2.get("ip"));
                ipMessage.setCountry((String) obj2.get("country"));
                if (StringUtils.isNotBlank(ipMessage.getCountry())){
                    ipMessage.setCode(code);
                    ipMessage.setDesc("获取IP信息正常");
                }else{
                    ipMessage.setCode(-1);
                    ipMessage.setDesc("IP地址有误");
                }
                ipMessage.setArea((String) obj2.get("area"));
                ipMessage.setRegion((String) obj2.get("region"));
                ipMessage.setCity((String) obj2.get("city"));
                ipMessage.setIsp((String) obj2.get("isp"));
                ipMessage.setCountryId((String) obj2.get("country_id"));
                ipMessage.setAreaId((String) obj2.get("area_id"));
                ipMessage.setRegionId((String) obj2.get("region_id"));
                ipMessage.setCityId((String) obj2.get("city_id"));
                ipMessage.setIspId((String) obj2.get("isp_id"));
            } else {
                ipMessage.setCode(code);
                ipMessage.setDesc("IP地址有误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取ip信息异常："+e.getMessage());
        }
        return ipMessage;

    }

    /**
     * 访问ip库
     *
     * @param urlStr
     * @return
     */
    public static String getJsonContent(String urlStr) {
        try {
            // 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url
                    .openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200) {
                return ConvertStream2Json(httpConn.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 结果解析
     *
     * @param inputStream
     * @return
     */
    private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        //内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        //将输入流转到输出流
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /**
     * 获取IP网段
     *
     * @return
     */
    private static int[][] getIPRange() {
        int[][] range =
                {
                        {607649792, 608174079}, {1038614528, 1039007743},
                        {1783627776, 1784676351}, {2035023872, 2035154943},
                        {2078801920, 2079064063}, {-1950089216, -1948778497},
                        {-1425539072, -1425014785}, {-1236271104, -1235419137},
                        {-770113536, -768606209}, {-569376768, -564133889},
                };
        return range;
    }


    /**
     * 随机生成国内IP
     *
     * @return
     */
    public static String getChinaIp() {
        int[][] range = getIPRange();
        Random random = new Random();
        int index = random.nextInt(10);
        int ipNumber = range[index][0] + new Random().nextInt(range[index][1] - range[index][0]);
        int[] b = new int[4];
        String ip;
        b[0] = ((ipNumber >> 24) & 0xff);
        b[1] = ((ipNumber >> 16) & 0xff);
        b[2] = ((ipNumber >> 8) & 0xff);
        b[3] = (ipNumber & 0xff);
        ip = Integer.toString(b[0]) + "."
                + Integer.toString(b[1]) + "."
                + Integer.toString(b[2]) + "."
                + Integer.toString(b[3]);
        return ip;
    }
}