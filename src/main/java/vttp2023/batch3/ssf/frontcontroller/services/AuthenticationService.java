package vttp2023.batch3.ssf.frontcontroller.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;




import vttp2023.batch3.ssf.frontcontroller.model.User;
import vttp2023.batch3.ssf.frontcontroller.respositories.AuthenticationRepository;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationRepository repo;

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public boolean authenticate(String username, String password) throws Exception {
		User user = new User(username, password);
		System.out.println(user.toJSON().toString());
		String url = "https://authservice-production-e8b2.up.railway.app/api/authenticate";
		RequestEntity<String> req = RequestEntity
			.post(url)
			.contentType(MediaType.APPLICATION_JSON)
			.header("Accept", MediaType.APPLICATION_JSON_VALUE)
			.body(user.toJSON().toString());

			

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> res = template.exchange(req, String.class);

		System.out.println(res.getStatusCode().toString());

		if((res.getStatusCode().equals(HttpStatus.BAD_REQUEST)) || (res.getStatusCode().equals(HttpStatus.UNAUTHORIZED))){
			user.setWrongCount(user.getWrongCount()+1);
			return false;
		}
		user.setAuthenticated(true);
		return true;
		
		}





	
	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		this.repo.save(username);
		this.repo.disable(username);
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}
