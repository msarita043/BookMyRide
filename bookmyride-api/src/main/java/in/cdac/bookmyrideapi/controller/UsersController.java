package in.cdac.bookmyrideapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.bookmyrideapi.entity.Users;
import in.cdac.bookmyrideapi.repositories.UsersDAO;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UsersDAO usersDAO;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/{id}")
	public Users getUser(@PathVariable("id") Integer id) {
		return usersDAO.findById(id).orElse(new Users());
	}

	@PostMapping("/getUser")
	public Users getUser(@RequestBody Users users) {
		List<Users> usersByEmail = usersDAO.findUsersByEmailId(users.getEmail());
		if (!usersByEmail.isEmpty()) {
			Users u = usersByEmail.get(0);
			if (passwordEncoder.matches(users.getPassword(), u.getPassword())) {
				return u;
			}

		}
		return users;
	}

	@PostMapping("/saveUsers")
	public Users saveUser(@RequestBody Users users) {
		String encodedPassword = passwordEncoder.encode(users.getPassword());
		users.setPassword(encodedPassword);
		return usersDAO.save(users);
	}

	@PostMapping("/checkExistingUser")
	public Map<String, Boolean> checkExistingUser(@RequestBody Users users) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();

		List<Users> usersByEmailList = usersDAO.findUsersByEmailId(users.getEmail());

		if (usersByEmailList.isEmpty()) {
			result.put("email", false);
		} else {
			result.put("email", true);
		}

		List<Users> usersByContactNo = usersDAO.findByContactNo(users.getContactNo());
		if (usersByContactNo.isEmpty()) {
			result.put("contact", false);
		} else {
			result.put("contact", true);
		}

		return result;
	}

}
