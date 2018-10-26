package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.ResourceConfig;

import java.util.List;

public interface ResourceConfigMapper {
    /**
     * 查询所有的6帧资源位的配置
     *
     * @return
     */
    List<ResourceConfig> selectAllResourceConfig();
}