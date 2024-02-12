package in.cdac.bookmyrideclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.Users;
import jakarta.servlet.http.HttpSession;

@Controller
public class DriverDashboardController {
	
	@GetMapping("/driver-dashboard")
	public String driverDashboard(HttpSession httpSession) {
		if (httpSession.getAttribute("user") == null) {
			return "redirect:/";
		}
		Users users = (Users) httpSession.getAttribute("user");
		if (!users.getRole().equals(Roles.DRIVER)) {
			httpSession.invalidate();
			return "redirect:/";
		}
		return "driverDashboard";
	}

}
