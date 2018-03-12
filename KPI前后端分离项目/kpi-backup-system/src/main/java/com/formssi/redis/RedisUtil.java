/**
 * FileName:RedisUtil.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月15日下午10:01:07
 ********************************
 *Modifycation History:
 *date:2018年1月15日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.redis;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.formssi.util.JsonUtil;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

/**
 * @author Bill
 *
 */
@Component
public class RedisUtil {

	@Autowired
	private RedisClient redisClient;
	/**
	 * 缓存生存时间
	 */
	private static final int expire = 60000;
	/** 操作Key的方法 */
	/*
	 * public static RedisUtil.Keys KEYS = new RedisUtil().new Keys();
	 *//** 对存储结构为String类型的操作 */
	/*
	 * public static RedisUtil.Strings STRINGS = new RedisUtil().new Strings();
	 *//** 对存储结构为List类型的操作 */
	/*
	 * public static RedisUtil.Lists LISTS = new RedisUtil().new Lists();
	 *//** 对存储结构为Set类型的操作 */
	/*
	 * public static RedisUtil.Sets SETS = new RedisUtil().new Sets();
	 *//** 对存储结构为HashMap类型的操作 */
	/*
	 * public static RedisUtil.Hash HASH = new RedisUtil().new Hash();
	 *//** 对存储结构为Set(排序的)类型的操作 *//*
									 * public static RedisUtil.SortSet SORTSET = new RedisUtil().new SortSet();
									 */

	/**
	 * 设置过期时间
	 *
	 * @author Bill
	 * @param key
	 * @param seconds
	 */
	public void expire(String key, int seconds) {
		if (seconds <= 0) {
			return;
		}
		redisClient.operate(jedis -> jedis.expire(key, seconds));
	}

	public void expire(String key, int seconds, int DBindex) {
		if (seconds <= 0) {
			return;
		}
		redisClient.operateSpecificDB(jedis -> jedis.expire(key, seconds), DBindex);
	}

	/**
	 * 设置默认过期时间
	 *
	 * @author Bill
	 * @param key
	 */
	public void expire(String key) {
		expire(key, expire);
	}

	// *******************************************Keys*******************************************//

	/**
	 * 清空所有key
	 */
	public String flushAll() {
		return redisClient.operate(jedis -> jedis.flushAll());
	}

	/**
	 * 更改key
	 *
	 * @param String
	 *            oldkey
	 * @param String
	 *            newkey
	 * @return 状态码
	 */
	public String rename(String oldkey, String newkey) {
		return rename(SafeEncoder.encode(oldkey), SafeEncoder.encode(newkey));
	}

	/**
	 * 更改key,仅当新key不存在时才执行
	 *
	 * @param String
	 *            oldkey
	 * @param String
	 *            newkey
	 * @return 状态码
	 */
	public long renamenx(String oldkey, String newkey) {
		return redisClient.operate(jedis -> jedis.renamenx(oldkey, newkey));
	}

	/**
	 * 更改key
	 *
	 * @param String
	 *            oldkey
	 * @param String
	 *            newkey
	 * @return 状态码
	 */
	public String rename(byte[] oldkey, byte[] newkey) {
		return redisClient.operate(jedis -> jedis.rename(oldkey, newkey));
	}

	/**
	 * 设置key的过期时间，以秒为单位
	 *
	 * @param String
	 *            key
	 * @param 时间
	 *            ,已秒为单位
	 * @return 影响的记录数
	 */
	public long expired(String key, int seconds) {
		return redisClient.operate(jedis -> jedis.expire(key, seconds));
	}

