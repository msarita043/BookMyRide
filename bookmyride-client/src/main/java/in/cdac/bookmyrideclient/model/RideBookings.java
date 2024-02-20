package in.cdac.bookmyrideclient.model;

import java.util.Date;

import in.cdac.bookmyrideclient.enums.BookingStatus;
import in.cdac.bookmyrideclient.enums.PaymentMethod;
import in.cdac.bookmyrideclient.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideBookings {
	
	private Integer rideId;
	
	private Users users;
	
	private CarType carType;
	
	private Driver driver;
	
	private Integer noOfHours;
	
	private Integer charges;
	
	private String pickupLocation;
	
	private String pickupAddress;
	
	private Date pickupTime;
	
	private Date bookingTime;
	
	private BookingStatus bookingStatus;
	
	private PaymentStatus paymentStatus;
	
	private PaymentMethod paymentMethod; 
	
	private Date updatePaymentBefore;
}
