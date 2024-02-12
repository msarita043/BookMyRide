package in.cdac.bookmyrideapi.entity;

import in.cdac.bookmyrideapi.enums.ActivationStatus;
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
@Data
@Table(name="Driver")
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DRIVER_ID")
	private Integer driverId;
	
	@Column(name="USER_ID")
	private Integer userID;
	
	@Column(name= "CAR_TYPE_ID")
	private Integer carTypeID;
	
	@Column(name= "CAR_MODEL")
	private String carModel;
	
	@Column(name = "CAR_REG_NO")
	private String carRegNo;
	
	@Column(name = "ACTIVATION_STATUS")
	@Enumerated(EnumType.STRING)
	private ActivationStatus activationStatus;

}
