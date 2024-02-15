package in.cdac.bookmyrideclient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.cdac.bookmyrideclient.model.BookNewRideForm;
import in.cdac.bookmyrideclient.model.CarType;
import in.cdac.bookmyrideclient.model.Ride;
import in.cdac.bookmyrideclient.model.Users;

@Service
public class BookNewRideService {
	
	@Autowired
	WebClient webClient;
	
	@Autowired
	CarTypeService carTypeService;
	@Autowired
	UsersService userService;
	
	public Ride newRide(BookNewRideForm bookNewRideForm) {
		Ride r = new Ride();
		/*rideid**/
		Optional<CarType> carType = carTypeService.getCarTypeById(bookNewRideForm.getCarTypeId());
		r.setCarType(carType.get());
		r.setTravelhours(bookNewRideForm.getTravelhours());
		r.setCharges(bookNewRideForm.getCharges());
		r.setPickuptime(bookNewRideForm.getPickuptime());
		r.setPickupaddress(bookNewRideForm.getPickupaddress());
		r.setPickuplocation(bookNewRideForm.getPickuplocation());
		
		return r;
	}
	public List<Ride> getAllRideBookings() {
		return webClient.get().uri("/rideBookings/getAllBookings").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Ride>>() {
				}).block();
	}
	public Ride addNewRide(Ride ride) {
		return webClient.post().uri("/rideBookings/saveNewBooking").bodyValue(ride).retrieve().bodyToMono(Ride.class).block();

	}
}
