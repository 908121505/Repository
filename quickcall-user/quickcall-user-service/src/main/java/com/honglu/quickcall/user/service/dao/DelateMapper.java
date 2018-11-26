package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.Delate;
import com.honglu.quickcall.user.facade.vo.DelateVO;

import java.util.List;

public interface DelateMapper {


   List<DelateVO> getAllDelateExcludeOther();

   int insertOtherReason(Delate delate);


}
