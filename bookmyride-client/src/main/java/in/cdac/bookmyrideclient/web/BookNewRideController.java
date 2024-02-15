package in.cdac.bookmyrideclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.Users;
import jakarta.servlet.http.HttpSession;

@Controller
public class BookNewRideController {
	
	@GetMapping("/book-new-ride")
	public String bookNewRide(HttpSession httpSession) {
		if (httpSession.getAttribute("user") == null) {
			return "redirect:/";
		}
		Users users = (Users) httpSession.getAttribute("user");
		if (!users.getRole().equals(Roles.USER)) {
			httpSession.invalidate();
			return "redirect:/";
		}
		return "bookNewRide";
	}
}
