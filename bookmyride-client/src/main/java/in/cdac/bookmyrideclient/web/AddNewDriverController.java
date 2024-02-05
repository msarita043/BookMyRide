package in.cdac.bookmyrideclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddNewDriverController {

	@GetMapping("/add-new-driver")
	public String addNewDriver() {
		return "addNewDriver";
	}
}
