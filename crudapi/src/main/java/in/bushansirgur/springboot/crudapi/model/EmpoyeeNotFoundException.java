package in.bushansirgur.springboot.crudapi.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class EmpoyeeNotFoundException extends RuntimeException {

	public EmpoyeeNotFoundException(String exception) {
		// TODO Auto-generated constructor stub
		super(exception);
	}

}
