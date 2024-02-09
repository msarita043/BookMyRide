package in.cdac.bookmyrideclient.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import in.cdac.bookmyrideclient.model.UserSignUpForm;
import in.cdac.bookmyrideclient.model.Users;
import in.cdac.bookmyrideclient.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserSignupController {

	@Autowired
	UsersService usersService;

	@GetMapping("/user-signup")
	public String userSignup(@ModelAttribute("userSignupForm") UserSignUpForm userSignupForm, Model model) {
		return "userSignUp";
	}

	@PostMapping("/user-signup")
	public String userSignup(@Valid @ModelAttribute("userSignupForm") UserSignUpForm userSignupForm,
			BindingResult validationErrorResult, Model model, HttpServletRequest request) {
		if (userSignupForm.getConfirmPassword() == null || userSignupForm.getConfirmPassword().isEmpty()
				|| !(userSignupForm.getPassword().equals(userSignupForm.getConfirmPassword()))) {
			validationErrorResult.rejectValue("confirmPassword", "error.userSignupForm", "Should be same as password.");
		}
		if (validationErrorResult.hasErrors()) {
			return "userSignUp";
		} else {

			Users u = usersService.getUser(userSignupForm);
			Map<String, Boolean> isUserExist = usersService.isUserExist(u);
			if (isUserExist.get("email")) {
				validationErrorResult.rejectValue("email", "error.userSignupForm", "Email Already Exists.");
			}
			if (isUserExist.get("contact")) {
				validationErrorResult.rejectValue("contact", "error.userSignupForm", "ContactNo Already Exists.");
			}

			if (validationErrorResult.hasErrors()) {
				return "userSignUp";
			} else {
				Users newUser = usersService.addNewUser(u);
				
				HttpSession session = request.getSession(true);
				session.setAttribute("user", newUser);
				return "redirect:/user-dashboard";
			}
		}
	}
}
