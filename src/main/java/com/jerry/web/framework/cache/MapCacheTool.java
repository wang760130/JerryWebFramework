package com.jerry.web.framework.cache;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import java.util.concurrent.ConcurrentHashMap;

import java.util.Map;


public class MapCacheTool {

	/**
	 * 
	 * 开发模式
	 * 
	 */

	private boolean isdev = false;

	private static String ERROR_SET = "VirtualCache缓存数据异常:key=";

	private static String ERROR_GET = "VirtualCache取出数据异常:key=";

	/**
	 * 
	 * 虚拟缓存
	 * 
	 */

	private Map<String, Object> cache = new ConcurrentHashMap<String, Object>();

	/**
	 * 
	 * 过期时间
	 * 
	 */

	private Map<String, Long> tasks = new ConcurrentHashMap<String, Long>();

	public MapCacheTool(boolean isdev){

     this.isdev = isdev;

 }

	public MapCacheTool(){

      

 }

	/**
	 * 
	 * 深度克隆
	 * 
	 */

	private Object clone(String key, Object obj, boolean isSet) {

		if (obj == null)

			return null;

		ByteArrayOutputStream bo = null;

		ObjectOutputStream oo = null;

		ByteArrayInputStream bi = null;

		ObjectInputStream oi = null;

		Object value = null;

		try {

			bo = new ByteArrayOutputStream();

			oo = new ObjectOutputStream(bo);

			oo.writeObject(obj);

			bi = new ByteArrayInputStream(bo.toByteArray());

			oi = new ObjectInputStream(bi);

			value = oi.readObject();

		} catch (Exception e) {

			this.printError(e, key, isSet);

		} finally {

			if (oo != null)

				try {

					oo.close();

				} catch (Exception e) {

					this.printError(e, key, isSet);

				}

			if (oi != null)

				try {

					oi.close();

				} catch (Exception e) {

					this.printError(e, key, isSet);

				}

		}

		return value;

	}

	/**
	 * 
	 * 日志输出
	 * 
	 */

	private void printError(Exception e, String key, boolean isSet) {

		if (isSet)

			System.err.println(ERROR_SET + key);

		else

			System.err.println(ERROR_GET + key);

		if (this.isdev)

			e.printStackTrace();

	}

	/**
	 * 
	 * 存入缓存
	 * 
	 */

	public void set(String key, Object value, long timeout) {

		this.delete(key);

		this.cache.put(key, this.clone(key, value, true));

		if (timeout > 0l)

			this.tasks.put(key, timeout * 1000l + System.currentTimeMillis());

	}

	/**
	 * 
	 * 获取缓存
	 * 
	 */

	public Object get(String key) {

		Long timeout = this.tasks.get(key);

		if (timeout != null && timeout <= System.currentTimeMillis())

			this.delete(key);

		return this.clone(key, this.cache.get(key), false);

	}

	/**
	 * 
	 * 删除缓存
	 * 
	 */

	public void delete(String key) {

		this.cache.remove(key);

		this.tasks.remove(key);

	}

}
