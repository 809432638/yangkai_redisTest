package yangkai_redis_test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangkai_Assset.utils.RandomUtil;
import com.yangkai_Assset.utils.Stringutils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans2.xml")
public class JsonRedis {

	@Resource
	private RedisTemplate<String, User> redisTemplate;
	
	@Test
public void Json_Test() {
		
		List<User> list=new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			list.add(new User(i, Stringutils.generateChineseName(), Stringutils.sex(), "13"+RandomUtil.randomString(9), 
					"@qq.com"+RandomUtil.randomString(15),RandomUtil.random(18, 70)));
		}
		long s = System.currentTimeMillis();
		for (User user : list) {
			redisTemplate.opsForValue().set("u", user);
			//System.out.println(user);
		}
		long e = System.currentTimeMillis();
    System.out.println("使用Json方式保存对象所用时间为:"+(e-s));
		
	}
}
