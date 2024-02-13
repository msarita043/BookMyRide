package in.cdac.bookmyrideclient.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.BookNewRideForm;
import in.cdac.bookmyrideclient.model.CarType;
import in.cdac.bookmyrideclient.model.Users;
import in.cdac.bookmyrideclient.service.CarTypeService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BookNewRideController {
	
	@Autowired
	CarTypeService carTypeService;
	
	@GetMapping("/book-new-ride")
	public String bookNewRide(@ModelAttribute("BookNewRideForm") BookNewRideForm bookNewRideForm, HttpSession httpSession, Model model) {
		if (httpSession.getAttribute("user") == null) {
			return "redirect:/";
		}
		Users users = (Users) httpSession.getAttribute("user");
		if (!users.getRole().equals(Roles.USER)) {
			httpSession.invalidate();
			return "redirect:/";
		}
		
		List<CarType> carTypeList = carTypeService.getAllCarTypes();
		model.addAttribute("cartypes", carTypeList);
		return "bookNewRide";
	}
	
	@PostMapping("/book-new-ride")
	public String bookNewRide(@ModelAttribute("BookNewRideForm") BookNewRideForm bookNewRideForm, Model model) {
		
		
		System.out.println(bookNewRideForm.toString());
		
		
		return "bookNewRide";
	}
	
	
	
}
