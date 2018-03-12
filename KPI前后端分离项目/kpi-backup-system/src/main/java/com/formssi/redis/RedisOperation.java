/**
 * FileName:RedisOperationInterface.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月15日下午8:18:26
 ********************************
 *Modifycation History:
 *date:2018年1月15日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.redis;

import redis.clients.jedis.Jedis;

/**
 * @author Bill
 *
 */
public interface RedisOperation<T> {

	    public T operation(Jedis jedis);
}
