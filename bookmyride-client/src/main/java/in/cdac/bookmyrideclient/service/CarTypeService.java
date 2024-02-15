package in.cdac.bookmyrideclient.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.cdac.bookmyrideclient.model.CarType;

@Service
public class CarTypeService {

	@Autowired
	WebClient webClient;

	public List<CarType> getAllCarTypes() {
		return webClient.get().uri("/carType/getAllCarTypes").retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<CarType>>() {
				}).block();
	}

}
