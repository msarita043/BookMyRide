package in.cdac.bookmyrideclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {
	
	@GetMapping("/admin-dashboard")
	public String adminDashboard() {
		return "adminDashboard";
	}

}
