package in.cdac.bookmyrideclient.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookNewRideForm {
	
	@NotNull(message = "CarType can not be empty.")
	private Integer carTypeId;
	
	@NotNull(message = "Travel hours can not be empty.")
	private Integer travelHours;
	
	@Future
	@NotNull(message="Date & Time can not be empty.")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date pickupTime;
	
	@NotEmpty(message = "Pickup address can not be empty.")
	private String pickupAddress;
	
	private String pickupLocation;

}
