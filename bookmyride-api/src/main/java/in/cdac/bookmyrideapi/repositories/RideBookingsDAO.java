package in.cdac.bookmyrideapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.cdac.bookmyrideapi.entity.RideBookings;

@Repository
public interface RideBookingsDAO extends JpaRepository<RideBookings, Integer> {

}
