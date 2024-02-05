package in.cdac.bookmyrideapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.cdac.bookmyrideapi.entity.Users;

@Repository
public interface UsersDAO extends JpaRepository<Users, Integer> {

}
