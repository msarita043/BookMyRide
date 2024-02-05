package in.cdac.bookmyrideclient.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import in.cdac.bookmyrideclient.service.UsersService;

@Controller
public class HomePageController {
	
	@Autowired
	UsersService usersService;
	
	@RequestMapping(path = "/")
	public ModelAndView indexPage() {
		ModelAndView mv = new ModelAndView();
		//mv.addObject("userName", "Sarita");
		
		mv.addAllObjects(Map.of("userName", usersService.getEmail(1),
				"number", List.of(1,2,3,4)));
		mv.setViewName("index");
		return mv;
	}
	
}