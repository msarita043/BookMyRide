package in.cdac.bookmyrideapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.bookmyrideapi.entity.CarType;
import in.cdac.bookmyrideapi.repositories.CarTypeDAO;

@RestController
@RequestMapping("/carType")
public class CarTypeController {

	@Autowired
	CarTypeDAO carTypeDao;
	
	@GetMapping("/getAllCarTypes")
	public List<CarType> getAllCarTypes() {
		return carTypeDao.findAll();
	}
	
}
