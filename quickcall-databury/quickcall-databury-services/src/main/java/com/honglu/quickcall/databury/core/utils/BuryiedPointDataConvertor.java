package com.honglu.quickcall.databury.core.utils;

import java.util.HashMap;
import java.util.Map;

public class BuryiedPointDataConvertor {
    public static Map<String,Object> newInstanceEvent(String key, String realUserId, Map<String,Object> event){
        Map<String,Object> params = new HashMap<>();
        params.put("key",key);
        params.put("realUserId",realUserId);
        params.put("event",event);
        return params;
    }
    public static Map<String,Object> newInstanceEventLogin(String key, String realUserId, Map<String,Object> event,String virUserId){
        Map<String,Object> params = new HashMap<>();
        params.put("key",key);
        params.put("realUserId",realUserId);
        params.put("event",event);
        params.put("virUserId", virUserId);
        return params;
    }

    public static Map<String,Object> newInstanceAll(String key, String realUserId, Map<String,Object> event, Map<String,Object> user,String virUserId){
        Map<String,Object> params = newInstanceEvent(key,realUserId,event);
        params.put("user",user);
        params.put("virUserId", virUserId);
        return params;
    }
}
