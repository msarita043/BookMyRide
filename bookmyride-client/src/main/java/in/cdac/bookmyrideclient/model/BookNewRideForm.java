package in.cdac.bookmyrideclient.model;

import java.util.Date;

import lombok.Data;

@Data
public class BookNewRideForm {
	
	private Integer carTypeId;
	private Integer travelhours;
	private Integer charges;
	private Date pickuptime;
	private String pickupaddress;
	private String pickuplocation;

}
