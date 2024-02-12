package in.cdac.bookmyrideclient.model;

import in.cdac.bookmyrideclient.enums.ActivationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
	private Integer driverId;

	private Users users;
	
	private CarType carType;
	
	private String carModel;
	
	private String carRegNo;
	
	private ActivationStatus activationStatus;

}
