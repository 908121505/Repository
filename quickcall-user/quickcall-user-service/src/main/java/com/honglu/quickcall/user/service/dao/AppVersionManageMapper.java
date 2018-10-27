package com.honglu.quickcall.user.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honglu.quickcall.user.facade.entity.AppVersionManage;

public interface AppVersionManageMapper {

	List<AppVersionManage> findAppVersionInfo(@Param("appType") String appType, @Param("state") Integer state,
			@Param("tag") String tag);
}
