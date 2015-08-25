package ua.pp.kaeltas.pizzaorders.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlingController {

	private final Logger logger = LogManager.getLogger(ExceptionHandlingController.class);
	
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView generalException(Exception exception) {
		
		logger.error("Default exception handler", exception);
		
		ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", exception);
	    mav.setViewName("generalException");
	    
	    return mav;
	}
	
}
