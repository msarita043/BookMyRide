package in.cdac.bookmyrideclient.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	public String driverRideHistory(HttpSession httpSession, Model model) {
		if (httpSession.getAttribute("user") == null) {
			return "redirect:/";
		}
		Users users = (Users) httpSession.getAttribute("user");
		if (!users.getRole().equals(Roles.DRIVER)) {
			httpSession.invalidate();
			return "redirect:/";
		}

		Driver d = driverService.getDriverByUserId(users.getUserId());
		List<RideBookings> previousRides = rideBookingsService.getPreviousRidesByDriver(d.getDriverId());
		List<RideBookings> updatedPreviousRides = new ArrayList<RideBookings>();
		for (RideBookings rideBookings : previousRides) {
			RideBookings updateBooking = rideBookings;
			Date pickupTime = rideBookings.getPickupTime();
			Integer rideHours = rideBookings.getNoOfHours();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(pickupTime);
			calendar.add(Calendar.HOUR_OF_DAY, rideHours);
			updateBooking.setUpdatePaymentBefore(calendar.getTime());
			updatedPreviousRides.add(rideBookings);
		}
		model.addAttribute("previousRides", updatedPreviousRides);

		return "driverRideHistory";
	}

	@GetMapping("/driver-ride-history/{driverId}")
	public String driverRideHistory(HttpSession httpSession, Model model, @PathVariable Integer driverId) {

		if (httpSession.getAttribute("user") == null) {
			return "redirect:/";
		}
		Users users = (Users) httpSession.getAttribute("user");
		if (!users.getRole().equals(Roles.ADMIN)) {
			httpSession.invalidate();
			return "redirect:/";
		}
		
		List<RideBookings> previousRides = rideBookingsService.getPreviousRidesByDriver(driverId);
		List<RideBookings> updatedPreviousRides = new ArrayList<RideBookings>();
		for (RideBookings rideBookings : previousRides) {
			RideBookings updateBooking = rideBookings;
			Date pickupTime = rideBookings.getPickupTime();
			Integer rideHours = rideBookings.getNoOfHours();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(pickupTime);
			calendar.add(Calendar.HOUR_OF_DAY, rideHours);
			updateBooking.setUpdatePaymentBefore(calendar.getTime());
			updatedPreviousRides.add(rideBookings);
		}
		model.addAttribute("previousRides", updatedPreviousRides);

		return "driverRideHistoryAdmin";
	}

}
