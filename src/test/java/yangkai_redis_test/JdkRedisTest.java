package yangkai_redis_test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangkai_Assset.utils.DateUtils;
import com.yangkai_Assset.utils.RandomUtil;
import com.yangkai_Assset.utils.Stringutils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class JdkRedisTest {

	@Resource
	private RedisTemplate<String, User> redisTemplate;
	
	@Test
	public void Jdk_Test() {
		
		List<User> list=new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(new User(i, Stringutils.generateChineseName(), Stringutils.sex(), "13"+RandomUtil.randomString(9), 
					"@sohu.com"+RandomUtil.randomString(15),RandomUtil.random(18, 70)));
		}
		long s = System.currentTimeMillis();
		for (User user : list) {
			redisTemplate.opsForValue().set("u", user);
			System.out.println(user);
		}
		long e = System.currentTimeMillis();
    System.out.println("使用JDK方式保存对象所用时间为:"+(e-s));
		
	}
	
	
}
