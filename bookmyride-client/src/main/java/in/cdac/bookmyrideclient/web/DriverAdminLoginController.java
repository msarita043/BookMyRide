package in.cdac.bookmyrideclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DriverAdminLoginController {

	@GetMapping("/driver-admin-login")
	public String driverAdminLogin() {
		return "driverAdminLogin";
	}
}
