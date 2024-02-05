package in.cdac.bookmyrideclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashboardController {
	
	@GetMapping("/user-dashboard")
	public String userDashboard() {
		return "userDashboard";
	}

}
