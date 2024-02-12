package in.cdac.bookmyrideapi.entity;


import java.util.Date;

import in.cdac.bookmyrideapi.enums.BookingStatus;
import in.cdac.bookmyrideapi.enums.PaymentMethod;
import in.cdac.bookmyrideapi.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ride_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideBookings {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RIDE_ID")
	private Integer rideId;
	
	@ManyToOne
	@JoinColumn(name="USER_ID",nullable=false)
	private Users users;
	
	@ManyToOne
	@JoinColumn(name="CAR_TYPE_ID",nullable=false)
	private CarType carType;
	
	@ManyToOne
	@JoinColumn(name="DRIVER_ID",nullable=false)
	private Driver driver;
	
	@Column(name="NO_OF_HOURS")
	private Integer noOfHours;
	
	@Column(name="CHARGES")
	private Integer charges;
	
	@Column(name="PICKUP_LOCATION")
	private String pickupLocation;
	
	@Column(name="PICKUP_ADDRESS")
	private String pickupAddress;
	
	@Column(name="PICKUP_TIME")
	private Date pickupTime;
	
	@Column(name="BOOKING_TIME")
	private Date bookingTime;
	
	@Column(name="BOOKING_STATUS")
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus;
	
	@Column(name="PAYMENT_STATUS")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@Column(name="PAYMENT_METHOD")
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
}
