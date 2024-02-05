package in.cdac.bookmyrideclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DriverRideHistoryController {
	
	@GetMapping("/driver-ride-history")
	public String driverRideHistory() {
		return "driverRideHistory";
	}

}
