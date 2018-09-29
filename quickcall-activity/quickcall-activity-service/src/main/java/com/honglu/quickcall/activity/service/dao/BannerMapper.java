package com.honglu.quickcall.activity.service.dao;

import com.honglu.quickcall.activity.facade.entity.Banner;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Banner 表数据库Dao层
 *
 * @author duanjun
 * @date 2018-09-21 18:40
 */
@Repository
public interface BannerMapper {

    /**
     * 查询Banner信息
     *
     * @param banner
     * @return
     */
    List<Banner> queryBannerInfo(Banner banner);
}
