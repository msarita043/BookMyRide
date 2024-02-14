package in.cdac.bookmyrideclient.model;

import org.hibernate.validator.constraints.Length;

import in.cdac.bookmyrideclient.enums.ActivationStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDriverForm {
	@Length(min = 3, max = 30)
	@NotEmpty(message = "Name cannot be empty.")
	private String name;
	
	@NotNull(message = "CarType can not be empty.")
	private Integer carTypeId;
	
	@NotEmpty(message = "Car Model can not be empty.")
	private String carModel;
	
	@NotEmpty(message = "Car Registration Number can not be empty.")
	private String carRegNo;
	
	@NotNull(message = "Activation Status can not be empty.")
	private ActivationStatus activationStatus;

}
