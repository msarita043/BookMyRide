package in.cdac.bookmyrideapi.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.cdac.bookmyrideapi.entity.RideBookings;

@Repository
public interface RideBookingsDAO extends JpaRepository<RideBookings, Integer> {

	@Query("SELECT r FROM RideBookings r WHERE r.users.userId = :userId AND r.pickupTime >= :currentDateTime ORDER BY r.pickupTime")
	List<RideBookings> findUpcomingRidesByUser(Integer userId, Date currentDateTime);

	@Query("FROM RideBookings WHERE users.userId = :userId AND pickupTime < :currentDateTime")
	List<RideBookings> findPreviousRidesByUser(Integer userId, Date currentDateTime);

	@Query("FROM RideBookings WHERE carType.carTypeId = :carTypeId AND pickupTime >= :currentDateTime AND bookingStatus = 'PENDING'")
	List<RideBookings> findAllUpcomingRidesByCarTypeId(Integer carTypeId, Date currentDateTime);
	
	@Query("FROM RideBookings WHERE driver.driverId = :driverId AND pickupTime >= :currentDateTime AND bookingStatus = 'CONFIRMED'")
	List<RideBookings> getAllUpcomingRides(Integer driverId, Date currentDateTime);

	

	
}
