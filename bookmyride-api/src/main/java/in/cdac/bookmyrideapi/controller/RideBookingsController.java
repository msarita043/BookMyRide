package in.cdac.bookmyrideapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.bookmyrideapi.entity.DeclinedRides;
import in.cdac.bookmyrideapi.entity.Driver;
import in.cdac.bookmyrideapi.entity.RideBookings;
import in.cdac.bookmyrideapi.enums.BookingStatus;
import in.cdac.bookmyrideapi.repositories.DeclinedRidesDAO;
import in.cdac.bookmyrideapi.repositories.DriverDAO;
import in.cdac.bookmyrideapi.repositories.RideBookingsDAO;
import in.cdac.bookmyrideapi.repositories.UsersDAO;

@RestController
@RequestMapping("/rideBookings")
public class RideBookingsController {

	@Autowired
	RideBookingsDAO rideBookingsDao;

	@Autowired
	UsersDAO usersDAO;

	@Autowired
	DriverDAO driverDAO;

	@Autowired
	DeclinedRidesDAO declinedRidesDAO;

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

	@PostMapping("/getAllUpcomingRidesByCarTypeId/{carTypeId}")
	public List<RideBookings> getAllUpcomingRidesByCarTypeId(@PathVariable Integer carTypeId, @RequestBody Driver driver) {
		List<RideBookings> result = new ArrayList<RideBookings>();
		List<DeclinedRides> declinedRides = declinedRidesDAO.findByDriverId(driver.getDriverId());
		List<Integer> declinedRideIds = new ArrayList<Integer>();
		for (DeclinedRides declinedRide : declinedRides) {
			declinedRideIds.add(declinedRide.getRideId());
		}
		List<RideBookings> rides = rideBookingsDao.findAllUpcomingRidesByCarTypeId(carTypeId, new Date());
		for (RideBookings rideBookings : rides) {
			if(!declinedRideIds.contains(rideBookings.getRideId())) {
				result.add(rideBookings);
			}
		}
		return result;
	}

	@PostMapping("/acceptRide/{rideId}/{driverId}")
	public Map<String, String> acceptRide(@PathVariable Integer rideId, @PathVariable Integer driverId) {
		Map<String, String> res = new HashMap<String, String>();
		Optional<RideBookings> rideOptional = rideBookingsDao.findById(rideId);
		RideBookings rideBookings = rideOptional.get();
		if (rideBookings.getDriver() == null) {
			Optional<Driver> driver = driverDAO.findById(driverId);
			rideBookings.setDriver(driver.get());
			rideBookings.setBookingStatus(BookingStatus.CONFIRMED);
			rideBookingsDao.save(rideBookings);
			res.put("message", "Ride request accepted.");
		} else {
			res.put("message", "Ride is aassigned for different user.");
		}

		return res;
	}

	@PostMapping("/declineRide/{rideId}/{driverId}")
	public Map<String, String> declineRide(@PathVariable Integer rideId, @PathVariable Integer driverId) {
		Map<String, String> res = new HashMap<String, String>();
		DeclinedRides declinedRides = new DeclinedRides();
		declinedRides.setRideId(rideId);
		declinedRides.setDriverId(driverId);
		declinedRidesDAO.save(declinedRides);
		res.put("message", "Ride declined.");
		return res;
	}

	@PostMapping("/getAllUpcomingRides/{driverId}")
	public List<RideBookings> getAllUpcomingRides(@PathVariable Integer driverId) {
		return rideBookingsDao.getAllUpcomingRides(driverId, new Date());
	}
	
	@PostMapping("/cancelRide/{rideId}")
	public Map<String, String> cancelRide(@PathVariable Integer rideId) {
		Map<String, String> res = new HashMap<String, String>();
		Optional<RideBookings> rideOptional = rideBookingsDao.findById(rideId);
		RideBookings rideBookings = rideOptional.get();
		rideBookings.setDriver(null);
		rideBookings.setBookingStatus(BookingStatus.CANCELLED);
		rideBookingsDao.save(rideBookings);
		res.put("message", "Ride cancelled.");
		return res;
	}
	
	
	
}
