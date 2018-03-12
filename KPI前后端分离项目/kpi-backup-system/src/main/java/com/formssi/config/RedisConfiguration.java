package com.formssi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

	
	@Bean(name= "jedis.pool")  
    @Autowired  
    public JedisPool jedisPool (@Qualifier("spring.redis.pool.config") JedisPoolConfig config,   
                @Value("${spring.redis.host}")String host,   
                @Value("${spring.redis.port}")int port) {  
        return new JedisPool(config, host, port);  
    }  
      
    @Bean(name= "spring.redis.pool.config")  
    public JedisPoolConfig jedisPoolConfig (@Value("${spring.redis.pool.max-active}")int maxTotal,  
                                @Value("${spring.redis.pool.max-idle}")int maxIdle, 
                                @Value("${spring.redis.pool.min-idle}")int minIdle,
                                @Value("${spring.redis.pool.max-wait}")int maxWaitMillis) {  
       JedisPoolConfig config = new JedisPoolConfig();  
        config.setMaxTotal(maxTotal);  
        config.setMaxIdle(maxIdle);  
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);  
        return config;  
    } 
}
