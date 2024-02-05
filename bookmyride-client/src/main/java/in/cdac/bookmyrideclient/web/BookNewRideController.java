package in.cdac.bookmyrideclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookNewRideController {
	
	@GetMapping("/book-new-ride")
	public String bookNewRide() {
		return "bookNewRide";
	}
}
