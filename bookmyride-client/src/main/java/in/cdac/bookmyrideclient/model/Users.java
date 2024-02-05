package in.cdac.bookmyrideclient.model;

import java.math.BigInteger;

import in.cdac.bookmyrideclient.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	private Integer userId;
	private String email;
	private String name;
	private BigInteger contactNo;
	private String password;
	private Roles role;
}
