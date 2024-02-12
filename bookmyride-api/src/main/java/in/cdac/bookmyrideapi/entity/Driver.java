package in.cdac.bookmyrideapi.entity;

import java.math.BigInteger;

import in.cdac.bookmyrideapi.enums.ActivationStatus;
import in.cdac.bookmyrideapi.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DRIVER_ID")
	private Integer driverId;
	
	@Column(name = "DRIVER_ID")
	private Integer userId;
	
	
	private Integer carTypeId;
	
	
	private String carModel;
	
	
	private String carRegNo;
	
	
	private ActivationStatus activationStatus;
}
