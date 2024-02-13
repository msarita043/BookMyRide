package in.cdac.bookmyrideclient.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BookNewRideForm {
	private Integer carTypeId;
	private Integer travelhours;
	private Integer charges;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date pickuptime;
	private String pickupaddress;
	private String pickuplocation;

}
