package in.cdac.bookmyrideclient.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.AddNewDriverForm;
import in.cdac.bookmyrideclient.model.UserLoginForm;
import in.cdac.bookmyrideclient.model.UserSignUpForm;
import in.cdac.bookmyrideclient.model.Users;

@Service
public class UsersService {

	@Autowired
	WebClient webClient;

	public Users getUser(UserSignUpForm userSignUpForm) {
		Users u = new Users();
		u.setName(userSignUpForm.getName());
		u.setEmail(userSignUpForm.getEmail());
		u.setContactNo(userSignUpForm.getContact());
		u.setPassword(userSignUpForm.getPassword());
		u.setRole(Roles.USER);
		return u;
	}
	
	public Users getUser(AddNewDriverForm addNewDriverForm) {
		Users u = new Users();
		u.setName(addNewDriverForm.getName());
		u.setEmail(addNewDriverForm.getEmail());
		u.setContactNo(addNewDriverForm.getContact());
		u.setPassword(addNewDriverForm.getDriverPassword());
		u.setRole(Roles.DRIVER);
		return u;
	}
	
	public Users getUser(UserLoginForm userLoginForm) {
		Users u = new Users();
		u.setEmail(userLoginForm.getEmail());
		u.setPassword(userLoginForm.getPassword());
		
		return u;
	}

	public Users addNewUser(Users user) {
		return webClient.post().uri("/users/saveUsers").bodyValue(user).retrieve().bodyToMono(Users.class).block();
	}

	public Map<String, Boolean> isUserExist(Users user) {
		return webClient.post().uri("/users/checkExistingUser").bodyValue(user).retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, Boolean>>() {
				}).block();
	}
	
	public Users getLoginUser(Users user) {
		return webClient.post().uri("/users/getUser").bodyValue(user).retrieve().bodyToMono(Users.class).block();
	}

	
}
