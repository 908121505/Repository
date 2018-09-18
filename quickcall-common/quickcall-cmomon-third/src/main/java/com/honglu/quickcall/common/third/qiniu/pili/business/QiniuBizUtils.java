package com.honglu.quickcall.common.third.qiniu.pili.business;

import cn.jiguang.commom.utils.StringUtils;
import com.honglu.quickcall.common.constants.PropertiesConstant;
import com.honglu.quickcall.common.third.qiniu.pili.Client;
import com.honglu.quickcall.common.third.qiniu.pili.Hub;
import com.honglu.quickcall.common.third.qiniu.pili.PiliException;
import com.honglu.quickcall.common.third.qiniu.pili.Stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by len.song on 2017-12-14.
 */
public class QiniuBizUtils {

    /**
     * 获取闪配推流地址
     * @param streamKey
     * @return
     */
    public static String getPairPushUrl(String streamKey){
        //创建直播流 1，初始化  2，判断流是否存在   2,1 不存在时创建   2,1 存在时直接获取   3，返回流url
        Client cli = new Client(PropertiesConstant.QiniuAccessKey,PropertiesConstant.QiniuSecretKey);

        //初始化Hub
        Hub hub = cli.newHub(PropertiesConstant.QiniuHubName);

        try {
            hub.get(streamKey);
        }catch (PiliException e){
            if (e.isNotFound()) {
                try {
                    hub.create(streamKey);
                }catch (PiliException pe){
                    pe.printStackTrace();
                }
            }else{
               e.printStackTrace();
            }
        }

        return cli.RTMPPublishURL(PropertiesConstant.QiniuPublish, PropertiesConstant.QiniuHubName, streamKey, PropertiesConstant.ExpireAfterSeconds);
    }

    /**
     * 获取等待连接的直播key列表
     * @param count
     * @param maker
     * @return
     *      Map  key：result      value: Map(流id值,直播地址)
     *      Map  key: #maker#     value: String
     */
    public static Map<String,Object> getReadPairList(Integer count, String maker){
        //初始化client
        Client cli = new Client(PropertiesConstant.QiniuAccessKey,PropertiesConstant.QiniuSecretKey);
        //初始化Hub
        Hub hub = cli.newHub(PropertiesConstant.QiniuHubName);
        Map<String,Object> result = new HashMap<>();
        try{
            List<String>  keysList = printArrary( hub.listLive("", count, maker).keys);
            if(keysList.size() ==0 && !StringUtils.isEmpty(maker)){
                keysList = printArrary(hub.listLive("", count, "").keys);
            }
            Map<String,String> object = new HashMap();
            for (int i = 0; i < keysList.size(); i++) {
                object.put(keysList.get(i),cli.RTMPPlayURL(PropertiesConstant.QiniuLiveRtmp, PropertiesConstant.QiniuHubName, keysList.get(i)));
            }

            result.put("#maker#",maker);
            result.put("result",object);

        }catch (PiliException e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 判断闪配直播流状态
     * @param qiniuKey
     * @return  true: 空闲状态   false：占线忙碌状态
     */
    public static boolean getLiveStatus(String qiniuKey){
        Client cli = new Client(PropertiesConstant.QiniuAccessKey,PropertiesConstant.QiniuSecretKey);
        Hub hub = cli.newHub(PropertiesConstant.QiniuHubName);

        boolean isOnline = false;
        try{
            if(hub.get(qiniuKey).liveStatus() != null){
                isOnline = true;
            }
        }catch (PiliException e){
            e.printStackTrace();
        }

        return isOnline;
    }



    //转换数据
    private static List<String> printArrary(Object[] arr){
        StringBuilder sb = new StringBuilder();
        List<String> str = new ArrayList();
        for (int i=0; i<arr.length;i++){
           str.add(arr[i].toString());
        }
        return str;
    }
}
