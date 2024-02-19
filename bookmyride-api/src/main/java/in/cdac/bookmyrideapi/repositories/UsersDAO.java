package in.cdac.bookmyrideapi.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.cdac.bookmyrideapi.entity.Users;

@Repository
public interface UsersDAO extends JpaRepository<Users, Integer> {
	
	@Query("SELECT u FROM Users u WHERE u.email = :email")
	List<Users> findUsersByEmailId(String email);
	
	List<Users> findByContactNo(BigInteger contactNo);
	
}
