package ua.pp.kaeltas.pizzaorders.web;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.service.PizzaService;

@RestController
@RequestMapping("rest")
public class PizzaRestController extends AbstractPizzaController {

	private final Logger logger = LogManager.getLogger(PizzaRestController.class);
	
	@Autowired
	private PizzaService pizzaService;
	
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@RequestMapping(value = "/pizza", method = RequestMethod.GET)
	public ResponseEntity<List<Pizza>> getAllPizzas() {
		
		return new ResponseEntity<List<Pizza>>( pizzaService.getAllPizzas(), HttpStatus.OK); 
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@RequestMapping(value = "/pizza/{pizzaId}", method = RequestMethod.GET)
	public ResponseEntity<Pizza> getPizza(@PathVariable("pizzaId") Pizza pizza) {
		
		if (pizza == null) {
			return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Pizza>(pizza, HttpStatus.OK); 
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/pizza", method = RequestMethod.POST,
			headers = "Content-Type=application/json")
	public ResponseEntity<Void> createNewPizza(@RequestBody Pizza pizza, 
            UriComponentsBuilder builder) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.debug(authentication.getName() 
				+ " " + authentication.getAuthorities());
		
		logger.debug("REST: create new pizza " + pizza);
		
		pizzaService.save(pizza);
		
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/rest/pizza/{id}")
                .buildAndExpand(pizza.getId()).toUri());     
		
		return new ResponseEntity<>(headers, HttpStatus.OK); 
	}
	
	
}
