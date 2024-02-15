package in.cdac.bookmyrideclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DriverService {
	@Autowired
	WebClient webClient;
	
	
	
}
