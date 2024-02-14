package in.cdac.bookmyrideclient.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.CarType;
import in.cdac.bookmyrideclient.model.Driver;
import in.cdac.bookmyrideclient.model.UpdateDriverForm;
import in.cdac.bookmyrideclient.model.Users;
import in.cdac.bookmyrideclient.service.CarTypeService;
import in.cdac.bookmyrideclient.service.DriverService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UpdateDriverController {

	@Autowired
	CarTypeService carTypeService;
	
	@Autowired
	DriverService driverService;
	
	@GetMapping("/update-driver/{driverId}")
	public String updateDriver(@ModelAttribute("updateDriverForm") UpdateDriverForm updateDriverForm, Model model,
			HttpSession httpSession, @PathVariable Integer driverId) {
		if (httpSession.getAttribute("user") == null) {
			return "redirect:/";
		}
		Users users = (Users) httpSession.getAttribute("user");
		if (!users.getRole().equals(Roles.ADMIN)) {
			httpSession.invalidate();
			return "redirect:/";
		}
		Optional<Driver> driver = driverService.getDriver(driverId);
		List<CarType> carTypeList = carTypeService.getAllCarTypes();
		model.addAttribute("cartypes", carTypeList);
		model.addAttribute("driver", driver.get());
		return "updateDriver";
	}
	
	@PostMapping("/update-driver/{driverId}")
	public String updateDriverPost(@Valid @ModelAttribute("updateDriverForm") UpdateDriverForm updateDriverForm, Users u, BindingResult bindingResult, Model model,
			HttpSession httpSession, @PathVariable Integer driverId) {
		Optional<Driver> driver = driverService.getDriver(driverId);
		List<CarType> carTypeList = carTypeService.getAllCarTypes();
		model.addAttribute("cartypes", carTypeList);
		model.addAttribute("driver", driver.get());
		
		if(bindingResult.hasErrors()) {
			return "updateDriver";
		}
		Driver d = driverService.getDriver(updateDriverForm, u);
		Driver updatedDriver = driverService.updateDriver(d, driverId);
		
		
		return "redirect:/admin-dashboard";
	}
}
