package com.calf.cn.utils;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MyJedisPool {

	private static JedisPool pool = null;
	private static int maxActive;
	private static int maxIdle;
	private static long maxWait; 
	private static String ip;
	private static int port;
	private static final String pass;
	private static int expire;
	private static boolean testOnBorrow;
	static {
		ResourceBundle rb = ResourceBundle.getBundle("redis");
		maxActive = Integer.valueOf(rb.getString("maxIdle"));
		maxIdle = Integer.valueOf(rb.getString("maxIdle"));
		maxWait = Long.valueOf(rb.getString("maxWait"));
		ip = rb.getString("ip");
		pass = rb.getString("pass");
		port = Integer.valueOf(rb.getString("port"));
		expire = Integer.valueOf(rb.getString("expire"));
		testOnBorrow = Boolean.valueOf(rb.getString("testOnBorrow"));
	}
	
	/**
	 * 构建redis连接池
	 *
	 * @return JedisPool
	 */
	public static JedisPool getPool() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			config.setMaxActive(maxActive);
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(maxIdle);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWait(maxWait);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(testOnBorrow);
			
			pool = new JedisPool(config, ip, port, expire, pass);
		}
		return pool;
	}

	/**
	 * 返还到连接池
	 * 
	 * @param pool
	 * @param redis
	 */
	public static void returnResource(JedisPool pool, Jedis redis) {
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		String value = null;

		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return value;
	}

	/**
	 * 存储数据
	 * 
	 * @param key
	 * @param value
	 */
	public static String put(String key, String value) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			value = jedis.set(key, value);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return value;
	}
	
	public static String hset(String key, String field, String value) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.hset(key, field, value);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return value;
	}

	/**
	 * 存储数据(含失效时间)
	 * 
	 * @param key
	 * @param value
	 * @param second
	 */
	public static String putInTime(String key, String value, int second) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			value = jedis.set(key, value);
			jedis.expire(key, second);
		} catch (Exception e) {
			// 释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(pool, jedis);
		}
		return value;
	}

	/**
	 * 通过Key来移除缓存数据
	 * 
	 * @param key
	 * @return
	 */
	public static long delete(String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = MyJedisPool.getPool();
			jedis = pool.getResource();
			if (jedis.exists(key)) {
				return jedis.del(key);
			}
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return 0;
	}
	/**
	 * 通过Key来移除缓存数据
	 * 
	 * @param key
	 * @return
	 */
	public long remove(String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = MyJedisPool.getPool();
			jedis = pool.getResource();
			if (jedis.exists(key)) {
				return jedis.del(key);
			}
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return 0;
	}
	
	/**
	 * 
	 * @Description 批量删除key
	 * @param key
	 * @return
	 */
	public static long delAll(String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = MyJedisPool.getPool();
			jedis = pool.getResource();
			Set<String> keys = jedis.keys(key + "*");
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){  
	            jedis.del(it.next());  
	        }  
			return keys.size();
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return 0;
	}

	public static Set<String> getKeys(String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		pool = MyJedisPool.getPool();
		jedis = pool.getResource();
		Set<String> keys = jedis.keys(key);
		return keys;
	}

}
