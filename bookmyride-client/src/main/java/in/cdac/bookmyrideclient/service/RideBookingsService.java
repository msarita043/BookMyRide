package in.cdac.bookmyrideclient.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.cdac.bookmyrideclient.enums.BookingStatus;
import in.cdac.bookmyrideclient.enums.PaymentMethod;
import in.cdac.bookmyrideclient.enums.PaymentStatus;
import in.cdac.bookmyrideclient.model.BookNewRideForm;
import in.cdac.bookmyrideclient.model.CarType;
import in.cdac.bookmyrideclient.model.Driver;
import in.cdac.bookmyrideclient.model.RideBookings;
import in.cdac.bookmyrideclient.model.Users;

@Service
public class RideBookingsService {

	@Autowired
	WebClient webClient;

	@Autowired
	CarTypeService carTypeService;

	public RideBookings getRideBookings(BookNewRideForm bookNewRideForm, Users users) {

		RideBookings r = new RideBookings();
		r.setUsers(users);
		Optional<CarType> carType = carTypeService.getCarTypeById(bookNewRideForm.getCarTypeId());
		r.setCarType(carType.get());
		r.setNoOfHours(bookNewRideForm.getTravelHours());
		r.setCharges(bookNewRideForm.getTravelHours() * 500);
		r.setPickupTime(bookNewRideForm.getPickupTime());
		r.setPickupAddress(bookNewRideForm.getPickupAddress());
		r.setPickupLocation(bookNewRideForm.getPickupLocation());
		r.setBookingStatus(BookingStatus.PENDING);
		r.setDriver(null);
		r.setPaymentStatus(PaymentStatus.NOT_PAID);
		r.setPaymentMethod(PaymentMethod.CASH);
		return r;
	}

	public RideBookings addNewRideBooking(RideBookings rideBookings) {
		return webClient.post().uri("/rideBookings/saveRideBookings").bodyValue(rideBookings).retrieve()
				.bodyToMono(RideBookings.class).block();
	}

	public List<RideBookings> getAllRideBookings() {
		return webClient.get().uri("/rideBookings/getAllRideBookings").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<RideBookings>>() {
				}).block();
	}

	public List<RideBookings> getUpcomingRidesByUser(Integer userId) {
		return webClient.post().uri("/rideBookings/getUpcomingRidesByUser/" + userId).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<RideBookings>>() {
				}).block();
	}

	public List<RideBookings> getPreviousRidesByUser(Integer userId) {
		return webClient.post().uri("/rideBookings/getPreviousRidesByUser/" + userId).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<RideBookings>>() {
				}).block();
	}

	public List<RideBookings> getAllUpcomingRidesByCarTypeId(Integer carTypeId, Driver driver) {
		return webClient.post().uri("/rideBookings/getAllUpcomingRidesByCarTypeId/" + carTypeId).bodyValue(driver)
				.retrieve().bodyToMono(new ParameterizedTypeReference<List<RideBookings>>() {
				}).block();
	}

	public Map<String, String> acceptRide(Integer rideId, Integer driverId) {
		return webClient.post().uri("/rideBookings/acceptRide/" + rideId + "/" + driverId).retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
				}).block();
	}

	public List<RideBookings> getAllUpcomingRides(Integer driverId) {
		return webClient.post().uri("/rideBookings/getAllUpcomingRides/" + driverId).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<RideBookings>>() {
				}).block();
	}

	public Map<String, String> declineRide(Integer rideId, Integer driverId) {
		return webClient.post().uri("/rideBookings/declineRide/" + rideId + "/" + driverId).retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
				}).block();
	}

	public Map<String, String> cancelRide(Integer rideId) {
		return webClient.post().uri("/rideBookings/cancelRide/" + rideId).retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
				}).block();
	}
	
	public List<RideBookings> getPreviousRidesByDriver(Integer driverId) {
		return webClient.post().uri("/rideBookings/getPreviousRidesByDriver/" + driverId).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<RideBookings>>() {
				}).block();
	}

	public Map<String, String> updatePayment(RideBookings rideBookings) {
		return webClient.post().uri("/rideBookings/updatePayment" ).bodyValue(rideBookings).retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
				}).block();
	}

}
