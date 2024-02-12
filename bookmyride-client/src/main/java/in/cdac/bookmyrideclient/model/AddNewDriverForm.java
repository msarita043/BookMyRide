package in.cdac.bookmyrideclient.model;

import java.math.BigInteger;

import in.cdac.bookmyrideclient.enums.ActivationStatus;
import lombok.Data;

@Data
public class AddNewDriverForm {
	private String name;
	private String email;
	private BigInteger contact;
	private CarType carType;
	private String carModel;
	private String carRegNo;
	private ActivationStatus activationStatus;
	private String driverPassword;
}
