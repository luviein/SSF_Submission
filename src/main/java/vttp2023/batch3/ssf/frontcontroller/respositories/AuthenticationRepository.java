package vttp2023.batch3.ssf.frontcontroller.respositories;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.ssf.frontcontroller.model.User;

@Repository
public class AuthenticationRepository {
	@Autowired
	RedisTemplate<String,Object> template;
	private ValueOperations valueOperations;
	// TODO Task 5
	// Use this class to implement CRUD operations on Redis
	public void save(String username){
		template.opsForValue().set(username, "disabled");

	}
	public void disable(String username){
		valueOperations.getAndExpire(username, 30, TimeUnit.MINUTES);
	}
}
