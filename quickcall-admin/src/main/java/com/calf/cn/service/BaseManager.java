package com.calf.cn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calf.cn.dao.BaseDao;

@Service
@Transactional
public class BaseManager {
	private static final Logger logger = LoggerFactory.getLogger(BaseManager.class);

	/**
	 * 新增
	 * @param entity 实体
	 * @return 返回受影响的行数
	 */
	public <T> int insert(T entity) {
		return baseDao.insertRow(entity);
	}

	/**
	 * 原生新增
	 * @param statementName
	 * @param entity
	 * @return 返回受影响的行数
	 */
	public <T> int insert(String statementName, T entity) {
		return baseDao.insertRow(statementName, entity);
	}

	/**
	 * 批量新增
	 * @param entities 实体集
	 * @return 返回受影响的行数
	 */
	public <T> int batchInsert(List<T> entities) {
		if (null == entities || 0 == entities.size()) {
			return 0;
		}
		return baseDao.insertRows(entities);
	}
	
	/**
	 * 批量原生新增
	 * @param entities 实体集
	 * @return 返回受影响的行数
	 */
	public <T> int batchInsert(String statementName,List<T> entities) {
		if (null == entities || 0 == entities.size()) {
			return 0;
		}
		return baseDao.insertRows(statementName, entities);
	}
	
	/**
	 * 批量删除
	 * @param entities 实体集
	 * @return 返回受影响的行数
	 */
	public <T> int batchDelete(List<T> entities) {
		if (null == entities || 0 == entities.size()) {
			return 0;
		}
		return baseDao.batchDelete(entities);
	}
	
	public <T> int batchDelete(String statementName, List<T> entities) {
		if (null == entities || 0 == entities.size()) {
			return 0;
		}
		return baseDao.batchDelete(statementName,entities);
	}

	/**
	 * 删除
	 * @param entity 实体
	 * @return 返回受影响的行数
	 */
	public <T> int delete(Class<T> entity, Object[] parameters) {
		if(null == parameters || parameters.length == 0){
			return 0;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < parameters.length; i++) {
			String value = String.valueOf(parameters[i]);
			if(StringUtils.isNotBlank(value)){
				params.put("arg"+i, value);
			}
		}
		return baseDao.deleteRow(entity, params);
	}
	
	/**
	 * 通过map删除
	 * @param entity
	 * @param parameters
	 * @return
	 */
	public <T> int delete(String statementName, Map<String,Object> parameters) {
		return baseDao.deleteRow(statementName, parameters);
	}
	
	/**
	 * mybatis原生删除
	 * @param statementName
	 * @param parameters
	 * @return
	 */
	public <T> int delete(String statementName, Object[] parameters) {
		if(null == parameters || parameters.length == 0){
			return 0;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < parameters.length; i++) {
			String value = String.valueOf(parameters[i]);
			if(StringUtils.isNotBlank(value)){
				params.put("arg"+i, value);
			}
		}
		return baseDao.deleteRow(statementName, params);
	}

	/**
	 * 更新
	 * @param entity 实体
	 * @return 返回受影响的行数
	 */
	public <T> int update(T entity) {
		return baseDao.updateRow(entity);
	}
	/**
	 * 批量更新
	 * @param statementName
	 * @param entities
	 * @return
	 */
	public <T> int batchUpdate(String statementName,List<T> entities) {
		if (null == entities || 0 == entities.size()) {
			return 0;
		}
		return baseDao.updateRows(statementName, entities);
	}
	
	/**
	 * 批量更新
	 * @param entities 实体集
	 * @return 返回受影响的行数
	 */
	public <T> int batchUpdate(List<T> entities) {
		if (null == entities || 0 == entities.size()) {
			return 0;
		}
		return baseDao.updateRows(entities);
	}
	
	/**
	 * mybatis 原生更新
	 * @param statementName
	 * @param parameters
	 * @return 返回受影响的行数
	 */
	public int update(String statementName, Map<String, Object> parameters) {
		return baseDao.updateRow(statementName, parameters);
	}
	
	/**
	 * mybatis 通过实体类更新
	 * @param statementName
	 * @param entity
	 * @return 返回受影响的行数
	 */
	public <T> int update(String statementName,T entity){
		return baseDao.updateRow(statementName,entity);
	}

	/**
	 * 根据实体标识查询
	 * @param entity 实体
	 * @return 实体
	 */
	@Transactional(readOnly = true)
	public <T> T get(Class<T> entity, Object[] parameters) {
		if(null == parameters || parameters.length == 0){
			return null;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < parameters.length; i++) {
			String value = String.valueOf(parameters[i]);
			if(StringUtils.isNotBlank(value)){
				params.put("arg"+i, value);
			}
		}
		return baseDao.getRow(entity, params);
	}
	
