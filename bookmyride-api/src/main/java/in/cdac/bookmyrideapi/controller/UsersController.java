package in.cdac.bookmyrideapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.bookmyrideapi.entity.Users;
import in.cdac.bookmyrideapi.repositories.UsersDAO;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	UsersDAO usersDAO;
	
	@GetMapping("/{id}")
	public Users getUser(@PathVariable("id") Integer id) {
		return usersDAO.findById(id).orElse(new Users());
	}
	
	
}
