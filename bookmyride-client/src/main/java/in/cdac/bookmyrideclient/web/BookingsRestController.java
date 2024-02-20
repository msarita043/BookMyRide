package in.cdac.bookmyrideclient.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.bookmyrideclient.model.Driver;
import in.cdac.bookmyrideclient.model.RideBookings;
import in.cdac.bookmyrideclient.service.DriverService;
import in.cdac.bookmyrideclient.service.RideBookingsService;

@RestController
public class BookingsRestController {

	@Autowired
	RideBookingsService rideBookingsService;
	@Autowired
	DriverService driverService;
	
	@PostMapping("/acceptRide")
	public Map<String, String> acceptRide(@RequestBody Map<String, Integer> input){
		Integer userId = input.get("userId");
		Integer rideId = input.get("rideId");
		Driver d = driverService.getDriverByUserId(userId);
		return rideBookingsService.acceptRide(rideId, d.getDriverId());
	}
	
	@PostMapping("/declineRide")
	public Map<String, String> declineRide(@RequestBody Map<String, Integer> input){
		Integer userId = input.get("userId");
		Integer rideId = input.get("rideId");
		Driver d = driverService.getDriverByUserId(userId);
		System.out.println(userId +" "+rideId );
		return rideBookingsService.declineRide(rideId, d.getDriverId());
	}
	
	@PostMapping("/cancelRide")
	public Map<String, String> canceleRide(@RequestBody Map<String, Integer> input){
		Integer rideId = input.get("rideId");
		return rideBookingsService.cancelRide(rideId);
	}
	
	@PostMapping("/updatePayment")
	public Map<String, String> updatePayment(@RequestBody RideBookings rideBookings){
		return rideBookingsService.updatePayment(rideBookings);
	}
}
