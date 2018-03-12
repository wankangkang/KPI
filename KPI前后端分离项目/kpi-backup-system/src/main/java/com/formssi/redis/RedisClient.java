/**
 * FileName:RedisOperationImpl.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月15日下午9:52:09
 ********************************
 *Modifycation History:
 *date:2018年1月15日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

/**
 * @author Bill
 *
 */
@Component
public class RedisClient {

	@Autowired
	RedisPoolClient redisPoolClient;
	public  <T extends Object> T operate(RedisOperation<T> interfaces) {
        // 返回值
        T Object;
        // 获取连接池里的连接
        Jedis jedis = redisPoolClient.getJedis();
        try {
            // 业务操作
            Object = interfaces.operation(jedis);
        } finally {
            // 释放链接
        	redisPoolClient.returnJedis(jedis);
        }
        return Object;
    }
	
	public <T extends Object> T operateSpecificDB(RedisOperation<T> interfaces, int DBindex) {
        // 返回值
        T Object;
        // 获取连接池里的连接
        Jedis jedis = redisPoolClient.getJedis(DBindex);
        try {
            // 业务操作
            Object = interfaces.operation(jedis);
        } finally {
            // 释放链接
        	redisPoolClient.returnJedis(jedis);
        }
        return Object;
    }
}
