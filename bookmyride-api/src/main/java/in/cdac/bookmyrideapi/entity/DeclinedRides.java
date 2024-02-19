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
@Data
@Table(name="declined_rides")
@NoArgsConstructor
@AllArgsConstructor
public class DeclinedRides {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DECLINED_RIDE_ID")
	private Integer declinedRideId;
	
	@Column(name = "DRIVER_ID")
	private Integer driverId;
	
	@Column(name = "RIDE_ID")
	private Integer rideId;
}
