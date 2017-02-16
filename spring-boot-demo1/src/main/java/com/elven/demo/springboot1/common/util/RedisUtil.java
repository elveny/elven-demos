package com.elven.demo.springboot1.common.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@Component("redisUtil")
public class RedisUtil {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * Strings - 字符串
	 * 获取ValueOperations，若与本工具类中未封装方法，可以直接获取此对象进行相应操作
	 * @return
	 */
	public ValueOperations<Serializable, Object> getValueOperations() {
		return redisTemplate.opsForValue();
	}

	/**
	 * Hashes - 哈希值
	 * 获取HashOperations，若与本工具类中未封装方法，可以直接获取此对象进行相应操作
	 * @return
	 */
	public HashOperations<Serializable, Object, Object> getHashOperations() {
		return redisTemplate.opsForHash();
	}

	/**
	 * Lists - 列表
	 * 获取ListOperations，若与本工具类中未封装方法，可以直接获取此对象进行相应操作
	 * @return
	 */
	public ListOperations<Serializable, Object> getListOperations() {
		return redisTemplate.opsForList();
	}

	/**
	 * Sets - 集合
	 * 获取SetOperations，若与本工具类中未封装方法，可以直接获取此对象进行相应操作
	 * @return
	 */
	public SetOperations<Serializable, Object> getSetOperations() {
		return redisTemplate.opsForSet();
	}

	/**
	 * ZSets - 有序集合
	 * 获取ZSetOperations，若与本工具类中未封装方法，可以直接获取此对象进行相应操作
	 * @return
	 */
	public ZSetOperations<Serializable, Object> getZSetOperations() {
		return redisTemplate.opsForZSet();
	}
	
	/**
	 * Strings - 字符串
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		Object result = null;
		result = getValueOperations().get(key);
		return result;
	}

	/**
	 * Strings - 字符串
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {
		boolean result = false;
		getValueOperations().set(key, value);
		result = true;
		return result;
	}

	/**
	 * Hashes - 哈希值
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object get(String key, Object hashKey) {
		Object result = null;
		result = getHashOperations().get(key, hashKey);
		return result;
	}
	
	/**
	 * Hashes - 哈希值
	 * @param key
	 * @param hashKey
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object hashKey, Object value) {
		boolean result = false;
		getHashOperations().put(key, hashKey, value);
		result = true;
		return result;
	}
	
	/**
	 * Hashes - 哈希值
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Map<Object, Object> value) {
		boolean result = false;
		getHashOperations().putAll(key, value);
		result = true;
		return result;
	}
	
	/**
	 * Lists - 列表
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean leftPush(String key, Object value){
		boolean result = false;
		getListOperations().leftPush(key, value);
		result = true;
		return result;
	}
	
	/**
	 * Lists - 列表
	 * @param key
	 * @param values
	 * @return
	 */
	public boolean leftPushAll(String key, Collection<Object> values){
		boolean result = false;
		getListOperations().leftPushAll(key, values);
		result = true;
		return result;
	}
	
	/**
	 * Lists - 列表
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean rightPush(String key, Object value){
		boolean result = false;
		getListOperations().rightPush(key, value);
		result = true;
		return result;
	}
	
	/**
	 * Lists - 列表
	 * @param key
	 * @param values
	 * @return
	 */
	public boolean rightPushAll(String key, Collection<Object> values){
		boolean result = false;
		getListOperations().rightPushAll(key, values);
		result = true;
		return result;
	}
	
	/**
	 * Lists - 列表
	 * @param key
	 * @return
	 */
	public Object leftPop(String key){
		return getListOperations().leftPop(key);
	}
	
	/**
	 * Lists - 列表
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public Object leftPop(String key, long timeout, TimeUnit unit){
		return getListOperations().leftPop(key, timeout, unit);
	}
	
	/**
	 * Lists - 列表
	 * @param key
	 * @return
	 */
	public Object rightPop(String key){
		return getListOperations().rightPop(key);
	}
	
	/**
	 * Lists - 列表
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public Object rightPop(String key, long timeout, TimeUnit unit){
		return getListOperations().rightPop(key, timeout, unit);
	}
}
