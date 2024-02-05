package in.cdac.bookmyrideclient.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.cdac.bookmyrideclient.model.Users;

@Service
public class UsersService {
	
	public String getEmail(Integer id) {
		Users user = WebClient.create("http://localhost:8083")
				.get().uri("/users/{id}",Map.of("id",1)).retrieve().bodyToMono(Users.class).block();
		return user.getEmail();
	}
}
