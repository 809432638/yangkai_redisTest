package yangkai_redis_test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangkai_Assset.utils.RandomUtil;
import com.yangkai_Assset.utils.Stringutils;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class JdkHsahTest {

	@Resource
	private RedisTemplate<String, User> redisTemplate;
	
	@Test
	public void Jdk_Hsah_Test() {
		Map<String,User> map =new HashMap<>();
		for (int i = 0; i < 100000; i++) {
			map.put("map", new User(i, Stringutils.generateChineseName(), Stringutils.sex(), "13"+RandomUtil.randomString(9), 
					"@163.com"+RandomUtil.randomString(15),RandomUtil.random(18, 70)));
		}
		long s = System.currentTimeMillis();
		redisTemplate.opsForHash().putAll("u", map);
		long e = System.currentTimeMillis();
	    System.out.println("使用JDK_hash方式保存对象所用时间为:"+(e-s));
	}
	
	
	
}
