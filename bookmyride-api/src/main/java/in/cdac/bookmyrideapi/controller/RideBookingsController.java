package in.cdac.bookmyrideapi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.bookmyrideapi.entity.RideBookings;
import in.cdac.bookmyrideapi.repositories.RideBookingsDAO;
import in.cdac.bookmyrideapi.repositories.UsersDAO;

@RestController
@RequestMapping("/rideBookings")
public class RideBookingsController {

	@Autowired
	RideBookingsDAO rideBookingsDao;
	
	@Autowired
	UsersDAO usersDAO;
	
	@GetMapping("/getAllRideBookings")
	public List<RideBookings> getAllRideBookings() {
		return rideBookingsDao.findAll();
	}
	@PostMapping("/saveRideBookings")
	public RideBookings saveNewRide(@RequestBody RideBookings rideBookings) {
		return rideBookingsDao.save(rideBookings);
	}
	
	@PostMapping("/getUpcomingRidesByUser/{userId}")
	public List<RideBookings> getUpcomingRidesByUser(@PathVariable Integer userId) {
		return rideBookingsDao.findUpcomingRidesByUser(userId, new Date());
	}
	
	@PostMapping("/getPreviousRidesByUser/{userId}")
	public List<RideBookings> getPreviousRidesByUser(@PathVariable Integer userId) {
		return rideBookingsDao.findPreviousRidesByUser(userId, new Date());
	}

}
