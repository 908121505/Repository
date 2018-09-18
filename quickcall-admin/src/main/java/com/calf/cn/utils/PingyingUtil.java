package com.calf.cn.utils;

import net.sourceforge.pinyin4j.PinyinHelper;


/**
 * 汉语拼音相关工具类
 *
 * @author len.song
 * @date 2018/05/30
 */
public class PingyingUtil {
    public static String ToPinYinString(String str){

        StringBuilder sb=new StringBuilder();
        String[] arr=null;

        for(int i=0;i<str.length();i++){
            arr= PinyinHelper.toHanyuPinyinStringArray(str.charAt(i));
            if(arr!=null && arr.length>0){
                for (String string : arr) {
                    sb.append(string);
                }
            }
        }

        return sb.toString();
    }
}
