package in.cdac.bookmyrideclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import in.cdac.bookmyrideclient.model.UserSignUpForm;
import jakarta.validation.Valid;

@Controller
public class UserSignupController {

	@GetMapping("/user-signup")
	public String userSignup(@ModelAttribute("userSignupForm") UserSignUpForm userSignupForm, Model model) {
		return "userSignUp";
	}

	@PostMapping("/user-signup")
	public String userSignup(@Valid @ModelAttribute("userSignupForm") UserSignUpForm userSignupForm,
			BindingResult validationErrorResult, Model model) {
		if (userSignupForm.getConfirmPassword() == null || userSignupForm.getConfirmPassword().isEmpty()
				|| !(userSignupForm.getPassword().equals(userSignupForm.getConfirmPassword()))) {
			validationErrorResult.rejectValue("confirmPassword", "error.userSignupForm", "Should be same as password.");
		}
		if (validationErrorResult.hasErrors()) {
			return "userSignUp";
		} else {
			return "redirect:/user-signup";
		}
	}
}
