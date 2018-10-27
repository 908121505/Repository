package com.honglu.quickcall.common.core.generator;

/**
 * Created by len.song on 2018-02-23.
 */
public class RandomWorker {
    public static String generate6Length(){
        return (int)((Math.random()*9+1)*100000) + "";
    }

    public static void main(String[] args) {
        System.out.println(generate6Length());
    }
}



