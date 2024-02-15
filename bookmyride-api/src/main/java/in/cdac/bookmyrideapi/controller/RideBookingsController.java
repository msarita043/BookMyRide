package in.cdac.bookmyrideapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.bookmyrideapi.entity.Driver;
import in.cdac.bookmyrideapi.entity.RideBookings;
import in.cdac.bookmyrideapi.repositories.RideBookingsDAO;

@RestController
@RequestMapping("/rideBookings")
public class RideBookingsController {

	@Autowired
	RideBookingsDAO rideBookingsDao;
	
	@GetMapping("/getAllBookings")
	public List<RideBookings> getAllRideBookings() {
		return rideBookingsDao.findAll();
	}
	@PostMapping("/saveNewBooking")
	public RideBookings saveNewRide(@RequestBody RideBookings ride) {
		return rideBookingsDao.save(ride);
	}
	
}
