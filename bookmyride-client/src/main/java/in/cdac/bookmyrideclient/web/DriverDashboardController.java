package in.cdac.bookmyrideclient.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.Driver;
import in.cdac.bookmyrideclient.model.RideBookings;
import in.cdac.bookmyrideclient.model.Users;
import in.cdac.bookmyrideclient.service.DriverService;
import in.cdac.bookmyrideclient.service.RideBookingsService;
import jakarta.servlet.http.HttpSession;

@Controller
public class DriverDashboardController {
	@Autowired
	RideBookingsService rideBookingsService;

	@Autowired
	DriverService driverService;

	@GetMapping("/driver-dashboard")
	public String driverDashboard(HttpSession httpSession, Model model) {
		if (httpSession.getAttribute("user") == null) {
			return "redirect:/";
		}
		Users users = (Users) httpSession.getAttribute("user");
		if (!users.getRole().equals(Roles.DRIVER)) {
			httpSession.invalidate();
			return "redirect:/";
		}

		Driver d = driverService.getDriverByUserId(users.getUserId());
		List<RideBookings> currentRequests = rideBookingsService
				.getAllUpcomingRidesByCarTypeId(d.getCarType().getCarTypeId(), d);
		model.addAttribute("currentRequests", currentRequests);
		List<RideBookings> upcomingRides = rideBookingsService.getAllUpcomingRides(d.getDriverId());
		model.addAttribute("upcomingRides", upcomingRides);
		return "driverDashboard";
	}

}
