package in.cdac.bookmyrideapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.bookmyrideapi.entity.Driver;
import in.cdac.bookmyrideapi.repositories.DriverDAO;

@RestController
@RequestMapping("/driver")
public class DriverController {
	
	@Autowired
	DriverDAO driverDAO;
	
	@GetMapping("/getDrivers")
	public List<Driver> getAllDrivers() {
		return driverDAO.findAll();
	}
	
	@PostMapping("/saveDrivers")
	public Driver saveDriver(@RequestBody Driver driver) {
		return driverDAO.save(driver);
	}
}
