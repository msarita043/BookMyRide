package in.cdac.bookmyrideclient.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.BookNewRideForm;
import in.cdac.bookmyrideclient.model.CarType;
import in.cdac.bookmyrideclient.model.Driver;
import in.cdac.bookmyrideclient.model.RideBookings;
import in.cdac.bookmyrideclient.model.Users;
import in.cdac.bookmyrideclient.service.CarTypeService;
import in.cdac.bookmyrideclient.service.RideBookingsService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BookNewRideController {

	@Autowired
	CarTypeService carTypeService;

	@Autowired
	RideBookingsService rideBookingsService;

	@GetMapping("/book-new-ride")
	public String bookNewRide(@ModelAttribute("bookNewRideForm") BookNewRideForm bookNewRideForm,
			HttpSession httpSession, Model model) {
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
	public String bookNewRide(@Valid @ModelAttribute("bookNewRideForm") BookNewRideForm bookNewRideForm,
			BindingResult validationErrorResult, HttpSession httpSession, Model model) {

		List<CarType> carTypeList = carTypeService.getAllCarTypes();
		model.addAttribute("cartypes", carTypeList);
		if (validationErrorResult.hasErrors()) {
			return "bookNewRide";
		} else {
			Users users = (Users) httpSession.getAttribute("user");
			RideBookings r = rideBookingsService.getRideBookings(bookNewRideForm, users);

			RideBookings newRideBooking = rideBookingsService.addNewRideBooking(r);

			if (newRideBooking.getRideId() != null) {
				return "redirect:/user-dashboard";
			} else {
				validationErrorResult.rejectValue("name", "error.bookNewRideForm", "There is some error!");
			}
			return "bookNewRide";

		}
	}

}
