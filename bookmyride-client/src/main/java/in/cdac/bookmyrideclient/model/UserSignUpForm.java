package in.cdac.bookmyrideclient.model;

import java.math.BigInteger;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserSignUpForm {
	
	@Length(min = 3, max = 30)
	@NotEmpty(message = "Name cannot be empty.")
	private String name;
	
	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty(message = "Email cannot be empty.")
	private String email;
	
	@NotNull(message = "Contact can't be empty.")
	private BigInteger contact;
	
	@NotEmpty(message = "Password can't be empty.")
	@Length(min = 6, max=30)
	private String password;
	
	@NotEmpty(message = "Confirm password can not be empty.")
	private String confirmPassword;
}
