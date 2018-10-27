package com.honglu.quickcall.account.web.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapUtils {
    public static Map<String, String> GetInfoFromALiPay(Map<String,String> maps) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = maps;
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i]
                        + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }

    public static String getWechatParams(HttpServletRequest request){
        BufferedReader reader;
        String line = "";
        StringBuffer inputString = new StringBuffer();

        try{
            reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            if (reader != null) {
                reader.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return inputString.toString();
    }
}
