package in.cdac.bookmyrideapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.cdac.bookmyrideapi.entity.CarType;

@Repository
public interface CarTypeDAO extends JpaRepository<CarType, Integer> {

}
