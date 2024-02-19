package in.cdac.bookmyrideapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.cdac.bookmyrideapi.entity.DeclinedRides;

@Repository
public interface DeclinedRidesDAO extends JpaRepository<DeclinedRides, Integer>{
	List<DeclinedRides> findByDriverId(Integer driverId);
}
