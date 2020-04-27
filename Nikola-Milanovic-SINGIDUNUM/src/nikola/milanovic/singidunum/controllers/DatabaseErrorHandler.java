package nikola.milanovic.singidunum.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DatabaseErrorHandler {

	 @ExceptionHandler(Exception.class)
	    public String handleDatabaseException(Exception e) {
	        return "error";
	    }
}
