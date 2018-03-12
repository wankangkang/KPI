/**
 * FileName:RedisClient.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月15日下午9:42:52
 ********************************
 *Modifycation History:
 *date:2018年1月15日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Bill
 *
 */
@Component
public class RedisPoolClient {
	  
	    @Autowired
	    @Qualifier("jedis.pool")
	    private JedisPool jedisPool; 
	    
	    /**
		 * 从jedis连接池中获取获取jedis对象
		 * 
		 * @return
		 */
		public Jedis getJedis() {
			return jedisPool.getResource();
		}

		/**
		 * @param 选择指定DB
		 * @return
		 */
		public Jedis getJedis(int DBindex) {
			Jedis jedis = jedisPool.getResource();
			jedis.select(DBindex);
			return jedis;
		}


		/**
		 * 回收jedis
		 * 
		 * @param jedis
		 */
		public void returnJedis(Jedis jedis) {
			// jedisPool.returnResource(jedis);
			jedis.close();
		} 
}
