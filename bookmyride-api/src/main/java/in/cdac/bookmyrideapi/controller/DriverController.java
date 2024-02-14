package in.cdac.bookmyrideapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.bookmyrideapi.entity.CarType;
import in.cdac.bookmyrideapi.entity.Driver;
import in.cdac.bookmyrideapi.entity.Users;
import in.cdac.bookmyrideapi.repositories.DriverDAO;
import in.cdac.bookmyrideapi.repositories.UsersDAO;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	DriverDAO driverDAO;
	
	@Autowired
	UsersDAO usersDAO;

	@GetMapping("/getAllDrivers")
	public List<Driver> getAllDrivers() {
		return driverDAO.findAll();
	}

	@PostMapping("/saveDrivers")
	public Driver saveDriver(@RequestBody Driver driver) {
		return driverDAO.save(driver);
	}

	@GetMapping("/getDriver/{driverId}")
	public Optional<Driver> getDriver(@PathVariable Integer driverId) {
		return driverDAO.findById(driverId);
	}

	/*
	 * @PostMapping("/restaurant/{id}/update") public Restaurant
	 * updateRestaurant(@PathVariable Long id, @RequestBody Restaurant
	 * restaurantDetails, BindingResult bindingResult) { Optional<Restaurant>
	 * restaurantOptional = restaurantService.findById(id);
	 * 
	 * if (restaurantOptional.isPresent()) { Restaurant restaurant =
	 * restaurantOptional.get(); restaurant.setName(restaurantDetails.getName());
	 * restaurant.setLocation(restaurant.getLocation());
	 * restaurant.setDescription(restaurantDetails.getDescription());
	 * logger.info("restaurant information edited successfully"); return
	 * restaurantService.save(restaurant); } else return null; }
	 */

	@PostMapping("/updateDriver/{driverId}")
	public Driver updateDriver(@PathVariable Integer driverId, @RequestBody Driver driver) {
		Optional<Driver> driverOptional = driverDAO.findById(driverId);
		if (driverOptional.isPresent()) {
			Driver d = driverOptional.get();
			Users u = d.getUsers();
			u.setName(driver.getUsers().getName());
			Users updatedUsers= usersDAO.save(u);
			d.setUsers(updatedUsers);
			d.setCarType(driver.getCarType());
			d.setCarModel(driver.getCarModel());
			d.setCarRegNo(driver.getCarRegNo());
			d.setActivationStatus(driver.getActivationStatus());
			return driverDAO.save(d);
		} else
			return null;
	}
}
