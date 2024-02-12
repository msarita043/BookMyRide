package in.cdac.bookmyrideapi.entity;

import java.math.BigInteger;

import in.cdac.bookmyrideapi.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name="EMAIL_ID")
	private String email;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CONTACT_NO")
	private BigInteger contactNo;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ROLE")
	@Enumerated(EnumType.STRING)
	private Roles role;
}
