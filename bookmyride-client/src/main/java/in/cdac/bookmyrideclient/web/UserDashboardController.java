package in.cdac.bookmyrideclient.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.RideBookings;
import in.cdac.bookmyrideclient.model.Users;
import in.cdac.bookmyrideclient.service.RideBookingsService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserDashboardController {

	@Autowired
	RideBookingsService rideBookingsService;

	@GetMapping("/user-dashboard")
	public String userDashboard(HttpSession httpSession, Model model) {
		if (httpSession.getAttribute("user") == null) {
			return "redirect:/";
		}
		Users users = (Users) httpSession.getAttribute("user");
		if (!users.getRole().equals(Roles.USER)) {
			httpSession.invalidate();
			return "redirect:/";
		}

		List<RideBookings> rideBooking = rideBookingsService.getAllRideBookings();
		model.addAttribute("rideBookings", rideBooking);
		return "userDashboard";
	}

}
