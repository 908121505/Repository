package com.calf.cn.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 数据库级查询
 * @author guixin
 *
 */
@Repository
public class BaseDao extends SqlSessionDaoSupport {

	/**
	 * 新增
	 * @param entity 实体
	 * @return 返回受影响的行数
	 */
	public <T> int insertRow(T entity) {
		return getSqlSession().insert(entity.getClass().getSimpleName() + ".insert", entity);
	}
	public <T> int insertRow(String statementName, T entity) {
		return getSqlSession().insert(statementName, entity);
	}

	
	/**
	 * 批量新增
	 * @param entities 实体集
	 * @return 返回受影响的行数
	 */
	public <T> int insertRows(List<T> entities) {
		return getSqlSession().insert(entities.get(0).getClass().getSimpleName() + ".batchInsert",
				entities);
	}
	public <T> int insertRows(String statementName, List<T> entities) {
		return getSqlSession().insert(statementName,entities);
	}
	
	
	/**
	 * 批量删除
	 * @param entities 实体集
	 * @return 返回受影响的行数
	 */
	public <T> int batchDelete(List<T> entities) {
		return getSqlSession().delete(entities.get(0).getClass().getSimpleName() + ".batchDelete",
				entities);
	}
	
	public <T> int batchDelete(String statementName,List<T> entities) {
		return getSqlSession().delete(statementName, entities);
	}
	
	
	/**
	 * 删除
	 * @param entity 实体
	 * @return 返回受影响的行数
	 */
	public <T> int deleteRow(Class<T> entity, Map<String, Object> parameters) {
		try{
			return getSqlSession().update(entity.getSimpleName() + ".delete", parameters);
		} finally {
			parameters.clear();
			parameters = null;
		}
	}
	public <T> int deleteRow(String statementName, Map<String, Object> parameters) {
		try{
			return getSqlSession().delete(statementName, parameters);
		} finally {
			parameters.clear();
			parameters = null;
		}
	}

	
	/**
	 * 更新
	 * @param entity 实体
	 * @return 返回受影响的行数
	 */
	public <T> int updateRow(T entity) {
		return getSqlSession().update(entity.getClass().getSimpleName() + ".update", entity);
	}
	public int updateRow(String statementName, Map<String, Object> parameters) {
		return getSqlSession().update(statementName, parameters);
	}
	/**
	 * 批量更新
	 * @param entities
	 * @return
	 */
	public <T> int updateRows(List<T> entities) {
		return getSqlSession().update(entities.get(0).getClass().getSimpleName() + ".batchUpdate",
				entities);
	}
	public <T> int updateRows(String statementName, List<T> entities) {
		return getSqlSession().update(statementName,entities);
	}
	
	/**
	 * 根据实体标识查询
	 * @param entity 实体
	 * @return 实体
	 */
	@SuppressWarnings("unchecked")
	public <T> T getRow(Class<T> entity, Map<String, Object> parameters) {
		try{
			return (T) getSqlSession().selectOne(entity.getSimpleName() + ".get", parameters);
		} finally {
			parameters.clear();
			parameters = null;
		}
	}
	@SuppressWarnings("unchecked")
	public <T> T getRow(String statementName, Map<String, Object> parameters) {
		try{
			return (T) getSqlSession().selectOne(statementName, parameters);
		} finally {
			parameters.clear();
			parameters = null;
		}
	}
	@SuppressWarnings("unchecked")
	public <T> T getRow2(String statementName, Map<String, Object> parameters) {
		try{
			return (T) getSqlSession().selectOne(statementName, parameters);
		} finally {
			
		}
	}


	/**
	 * 查询实体集
	 * @param entity 实体
	 * @return 实体集
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryRows(Class<T> entity, Map<String, Object> parameters) {
		return getSqlSession().selectList(entity.getSimpleName() + ".query", parameters);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> queryAllRows(Class<T> entity, Map<String, Object> parameters) {
		return getSqlSession().selectList(entity.getSimpleName() + ".queryAll", parameters);
	}
	/**
	 * 查询实体集
	 * @param entity 实体
	 * @return 实体集
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryRows(Class<T> entity) {
		return getSqlSession().selectList(entity.getSimpleName() + ".queryList");
	}
	
	/**
	 * 查询实体集
	 * @param entity 实体
	 * @return 实体集
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryGroupRows(Class<T> entity) {
		return getSqlSession().selectList(entity.getSimpleName() + ".queryGroup");
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> queryRows(String statementName, Map<String, Object> parameters) {
		return getSqlSession().selectList(statementName, parameters);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> queryRowsByString(String statementName, String parameters) {
		return getSqlSession().selectList(statementName, parameters);
	}
	
	@SuppressWarnings("unchecked")
	public <T> Integer queryOne(Class<T> entity) {
		return (Integer) getSqlSession().selectOne(entity.getSimpleName() + ".queryOne");
	}
	
	
	/**
	 * 查询返回map
	 * @param statementName 命名空间+id
	 * @param parameters 请求参数
	 * @param key 作为主见的key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> queryMapRows(String statementName, Map<String, Object> parameters,String key) {
		try{
			return getSqlSession().selectMap(statementName, parameters, key);
		} finally {
			parameters.clear();
			parameters = null;
		}
	}
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> queryNoClearMapRows(String statementName, Map<String, Object> parameters,String key) {
		return getSqlSession().selectMap(statementName, parameters, key);
	}
}