	/**
	 * mybatis根据实体原生标识查询
	 * @param entity 实体
	 * @return 实体
	 */
	@Transactional(readOnly = true)
	public <T> T get(String statementName, Object[] parameters) {
		if(null == parameters || parameters.length == 0){
			return null;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < parameters.length; i++) {
			String value = String.valueOf(parameters[i]);
			if(StringUtils.isNotBlank(value)){
				params.put("arg"+i, value);
			}
		}
		return baseDao.getRow(statementName, params);
	}
	
	/**
	 * 查询返回一行
	 * @param statementName
	 * @param parameters
	 * @return
	 */
	@Transactional(readOnly = true)
	public <T> T get(String statementName, Map<String, Object> parameters) {
		return baseDao.getRow(statementName, parameters);
	}
	
	/**
	 * 查询返回一行
	 * @param statementName
	 * @param parameters
	 * @return
	 */
	@Transactional(readOnly = true)
	public <T> T getNew(String statementName, Map<String, Object> parameters) {
		return baseDao.getRow2(statementName, parameters);
	}
	
	/**
	 * 查询
	 * @param entity 实体
	 * @return 实体集
	 */
	@Transactional(readOnly = true)
	public <T> List<T> query(Class<T> entity, Map<String, Object> parameters) {
		return baseDao.queryRows(entity, parameters);
	}
	
	@Transactional(readOnly = true)
	public <T> List<T> queryAll(Class<T> entity, Map<String, Object> parameters) {
		return baseDao.queryAllRows(entity, parameters);
	}
	
	/**
	 * 查询
	 * @param entity 实体
	 * @return 实体集
	 */
	@Transactional(readOnly = true)
	public <T> List<T> query(Class<T> entity) {
		return baseDao.queryRows(entity);
	}

	/**
	 * mybatis 原生查询
	 * @param statementName
	 * @param parameters
	 * @return 结果集合
	 */
	public <T> List<T> query(String statementName, Map<String, Object> parameters) {
		logger.info("=============开始查询菜单===========,请求参数为:{}",statementName);
		return baseDao.queryRows(statementName, parameters);
	}
	
	public <T> List<T> queryByString(String statementName, String parameters) {
		return baseDao.queryRowsByString(statementName, parameters);
	}
	
	public <T> Integer queryOne(Class<T> entity) {
		return baseDao.queryOne(entity);
	}
	
	/**
	 * 查询返回map
	 * @param statementName
	 * @param parameters
	 * @param key
	 * @return
	 */
	public <T> Map<String,T> queryMap(String statementName, Map<String, Object> parameters,String key) {
		return baseDao.queryMapRows(statementName, parameters,key);
	}
	
	/**
	 * 查询返回map,并且不清除参数
	 * @param statementName
	 * @param parameters
	 * @param key
	 * @return
	 */
	public <T> Map<String,T> queryMapNoClear(String statementName, Map<String, Object> parameters,String key) {
		return baseDao.queryNoClearMapRows(statementName, parameters,key);
	}

	/**
	 * 查询
	 * @param entity 实体
	 * @return 实体集
	 */
	@Transactional(readOnly = true)
	public <T> List<T> queryGroup(Class<T> entity) {
		return baseDao.queryGroupRows(entity);
	}
	
	@Autowired
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 根据实体标识查询
	 * @param entity 实体
	 * @return 实体
	 */
	@Transactional(readOnly = true)
	public <T> T getAccountId(Class<T> entity, Object[] parameters) {
		if(null == parameters || parameters.length == 0){
			return null;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < parameters.length; i++) {
			String value = String.valueOf(parameters[i]);
			if(StringUtils.isNotBlank(value)){
				params.put("arg"+i, value);
			}
		}
		return baseDao.getRow(entity, params);
	}
	
	/**
	 * mybatis根据实体原生标识查询
	 * @param entity 实体
	 * @return 实体
	 */
	@Transactional(readOnly = true)
	public <T> T getAccountId(String statementName, Object[] parameters) {
		if(null == parameters || parameters.length == 0){
			return null;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < parameters.length; i++) {
			String value = String.valueOf(parameters[i]);
			if(StringUtils.isNotBlank(value)){
				params.put("arg"+i, value);
			}
		}
		return baseDao.getRow(statementName, params);
	}
	
	/**
	 * 查询返回一行
	 * @param statementName
	 * @param parameters
	 * @return
	 */
	@Transactional(readOnly = true)
	public <T> T getAccountId(String statementName, Map<String, Object> parameters) {
		return baseDao.getRow(statementName, parameters);
	}
}
