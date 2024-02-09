package in.cdac.bookmyrideclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserDashboardController {
	
	@GetMapping("/user-dashboard")
	public String userDashboard(HttpSession httpSession) {
		if(httpSession.getAttribute("user")==null) {
			return "redirect:/";
		}
		return "userDashboard";
	}

}
