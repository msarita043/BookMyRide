package in.cdac.bookmyrideclient.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.Driver;
import in.cdac.bookmyrideclient.model.Users;
import in.cdac.bookmyrideclient.service.DriverService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminDashboardController {
	
	@Autowired
	DriverService driverService;
	
	@GetMapping("/admin-dashboard")
	public String adminDashboard(HttpSession httpSession, Model model) {
		if (httpSession.getAttribute("user") == null) {
			return "redirect:/";
		}
		Users users = (Users) httpSession.getAttribute("user");
		if (!users.getRole().equals(Roles.ADMIN)) {
			httpSession.invalidate();
			return "redirect:/";
		}
		List<Driver> driver = driverService.getAllDrivers();
		model.addAttribute("drivers", driver);
		return "adminDashboard";
	}

}
