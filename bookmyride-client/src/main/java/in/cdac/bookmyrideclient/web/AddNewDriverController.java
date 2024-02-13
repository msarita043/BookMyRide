package in.cdac.bookmyrideclient.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.AddNewDriverForm;
import in.cdac.bookmyrideclient.model.CarType;
import in.cdac.bookmyrideclient.model.Users;
import in.cdac.bookmyrideclient.service.CarTypeService;
import in.cdac.bookmyrideclient.service.UsersService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AddNewDriverController {

	@Autowired
	CarTypeService carTypeService;
	@Autowired
	UsersService usersService;

	@GetMapping("/add-new-driver")
	public String addNewDriver(@ModelAttribute("addNewDriverForm") AddNewDriverForm addNewDriverForm, Model model,
			HttpSession httpSession) {
		if (httpSession.getAttribute("user") == null) {
			return "redirect:/";
		}
		Users users = (Users) httpSession.getAttribute("user");
		if (!users.getRole().equals(Roles.ADMIN)) {
			httpSession.invalidate();
			return "redirect:/";
		}
		List<CarType> carTypeList = carTypeService.getAllCarTypes();
		model.addAttribute("cartypes", carTypeList);
		return "addNewDriver";
	}

	@PostMapping("/add-new-driver")
	public String addNewDriver(@Valid @ModelAttribute("addNewDriverForm") AddNewDriverForm addNewDriverForm,
			BindingResult validationErrorResult, Model model) {

		List<CarType> carTypeList = carTypeService.getAllCarTypes();
		model.addAttribute("cartypes", carTypeList);

		if (validationErrorResult.hasErrors()) {
			return "addNewDriver";
		} else {
			Users u = usersService.getUser(addNewDriverForm);
			Map<String, Boolean> isUserExist = usersService.isUserExist(u);
			if (isUserExist.get("email")) {
				validationErrorResult.rejectValue("email", "error.userSignupForm", "Email Already Exists.");
			}
			if (isUserExist.get("contact")) {
				validationErrorResult.rejectValue("contact", "error.userSignupForm", "ContactNo Already Exists.");
			}

			if (validationErrorResult.hasErrors()) {
				return "addNewDriver";
			} else {
				Users newUser = usersService.addNewUser(u);

				return "redirect:/admin-dashboard";
			}
		}
	}
}
