package in.cdac.bookmyrideclient.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookNewRideForm {
	
	@NotNull(message = "CarType can not be empty.")
	private Integer carTypeId;
	
	@NotNull(message = "Travel hours can not be empty.")
	private Integer travelhours;
	
	@NotNull(message = "Charges can not be empty.")
	private Integer charges;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date pickuptime;
	
	@NotEmpty(message = "Pickup address can not be empty.")
	private String pickupaddress;
	
	@NotEmpty(message = "Pickup location can not be empty.")
	private String pickuplocation;

}
