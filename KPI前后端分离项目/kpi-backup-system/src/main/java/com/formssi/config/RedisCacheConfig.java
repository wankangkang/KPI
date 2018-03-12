/**
 * FileName:RedisConfig.java
 * @Description:继承缓存类，表明使用如下定义的redis进行缓存操作。
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2017年10月6日上午11:49:45
 ********************************
 *Modifycation History:
 *date:2017年10月6日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.config;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport{
	
	/**
	 * @Title:wiselyKeyGenerator
	 * @Description:制定根据类名生成的唯一键规则
	 * @param:@return
	 * @return:KeyGenerator
	 * @throws
	 */
	@Bean  
    public KeyGenerator wiselyKeyGenerator(){  
        return new KeyGenerator() {  
        	@Override  
            public Object generate(Object target, Method method, Object... params) {  
                StringBuilder sb = new StringBuilder();  
                sb.append(target.getClass().getName());  
                sb.append(method.getName());  
                for (Object obj : params) {  
                    sb.append(obj.toString());  
                }  
                return sb.toString();  
            }  
        };  
  
    } 
	
	/**
	 * @Title:cacheManager
	 * @Description:定义使用的redis缓存模板
	 * @param:@param redisTemplate
	 * @param:@return
	 * @return:CacheManager
	 * @throws
	 */
	@Bean
	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate){
		return new RedisCacheManager(redisTemplate);
	}
	
	/**
	 * @Title:redisTemplate
	 * @Description:自定义redis缓存模板
	 * @param:@param connectionFactory
	 * @param:@return
	 * @return:RedisTemplate
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate(connectionFactory);  
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);  
        ObjectMapper om = new ObjectMapper();  
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);  
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
        jackson2JsonRedisSerializer.setObjectMapper(om);  
        template.setValueSerializer(jackson2JsonRedisSerializer);  
        template.afterPropertiesSet();  
        return template;
	}
}
