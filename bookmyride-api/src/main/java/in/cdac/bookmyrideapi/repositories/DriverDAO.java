package in.cdac.bookmyrideapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.cdac.bookmyrideapi.entity.Driver;

@Repository
public interface DriverDAO extends JpaRepository<Driver, Integer> {		

}
