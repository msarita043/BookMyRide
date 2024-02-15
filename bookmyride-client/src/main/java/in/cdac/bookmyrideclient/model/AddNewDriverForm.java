package in.cdac.bookmyrideclient.model;

import java.math.BigInteger;

import org.hibernate.validator.constraints.Length;

import in.cdac.bookmyrideclient.enums.ActivationStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddNewDriverForm {
	
	@Length(min = 3, max = 30)
	@NotEmpty(message = "Name cannot be empty.")
	private String name;
	
	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty(message = "Email can not be empty.")
	private String email;
	
	@NotNull(message = "Contact can not be empty.")
	private BigInteger contact;
	
	@NotNull(message = "CarType can not be empty.")
	private Integer carTypeId;
	
	@NotEmpty(message = "Car Model can not be empty.")
	private String carModel;
	
	@NotEmpty(message = "Car Registration Number can not be empty.")
	private String carRegNo;
	
	@NotNull(message = "Activation Status can not be empty.")
	private ActivationStatus activationStatus;
	
	@NotEmpty(message = "Password can not be empty.")
	@Length(min = 6, max=30)
	private String driverPassword;
}
