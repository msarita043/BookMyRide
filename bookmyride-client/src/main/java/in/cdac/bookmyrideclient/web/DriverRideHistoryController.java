package in.cdac.bookmyrideclient.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.Driver;
import in.cdac.bookmyrideclient.model.RideBookings;
import in.cdac.bookmyrideclient.model.Users;
import in.cdac.bookmyrideclient.service.DriverService;
import in.cdac.bookmyrideclient.service.RideBookingsService;
import jakarta.servlet.http.HttpSession;

@Controller
public class DriverRideHistoryController {
	
	@Autowired
	RideBookingsService rideBookingsService;
	
	@Autowired
	DriverService driverService;
	
	@GetMapping("/driver-ride-history")
	public String driverRideHistory(HttpSession httpSession) {
		if (httpSession.getAttribute("user") == null) {
			return "redirect:/";
		}
		Users users = (Users) httpSession.getAttribute("user");
		if (!users.getRole().equals(Roles.DRIVER)) {
			httpSession.invalidate();
			return "redirect:/";
		}
		
		return "driverRideHistory";
	}

}