	public long expired(String key, int seconds, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.expire(key, seconds), DBindex);
	}

	/**
	 * 设置key的过期时间,它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00，格里高利历）的偏移量。
	 *
	 * @param String
	 *            key
	 * @param 时间
	 *            ,已秒为单位
	 * @return 影响的记录数
	 */
	public long expireAt(String key, long timestamp) {
		return redisClient.operate(jedis -> jedis.expireAt(key, timestamp));
	}

	/**
	 * 查询key的过期时间
	 *
	 * @param String
	 *            key
	 * @return 以秒为单位的时间表示
	 */
	public long ttl(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		return redisClient.operate(jedis -> jedis.ttl(key));
	}

	/**
	 * 取消对key过期时间的设置
	 *
	 * @param key
	 * @return 影响的记录数
	 */
	public long persist(String key) {
		return redisClient.operate(jedis -> jedis.persist(key));
	}

	/**
	 * 删除keys对应的记录,可以是多个key
	 *
	 * @param String
	 *            ... keys
	 * @return 删除的记录数
	 */
	public long del(String... keys) {
		return redisClient.operate(jedis -> jedis.del(keys));
	}

	public long del(int DBindex, String... keys) {
		return redisClient.operateSpecificDB(jedis -> jedis.del(keys), DBindex);
	}

	/**
	 * 删除keys对应的记录,可以是多个key
	 *
	 * @param String
	 *            ... keys
	 * @return 删除的记录数
	 */
	public long del(byte[]... keys) {
		return redisClient.operate(jedis -> jedis.del(keys));
	}

	/**
	 * 判断key是否存在
	 *
	 * @param String
	 *            key
	 * @return boolean
	 */
	public boolean exists(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		return redisClient.operate(jedis -> jedis.exists(key));
	}

	public boolean exists(String key, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.exists(key), DBindex);
	}

	/**
	 * 对List,Set,SortSet进行排序,如果集合数据较大应避免使用这个方法
	 *
	 * @param String
	 *            key
	 * @return List<String> 集合的全部记录
	 **/
	public List<String> sort(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		return redisClient.operate(jedis -> jedis.sort(key));
	}

	/**
	 * 对List,Set,SortSet进行排序或limit
	 *
	 * @param String
	 *            key
	 * @param SortingParams
	 *            parame 定义排序类型或limit的起止位置.
	 * @return List<String> 全部或部分记录
	 **/
	public List<String> sort(String key, SortingParams parame) {
		// ShardedJedis sjedis = getShardedJedis();
		return redisClient.operate(jedis -> jedis.sort(key, parame));
	}

	/**
	 * 返回指定key存储的类型
	 *
	 * @param String
	 *            key
	 * @return String string|list|set|zset|hash
	 **/
	public String type(String key) {
		return redisClient.operate(jedis -> jedis.type(key));
	}

	/**
	 * 查找所有匹配给定的模式的键
	 *
	 * @param String
	 *            key的表达式,*表示多个，？表示一个
	 */
	public Set<String> keys(String pattern) {
		return redisClient.operate(jedis -> jedis.keys(pattern));
	}

	// *******************************************Sets*******************************************//

	/**
	 * 向Set添加一条记录，如果member已存在返回0,否则返回1
	 *
	 * @param String
	 *            key
	 * @param String
	 *            member
	 * @return 操作码,0或1
	 */
	public long sadd(String key, String member) {
		return redisClient.operate(jedis -> jedis.sadd(key, member));
	}

	public long sadd(String key, String member, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.sadd(key, member), DBindex);
	}

	public long sadd(byte[] key, byte[] member) {
		return redisClient.operate(jedis -> jedis.sadd(key, member));
	}

	public long sadd(byte[] key, byte[] member, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.sadd(key, member), DBindex);
	}

	/**
	 * 获取给定key中元素个数
	 *
	 * @param String
	 *            key
	 * @return 元素个数
	 */
	public long scard(String key) {
		// ShardedJedis sjedis = getShardedJedis();
		return redisClient.operate(jedis -> jedis.scard(key));
	}

	public long scard(String key, int DBindex) {
		// ShardedJedis sjedis = getShardedJedis();
		return redisClient.operateSpecificDB(jedis -> jedis.scard(key), DBindex);
	}

	/**
	 * 返回从第一组和所有的给定集合之间的差异的成员
	 *
	 * @param String
	 *            ... keys
	 * @return 差异的成员集合
	 */
	public Set<String> sdiff(String... keys) {
		return redisClient.operate(jedis -> jedis.sdiff(keys));
	}

	public Set<String> sdiff(int DBindex, String... keys) {
		return redisClient.operateSpecificDB(jedis -> jedis.sdiff(keys), DBindex);
	}

	/**
	 * 这个命令等于sdiff,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
	 *
	 * @param String
	 *            newkey 新结果集的key
	 * @param String
	 *            ... keys 比较的集合
	 * @return 新集合中的记录数
	 **/
	public long sdiffstore(int DBindex, String newkey, String... keys) {
		return redisClient.operateSpecificDB(jedis -> jedis.sdiffstore(newkey, keys), DBindex);
	}

	public long sdiffstore(String newkey, String... keys) {
		return redisClient.operate(jedis -> jedis.sdiffstore(newkey, keys));
	}

	/**
	 * 返回给定集合交集的成员,如果其中一个集合为不存在或为空，则返回空Set
	 *
	 * @param String
	 *            ... keys
	 * @return 交集成员的集合
	 **/
	public Set<String> sinter(String... keys) {
		return redisClient.operate(jedis -> jedis.sinter(keys));
	}

	public Set<String> sinter(int DBindex, String... keys) {
		return redisClient.operateSpecificDB(jedis -> jedis.sinter(keys), DBindex);
	}

	/**
	 * 这个命令等于sinter,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
	 *
	 * @param String
	 *            newkey 新结果集的key
	 * @param String
	 *            ... keys 比较的集合
	 * @return 新集合中的记录数
	 **/
	public long sinterstore(String newkey, String... keys) {
		return redisClient.operate(jedis -> jedis.sinterstore(newkey, keys));
	}

	public long sinterstore(int DBindex, String newkey, String... keys) {
		return redisClient.operate(jedis -> jedis.sinterstore(newkey, keys));
	}

	/**
	 * 确定一个给定的值是否存在
	 *
	 * @param String
	 *            key
	 * @param String
	 *            member 要判断的值
	 * @return 存在返回1，不存在返回0
	 **/
	public boolean sismember(String key, String member) {
		return redisClient.operate(jedis -> jedis.sismember(key, member));
	}

	public boolean sismember(int DBindex, String key, String member) {
		return redisClient.operateSpecificDB(jedis -> jedis.sismember(key, member), DBindex);
	}

	/**
	 * 返回集合中的所有成员
	 *
	 * @param String
	 *            key
	 * @return 成员集合
	 */
	public Set<String> smembers(String key) {
		return redisClient.operate(jedis -> jedis.smembers(key));
	}

	public Set<String> smembers(String key, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.smembers(key), DBindex);
	}

	public Set<byte[]> smembers(byte[] key) {
		return redisClient.operate(jedis -> jedis.smembers(key));
	}

	public Set<byte[]> smembers(byte[] key, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.smembers(key), DBindex);
	}

	/**
	 * 将成员从源集合移出放入目标集合 <br/>
	 * 如果源集合不存在或不包哈指定成员，不进行任何操作，返回0<br/>
	 * 否则该成员从源集合上删除，并添加到目标集合，如果目标集合中成员已存在，则只在源集合进行删除
	 *
	 * @param String
	 *            srckey 源集合
	 * @param String
	 *            dstkey 目标集合
	 * @param String
	 *            member 源集合中的成员
	 * @return 状态码，1成功，0失败
	 */
	public long smove(String srckey, String dstkey, String member) {
		return redisClient.operate(jedis -> jedis.smove(srckey, dstkey, member));
	}

	public long smove(String srckey, String dstkey, String member, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.smove(srckey, dstkey, member), DBindex);
	}

	/**
	 * 从集合中删除成员
	 *
	 * @param String
	 *            key
	 * @return 被删除的成员
	 */
	public String spop(String key) {
		return redisClient.operate(jedis -> jedis.spop(key));
	}

	public String spop(String key, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.spop(key), DBindex);
	}

	/**
	 * 从集合中删除指定成员
	 *
	 * @param String
	 *            key
	 * @param String
	 *            member 要删除的成员
	 * @return 状态码，成功返回1，成员不存在返回0
	 */
	public long srem(String key, String member) {
		return redisClient.operate(jedis -> jedis.srem(key, member));
	}

	public long srem(String key, String member, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.srem(key, member), DBindex);
	}

	/**
	 * 合并多个集合并返回合并后的结果，合并后的结果集合并不保存<br/>
	 *
	 * @param String
	 *            ... keys
	 * @return 合并后的结果集合
	 * @see sunionstore
	 */
	public Set<String> sunion(String... keys) {
		return redisClient.operate(jedis -> jedis.sunion(keys));
	}

	public Set<String> sunion(int DBindex, String... keys) {
		return redisClient.operateSpecificDB(jedis -> jedis.sunion(keys), DBindex);
	}

	/**
	 * 合并多个集合并将合并后的结果集保存在指定的新集合中，如果新集合已经存在则覆盖
	 *
	 * @param String
	 *            newkey 新集合的key
	 * @param String
	 *            ... keys 要合并的集合
	 **/
	public long sunionstore(String newkey, String... keys) {
		return redisClient.operate(jedis -> jedis.sunionstore(newkey, keys));
	}

	public long sunionstore(int DBindex, String newkey, String... keys) {
		return redisClient.operateSpecificDB(jedis -> jedis.sunionstore(newkey, keys), DBindex);
	}

	// *******************************************SortSet*******************************************//

	/**
	 * 向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重
	 *
	 * @param String
	 *            key
	 * @param double
	 *            score 权重
	 * @param String
	 *            member 要加入的值，
	 * @return 状态码 1成功，0已存在member的值
	 */
	public long zadd(String key, double score, String member) {
		return redisClient.operate(jedis -> jedis.zadd(key, score, member));
	}

	public long zadd(String key, Map<String, Double> scoreMembers) {
		return redisClient.operate(jedis -> jedis.zadd(key, scoreMembers));
	}

	/**
	 * 获取集合中元素的数量
	 *
	 * @param String
	 *            key
	 * @return 如果返回0则集合不存在
	 */
	public long zcard(String key) {
		return redisClient.operate(jedis -> jedis.zcard(key));
	}

	/**
	 * 获取指定权重区间内集合的数量
	 *
	 * @param String
	 *            key
	 * @param double
	 *            min 最小排序位置
	 * @param double
	 *            max 最大排序位置
	 */
	public long zcount(String key, double min, double max) {
		return redisClient.operate(jedis -> jedis.zcount(key, min, max));
	}

	/**
	 * 获得set的长度
	 *
	 * @param key
	 * @return
	 */
	public long zlength(String key) {
		Set<String> set = Optional.ofNullable(redisClient.operate(jedis -> jedis.zrange(key, 0, -1)))
				.orElse(new HashSet<String>());
		long len = set.size();
		return len;
	}

	/**
	 * 权重增加给定值，如果给定的member已存在
	 *
	 * @param String
	 *            key
	 * @param double
	 *            score 要增的权重
	 * @param String
	 *            member 要插入的值
	 * @return 增后的权重
	 */
	public double zincrby(String key, double score, String member) {
		return redisClient.operate(jedis -> jedis.zincrby(key, score, member));
	}

	/**
	 * 返回指定位置的集合元素,0为第一个元素，-1为最后一个元素
	 *
	 * @param String
	 *            key
	 * @param int
	 *            start 开始位置(包含)
	 * @param int
	 *            end 结束位置(包含)
	 * @return Set<String>
	 */
	public Set<String> zrange(String key, int start, int end) {
		return redisClient.operate(jedis -> jedis.zrange(key, start, end));
	}

	/**
	 * 返回指定权重区间的元素集合
	 *
	 * @param String
	 *            key
	 * @param double
	 *            min 上限权重
	 * @param double
	 *            max 下限权重
	 * @return Set<String>
	 */
	public Set<String> zrangeByScore(String key, double min, double max) {
		return redisClient.operate(jedis -> jedis.zrangeByScore(key, min, max));
	}

	/**
	 * 获取指定值在集合中的位置，集合排序从低到高
	 *
	 * @see zrevrank
	 * @param String
	 *            key
	 * @param String
	 *            member
	 * @return long 位置
	 */
	public long zrank(String key, String member) {
		return redisClient.operate(jedis -> jedis.zrank(key, member));
	}

	/**
	 * 获取指定值在集合中的位置，集合排序从高到低
	 *
	 * @see zrank
	 * @param String
	 *            key
	 * @param String
	 *            member
	 * @return long 位置
	 */
	public long zrevrank(String key, String member) {
		return redisClient.operate(jedis -> jedis.zrevrank(key, member));
	}

	/**
	 * 从集合中删除成员
	 *
	 * @param String
	 *            key
	 * @param String
	 *            member
	 * @return 返回1成功
	 */
	public long zrem(String key, String member) {
		return redisClient.operate(jedis -> jedis.zrem(key, member));
	}

	/**
	 * 删除
	 *
	 * @param key
	 * @return
	 */
	public long zrem(String key) {
		return redisClient.operate(jedis -> jedis.del(key));
	}

	/**
	 * 删除给定位置区间的元素
	 *
	 * @param String
	 *            key
	 * @param int
	 *            start 开始区间，从0开始(包含)
	 * @param int
	 *            end 结束区间,-1为最后一个元素(包含)
	 * @return 删除的数量
	 */
	public long zremrangeByRank(String key, int start, int end) {
		return redisClient.operate(jedis -> jedis.zremrangeByRank(key, start, end));
	}

	/**
	 * 删除给定权重区间的元素
	 *
	 * @param String
	 *            key
	 * @param double
	 *            min 下限权重(包含)
	 * @param double
	 *            max 上限权重(包含)
	 * @return 删除的数量
	 */
	public long zremrangeByScore(String key, double min, double max) {
		return redisClient.operate(jedis -> jedis.zremrangeByScore(key, min, max));
	}

	/**
	 * 获取给定区间的元素，原始按照权重由高到低排序
	 *
	 * @param String
	 *            key
	 * @param int
	 *            start
	 * @param int
	 *            end
	 * @return Set<String>
	 */
	public Set<String> zrevrange(String key, int start, int end) {
		return redisClient.operate(jedis -> jedis.zrevrange(key, start, end));
	}

	/**
	 * 获取给定值在集合中的权重
	 * 
	 * @param String
	 *            key
	 * @param memeber
	 * @return double 权重
	 */
	public double zscore(String key, String memebr) {
		Double score = redisClient.operate(jedis -> jedis.zscore(key, memebr));
		if (score != null)
			return score;
		return 0;
	}

	// *******************************************Hash*******************************************//

	/**
	 * 从hash中删除指定的存储
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return 状态码，1成功，0失败
	 */
	public long hdel(String key, String fieid) {
		return redisClient.operate(jedis -> jedis.hdel(key, fieid));
	}

	public long hdel(String key) {
		return redisClient.operate(jedis -> jedis.del(key));
	}

	/**
	 * 测试hash中指定的存储是否存在
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return 1存在，0不存在
	 */
	public boolean hexists(String key, String fieid) {
		return redisClient.operate(jedis -> jedis.hexists(key, fieid));
	}

	/**
	 * 返回hash中指定存储位置的值
	 *
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return 存储对应的值
	 */
	public String hget(String key, String fieid) {
		return redisClient.operate(jedis -> jedis.hget(key, fieid));
	}

	public byte[] hget(byte[] key, byte[] fieid) {
		return redisClient.operate(jedis -> jedis.hget(key, fieid));
	}

	/**
	 * 以Map的形式返回hash中的存储和值
	 * 
	 * @param String
	 *            key
	 * @return Map<Strinig,String>
	 */
	public Map<String, String> hgetAll(String key) {
		return redisClient.operate(jedis -> jedis.hgetAll(key));
	}

	/**
	 * 添加一个对应关系
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid
	 * @param String
	 *            value
	 * @return 状态码 1成功，0失败，fieid已存在将更新，也返回0
	 **/
	public long hset(String key, String fieid, String value) {
		return redisClient.operate(jedis -> jedis.hset(key, fieid, value));
	}

	public long hset(String key, String fieid, byte[] value) {
		return redisClient.operate(jedis -> jedis.hset(key.getBytes(), fieid.getBytes(), value));
	}

	/**
	 * 添加对应关系，只有在fieid不存在时才执行
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid
	 * @param String
	 *            value
	 * @return 状态码 1成功，0失败fieid已存
	 **/
	public long hsetnx(String key, String fieid, String value) {
		return redisClient.operate(jedis -> jedis.hsetnx(key, fieid, value));
	}

	/**
	 * 获取hash中value的集合
	 *
	 * @param String
	 *            key
	 * @return List<String>
	 */
	public List<String> hvals(String key) {
		return redisClient.operate(jedis -> jedis.hvals(key));
	}

	/**
	 * 在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型
	 *
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储位置
	 * @param String
	 *            long value 要增加的值,可以是负数
	 * @return 增加指定数字后，存储位置的值
	 */
	public long hincrby(String key, String fieid, long value) {
		return redisClient.operate(jedis -> jedis.hincrBy(key, fieid, value));
	}

	/**
	 * 返回指定hash中的所有存储名字,类似Map中的keySet方法
	 *
	 * @param String
	 *            key
	 * @return Set<String> 存储名称的集合
	 */
	public Set<String> hkeys(String key) {
		return redisClient.operate(jedis -> jedis.hkeys(key));
	}

	/**
	 * 获取hash中存储的个数，类似Map中size方法
	 *
	 * @param String
	 *            key
	 * @return long 存储的个数
	 */
	public long hlen(String key) {
		return redisClient.operate(jedis -> jedis.hlen(key));
	}

	/**
	 * 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null
	 *
	 * @param String
	 *            key
	 * @param String
	 *            ... fieids 存储位置
	 * @return List<String>
	 */
	public List<String> hmget(String key, String... fieids) {
		return redisClient.operate(jedis -> jedis.hmget(key, fieids));
	}

	public List<byte[]> hmget(byte[] key, byte[]... fieids) {
		return redisClient.operate(jedis -> jedis.hmget(key, fieids));
	}

	/**
	 * 添加对应关系，如果对应关系已存在，则覆盖
	 *
	 * @param Strin
	 *            key
	 * @param Map
	 *            <String,String> 对应关系
	 * @return 状态，成功返回OK
	 */
	public String hmset(String key, Map<String, String> map) {
		return redisClient.operate(jedis -> jedis.hmset(key, map));
	}

	/**
	 * 添加对应关系，如果对应关系已存在，则覆盖
	 *
	 * @param Strin
	 *            key
	 * @param Map
	 *            <String,String> 对应关系
	 * @return 状态，成功返回OK
	 */
	public String hmset(byte[] key, Map<byte[], byte[]> map) {
		return redisClient.operate(jedis -> jedis.hmset(key, map));
	}


	// *******************************************Strings*******************************************//
	/**
	 * 根据key获取记录
	 * 
	 * @param String
	 *            key
	 * @return 值
	 */
	public String get(String key) {
		return redisClient.operate(jedis -> jedis.get(key));
	}

	public String get(String key, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.get(key), DBindex);
	}

	/**
	 * 根据key获取记录
	 * 
	 * @param byte[]
	 *            key
	 * @return 值
	 */
	public byte[] get(byte[] key) {
		return redisClient.operate(jedis -> jedis.get(key));
	}

	public byte[] get(byte[] key, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.get(key), DBindex);
	}

	/**
	 * 返回反序列化的对象
	 */
	public Object getObject(String key) {
		return JsonUtil.strToObj(redisClient.operate(jedis -> jedis.get(key)),Object.class);
	}

	public Object getObject(String key, int DBindex) {
		return JsonUtil.strToObj(redisClient.operateSpecificDB(jedis -> jedis.get(key), DBindex),Object.class);
	}

	/**
	 * 添加有过期时间的记录
	 *
	 * @param String
	 *            key
	 * @param int
	 *            seconds 过期时间，以秒为单位
	 * @param String
	 *            value
	 * @return String 操作状态
	 */
	public String setEx(String key, int seconds, String value) {
		return redisClient.operate(jedis -> jedis.setex(key, seconds, value));
	}

	public String setEx(String key, int seconds, String value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.setex(key, seconds, value), DBindex);
	}

	/**
	 * 添加有过期时间的记录
	 *
	 * @param String
	 *            key
	 * @param int
	 *            seconds 过期时间，以秒为单位
	 * @param String
	 *            value
	 * @return String 操作状态
	 */
	public String setEx(byte[] key, int seconds, byte[] value) {
		return redisClient.operate(jedis -> jedis.setex(key, seconds, value));
	}

	public String setEx(byte[] key, int seconds, byte[] value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.setex(key, seconds, value), DBindex);
	}

	/**
	 * 添加一条记录，仅当给定的key不存在时才插入
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return long 状态码，1插入成功且key不存在，0未插入，key存在
	 */
	public long setnx(String key, String value) {
		return redisClient.operate(jedis -> jedis.setnx(key, value));
	}

	public long setnx(String key, String value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.setnx(key, value), DBindex);
	}

	/**
	 * 添加一条序列化对象的记录
	 */
	public <T extends Serializable> String setObject(String key, T value) {
		return redisClient.operate(jedis -> jedis.set(key, JsonUtil.objToStr(value)));
	}

	public <T extends Serializable> String setObject(String key, T value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.set(key, JsonUtil.objToStr(value)), DBindex);
	}

	/**
	 * 添加记录,如果记录已存在将覆盖原有的value
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 状态码
	 */
	public String set(String key, String value) {
		return redisClient.operate(jedis -> jedis.set(key, value));
	}

	public String set(String key, String value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.set(key, value), DBindex);
	}

	/**
	 * 添加记录,如果记录已存在将覆盖原有的value
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 状态码
	 */
	public String set(String key, byte[] value) {
		return set(SafeEncoder.encode(key), value);
	}

	public String set(String key, byte[] value, int DBindex) {
		return set(SafeEncoder.encode(key), value, DBindex);
	}

	/**
	 * 添加记录,如果记录已存在将覆盖原有的value
	 * 
	 * @param byte[]
	 *            key
	 * @param byte[]
	 *            value
	 * @return 状态码
	 */
	public String set(byte[] key, byte[] value) {
		return redisClient.operate(jedis -> jedis.set(key, value));
	}

	public String set(byte[] key, byte[] value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.set(key, value), DBindex);
	}

	/**
	 * 从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据<br/>
	 * 例:String str1="123456789";<br/>
	 * 对str1操作后setRange(key,4,0000)，str1="123400009";
	 * 
	 * @param String
	 *            key
	 * @param long
	 *            offset
	 * @param String
	 *            value
	 * @return long value的长度
	 */
	public long setRange(String key, long offset, String value) {
		return redisClient.operate(jedis -> jedis.setrange(key, offset, value));
	}

	public long setRange(String key, long offset, String value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.setrange(key, offset, value), DBindex);
	}

	/**
	 * 在指定的key中追加value
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return long 追加后value的长度
	 **/
	public long append(String key, String value) {
		return redisClient.operate(jedis -> jedis.append(key, value));
	}

	public long append(String key, String value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.append(key, value), DBindex);
	}

	/**
	 * 将key对应的value减去指定的值，只有value可以转为数字时该方法才可用
	 *
	 * @param String
	 *            key
	 * @param long
	 *            number 要减去的值
	 * @return long 减指定值后的值
	 */
	public long decrBy(String key, long number) {
		return redisClient.operate(jedis -> jedis.decrBy(key, number));
	}

	public long decrBy(String key, long number, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.decrBy(key, number), DBindex);
	}

	/**
	 * <b>可以作为获取唯一id的方法</b><br/>
	 * 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用
	 * 
	 * @param String
	 *            key
	 * @param long
	 *            number 要减去的值
	 * @return long 相加后的值
	 */
	public long incrBy(String key, long number) {
		return redisClient.operate(jedis -> jedis.incrBy(key, number));
	}

	public long incrBy(String key, long number, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.incrBy(key, number), DBindex);
	}

	/**
	 * 对指定key对应的value进行截取
	 * 
	 * @param String
	 *            key
	 * @param long
	 *            startOffset 开始位置(包含)
	 * @param long
	 *            endOffset 结束位置(包含)
	 * @return String 截取的值
	 */
	public String getrange(String key, long startOffset, long endOffset) {
		return redisClient.operate(jedis -> jedis.getrange(key, startOffset, endOffset));
	}

	public String getrange(String key, long startOffset, long endOffset, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.getrange(key, startOffset, endOffset), DBindex);
	}

	/**
	 * 获取并设置指定key对应的value<br/>
	 * 如果key存在返回之前的value,否则返回null
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return String 原始value或null
	 */
	public String getSet(String key, String value) {
		return redisClient.operate(jedis -> jedis.getSet(key, value));
	}

	public String getSet(String key, String value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.getSet(key, value), DBindex);
	}

	/**
	 * 批量获取记录,如果指定的key不存在返回List的对应位置将是null
	 * 
	 * @param String
	 *            keys
	 * @return List<String> 值得集合
	 */
	public List<String> mget(String... keys) {
		return redisClient.operate(jedis -> jedis.mget(keys));
	}

	public List<String> mget(int DBindex, String... keys) {
		return redisClient.operateSpecificDB(jedis -> jedis.mget(keys), DBindex);
	}

	/**
	 * 批量存储记录
	 * 
	 * @param String
	 *            keysvalues 例:keysvalues="key1","value1","key2","value2";
	 * @return String 状态码
	 */
	public String mset(String... keysvalues) {
		return redisClient.operate(jedis -> jedis.mset(keysvalues));
	}

	public String mset(int DBindex, String... keysvalues) {
		return redisClient.operateSpecificDB(jedis -> jedis.mset(keysvalues), DBindex);
	}

	/**
	 * 获取key对应的值的长度
	 * 
	 * @param String
	 *            key
	 * @return value值得长度
	 */
	public long strlen(String key) {
		return redisClient.operate(jedis -> jedis.strlen(key));
	}

	public long strlen(String key, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.strlen(key), DBindex);
	}

	// *******************************************Lists*******************************************//
	/**
	 * List长度
	 * 
	 * @param String
	 *            key
	 * @return 长度
	 */
	public long llen(String key) {
		return redisClient.operate(jedis -> jedis.llen(key));
	}

	/**
	 * List长度
	 * 
	 * @param byte[]
	 *            key
	 * @return 长度
	 */
	public long llen(byte[] key) {
		return redisClient.operate(jedis -> jedis.llen(key));
	}

	/**
	 * 覆盖操作,将覆盖List中指定位置的值
	 * 
	 * @param byte[]
	 *            key
	 * @param int
	 *            index 位置
	 * @param byte[]
	 *            value 值
	 * @return 状态码
	 */
	public String lset(byte[] key, int index, byte[] value) {
		return redisClient.operate(jedis -> jedis.lset(key, index, value));
	}

	/**
	 * 覆盖操作,将覆盖List中指定位置的值
	 * 
	 * @param key
	 * @param int
	 *            index 位置
	 * @param String
	 *            value 值
	 * @return 状态码
	 */
	public String lset(String key, int index, String value) {
		return redisClient.operate(jedis -> jedis.lset(key, index, value));
	}

	/**
	 * 在value的相对位置插入记录
	 * 
	 * @param key
	 * @param LIST_POSITION
	 *            前面插入或后面插入
	 * @param String
	 *            pivot 相对位置的内容
	 * @param String
	 *            value 插入的内容
	 * @return 记录总数
	 */
	public long linsert(String key, LIST_POSITION where, String pivot, String value) {
		return redisClient.operate(jedis -> jedis.linsert(key, where, pivot, value));
	}

	/**
	 * 在指定位置插入记录
	 * 
	 * @param String
	 *            key
	 * @param LIST_POSITION
	 *            前面插入或后面插入
	 * @param byte[]
	 *            pivot 相对位置的内容
	 * @param byte[]
	 *            value 插入的内容
	 * @return 记录总数
	 */
	public long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value) {
		return redisClient.operate(jedis -> jedis.linsert(key, where, pivot, value));
	}

	/**
	 * 获取List中指定位置的值
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            index 位置
	 * @return 值
	 **/
	public String lindex(String key, int index) {
		return redisClient.operate(jedis -> jedis.lindex(key, index));
	}

	/**
	 * 获取List中指定位置的值
	 * 
	 * @param byte[]
	 *            key
	 * @param int
	 *            index 位置
	 * @return 值
	 **/
	public byte[] lindex(byte[] key, int index) {
		return redisClient.operate(jedis -> jedis.lindex(key, index));
	}

	/**
	 * 将List中的第一条记录移出List
	 * 
	 * @param String
	 *            key
	 * @return 移出的记录
	 */
	public String lpop(String key) {
		return redisClient.operate(jedis -> jedis.lpop(key));
	}

	/**
	 * 将List中的第一条记录移出List
	 * 
	 * @param byte[]
	 *            key
	 * @return 移出的记录
	 */
	public byte[] lpop(byte[] key) {
		return redisClient.operate(jedis -> jedis.lpop(key));
	}

	/**
	 * 将List中最后第一条记录移出List
	 *
	 * @param byte[]
	 *            key
	 * @return 移出的记录
	 */
	public String rpop(String key) {
		return redisClient.operate(jedis -> jedis.rpop(key));
	}

	/**
	 * 向List尾部追加记录
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 记录总数
	 */
	public long lpush(String key, String value) {
		return redisClient.operate(jedis -> jedis.lpush(key, value));
	}

	/**
	 * 向List头部追加记录
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 记录总数
	 */
	public long rpush(String key, String value) {
		return redisClient.operate(jedis -> jedis.rpush(key, value));
	}

	/**
	 * 向List头部追加记录，带数据库
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 记录总数
	 */
	public long rpush(String key, String value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.rpush(key, value), DBindex);
	}

	/**
	 * 向List尾部追加记录，带数据库
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 记录总数
	 */
	public long lpush(String key, String value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.lpush(key, value), DBindex);
	}

	/**
	 * 向List头部追加记录
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 记录总数
	 */
	public long rpush(byte[] key, byte[] value) {
		return redisClient.operate(jedis -> jedis.rpush(key, value));
	}

	/**
	 * 向List中追加记录
	 * 
	 * @param byte[]
	 *            key
	 * @param byte[]
	 *            value
	 * @return 记录总数
	 */
	public long lpush(byte[] key, byte[] value) {
		return redisClient.operate(jedis -> jedis.lpush(key, value));
	}

	/**
	 * 获取指定范围的记录，可以做为分页使用
	 * 
	 * @param String
	 *            key
	 * @param long
	 *            start
	 * @param long
	 *            end
	 * @return List
	 */
	public List<String> lrange(String key, long start, long end) {
		return redisClient.operate(jedis -> jedis.lrange(key, start, end));
	}

	/**
	 * 获取指定范围的记录，可以做为分页使用
	 * 
	 * @param String
	 *            key
	 * @param long
	 *            start
	 * @param long
	 *            end
	 * @return List
	 */
	public List<String> lrange(String key, long start, long end, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.lrange(key, start, end), DBindex);
	}

	/**
	 * 获取指定范围的记录，可以做为分页使用
	 * 
	 * @param byte[]
	 *            key
	 * @param int
	 *            start
	 * @param int
	 *            end 如果为负数，则尾部开始计算
	 * @return List
	 */
	public List<byte[]> lrange(byte[] key, int start, int end) {
		return redisClient.operate(jedis -> jedis.lrange(key, start, end));
	}

	/**
	 * 删除List中c条记录，被删除的记录值为value
	 * 
	 * @param byte[]
	 *            key
	 * @param int
	 *            c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
	 * @param byte[]
	 *            value 要匹配的值
	 * @return 删除后的List中的记录数
	 */
	public long lrem(byte[] key, int c, byte[] value) {
		return redisClient.operate(jedis -> jedis.lrem(key, c, value));
	}

	/**
	 * 删除List中c条记录，被删除的记录值为value
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
	 * @param String
	 *            value 要匹配的值
	 * @return 删除后的List中的记录数
	 */
	public long lrem(String key, int c, String value) {
		return redisClient.operate(jedis -> jedis.lrem(key, c, value));
	}

	/**
	 * 删除List中c条记录，被删除的记录值为value,带数据库编号
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
	 * @param String
	 *            value 要匹配的值
	 * @return 删除后的List中的记录数
	 */
	public long lrem(String key, int c, String value, int DBindex) {
		return redisClient.operateSpecificDB(jedis -> jedis.lrem(key, c, value), DBindex);
	}

	/**
	 * 算是删除吧，只保留start与end之间的记录
	 * 
	 * @param byte[]
	 *            key
	 * @param int
	 *            start 记录的开始位置(0表示第一条记录)
	 * @param int
	 *            end 记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
	 * @return 执行状态码
	 */
	public String ltrim(byte[] key, int start, int end) {
		return redisClient.operate(jedis -> jedis.ltrim(key, start, end));
	}

	/**
	 * 算是删除吧，只保留start与end之间的记录
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            start 记录的开始位置(0表示第一条记录)
	 * @param int
	 *            end 记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
	 * @return 执行状态码
	 */
	public String ltrim(String key, int start, int end) {
		return redisClient.operate(jedis -> jedis.ltrim(key, start, end));
	}
}
