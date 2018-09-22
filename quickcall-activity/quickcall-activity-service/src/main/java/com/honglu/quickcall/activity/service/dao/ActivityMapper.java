package com.honglu.quickcall.activity.service.dao;

import com.honglu.quickcall.activity.facade.entity.Activity;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author liuqiu
 */
@Repository
public class ActivityMapper {


    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询礼物表全部信息
     * @param map
     * @return
     */
    public List<Activity> find(Map<String, Object> map){

        List<Activity> activities = sqlSessionTemplate.selectList("Activity.activity_all", map);

        return activities;
    }

    /**
     * 查询数据数量
     * @return
     */
    public int findCount(){

        int a = sqlSessionTemplate.selectOne("Activity.activity_all_count");

        return a;
    }

    /**
     * 根据编号查询信息
     * @param
     * @return
     */
    public Activity findById(Activity activity){

        Activity act = sqlSessionTemplate.selectOne("Activity.selectByKey", activity);

        return act;
    }

	public Activity selectByActivityId(String activityId) {
		 Activity act = sqlSessionTemplate.selectOne("Activity.selectByActivityId", activityId);
		 return act;
	}

    public void update(Activity activity){
            sqlSessionTemplate.update("Activity.update", activity);
    }

	public List<Activity> selectByReturnMoney() {
		List<Activity> activitys = sqlSessionTemplate.selectList("Activity.selectByReturnMoney");
		return activitys;
	}


}