package com.honglu.quickcall.user.service.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.honglu.quickcall.user.facade.entity.Person;

@Repository
public class PersonMapper {
	@Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查看个人信息
     */
    public Person selectPersonInfo(String userId){
        Person p= sqlSessionTemplate.selectOne("Person.selectPersonInfo",userId);
        return p;
    }
}
