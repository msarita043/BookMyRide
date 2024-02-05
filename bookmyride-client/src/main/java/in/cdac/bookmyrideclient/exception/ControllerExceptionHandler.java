package in.cdac.bookmyrideclient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String errorPage404() {
		return "error";
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public String errorPage500() {
		return "error";
	}
}
