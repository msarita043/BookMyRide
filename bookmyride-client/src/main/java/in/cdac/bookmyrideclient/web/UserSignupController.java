package in.cdac.bookmyrideclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import in.cdac.bookmyrideclient.model.Users;

@Controller
public class UserSignupController {
	
	@GetMapping("/user-signup")
	public String userSignup() {
		return "userSignUp";
	}
	
	@PostMapping("/user-signup")
	public String userSignup(@ModelAttribute("users") Users users ) {
		System.out.println(users);
		return "redirect:/user-signup";
	}
}
