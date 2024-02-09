package in.cdac.bookmyrideclient.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import in.cdac.bookmyrideclient.enums.Roles;
import in.cdac.bookmyrideclient.model.UserLoginForm;
import in.cdac.bookmyrideclient.model.Users;
import in.cdac.bookmyrideclient.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomePageController {

	@Autowired
	UsersService usersService;

	@GetMapping(path = "/")
	public ModelAndView indexPage(@ModelAttribute("userLoginForm") UserLoginForm userLoginForm) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}

	@GetMapping(path = "/login")
	public ModelAndView login(@ModelAttribute("userLoginForm") UserLoginForm userLoginForm) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}

	@PostMapping(path = "/login")
	public String userLogin(@Valid @ModelAttribute("userLoginForm") UserLoginForm userLoginForm,
			BindingResult validationErrorResult, HttpServletRequest request) {
		if (validationErrorResult.hasErrors()) {
			return "index";
		} else {
			Users u = usersService.getUser(userLoginForm);
			Users loggedInUser = usersService.getLoginUser(u);
			if (loggedInUser.getUserId() == null) {
				validationErrorResult.rejectValue("password", "error.userLoginForm", "Invalid Email or password.");
			}
			if (validationErrorResult.hasErrors()) {
				return "index";
			}
			HttpSession session = request.getSession(true);
			session.setAttribute("user", loggedInUser);
			if (loggedInUser.getRole().equals(Roles.ADMIN)) {
				return "redirect:/admin-dashboard";
			} else if (loggedInUser.getRole().equals(Roles.DRIVER)) {
				return "redirect:/driver-dashboard";
			} else {
				return "redirect:/user-dashboard";
			}

		}
	}

	@RequestMapping(path = "/logout")
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/";
	}

}