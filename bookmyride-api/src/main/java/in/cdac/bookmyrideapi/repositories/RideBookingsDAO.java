package in.cdac.bookmyrideapi.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.cdac.bookmyrideapi.entity.RideBookings;

@Repository
public interface RideBookingsDAO extends JpaRepository<RideBookings, Integer> {

	@Query("SELECT r FROM RideBookings r WHERE r.users.userId = :userId and r.pickupTime >= :currentDateTime ORDER BY r.pickupTime")
	List<RideBookings> findUpcomingRidesByUser(Integer userId, Date currentDateTime);

	@Query("from RideBookings WHERE users.userId = :userId and pickupTime < :currentDateTime")
	List<RideBookings> findPreviousRidesByUser(Integer userId, Date currentDateTime);

}
