package in.cdac.bookmyrideclient.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.cdac.bookmyrideclient.model.AddNewDriverForm;
import in.cdac.bookmyrideclient.model.CarType;
import in.cdac.bookmyrideclient.model.Driver;
import in.cdac.bookmyrideclient.model.Users;

@Service
public class DriverService {
	@Autowired
	WebClient webClient;
	
	@Autowired
	CarTypeService carTypeService;
	@Autowired
	UsersService usersService;
	
	public Driver getDriver(AddNewDriverForm addNewDriverForm, Users users) {
		Driver d = new Driver();
		d.setUsers(users);
		Optional<CarType> carType = carTypeService.getCarTypeById(addNewDriverForm.getCarTypeId());
		d.setCarType(carType.get());
		d.setCarModel(addNewDriverForm.getCarModel());
		d.setCarRegNo(addNewDriverForm.getCarRegNo());
		d.setActivationStatus(addNewDriverForm.getActivationStatus());		
		return d;
	}
	
	public Driver addNewDriver(Driver driver) {
		return webClient.post().uri("/driver/saveDrivers").bodyValue(driver).retrieve().bodyToMono(Driver.class).block();
	}
	
}
