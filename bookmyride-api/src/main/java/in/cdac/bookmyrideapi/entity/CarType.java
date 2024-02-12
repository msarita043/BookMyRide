package in.cdac.bookmyrideapi.entity;

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
@Table(name="car_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CAR_TYPE_ID")
	private Integer carTypeId;
	
	@Column(name="CAR_TYPE")
	private String carType;
	
	
}
