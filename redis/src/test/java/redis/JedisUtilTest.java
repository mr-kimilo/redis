package redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtilTest extends TestCase {
	
	public JedisPool pool;
	public Jedis jedis;
	
	@Before
	public void setUp(){
		pool = new JedisPool(new JedisPoolConfig(),"9.112.49.178");
		jedis = pool.getResource();
	}

	
	@Test
	@Ignore
	public void testGet(){
		//System.out.println(jedis.get("author"));
		//System.out.println(jedis.append("author", "append2"));
		//System.out.println(jedis.del("author"));
		//System.out.println(jedis.get("author") == null);
		
		jedis.mset("name","zhangsan","age","18","height","180");
		System.out.println(jedis.mget("name","age","height"));
	}
	
	
	@Test
	@Ignore
	public void testMap(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("name", "zhangsan");
		map.put("age", "18");
		map.put("height", "180");
		jedis.hmset("user", map);
		
		//List<String> name = jedis.hmget("user","name");
		//System.out.println(name);
		
		Iterator<String> iter = jedis.hkeys("user").iterator();
		while(iter.hasNext()){
			String key = iter.next();
			System.out.println(key+":"+jedis.hmget("user", key));
		}
		
	}
	
	@Test
	public void testSet(){
		jedis.sadd("sone", "zhangsan");
		jedis.sadd("sone", "lisi");
		jedis.sadd("sone", "wangwu");
		jedis.sadd("sone", "apple");
		jedis.sadd("sone", "noname");
		
		jedis.srem("sone", "noname");
		System.out.println(jedis.smembers("sone"));
		System.out.println(jedis.sismember("sone", "apple"));
		System.out.println(jedis.scard("sone"));
	}
	
	
}
