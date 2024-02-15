package in.cdac.bookmyrideclient.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ride {
	
	private Integer rideID;
	private Users users;
	private CarType carType;
	private Integer travelhours;
	private Integer charges;
	private Date pickuptime;
	private String pickupaddress;
	private String pickuplocation;
}
