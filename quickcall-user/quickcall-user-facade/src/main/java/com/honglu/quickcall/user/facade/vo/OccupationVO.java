package com.honglu.quickcall.user.facade.vo;


/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：职业返回参数
 * @Package: com.honglu.quickcall.user.facade.vo 
 * @author: chenliuguang   
 * @date: 2018年10月9日 下午1:05:55
 */
public class OccupationVO {
    private Integer id;

    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

}