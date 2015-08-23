package ua.pp.kaeltas.pizzaorders.web;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.exception.NotFoundPizzaException;
import ua.pp.kaeltas.pizzaorders.service.PizzaService;

public class AbstractPizzaController {

	@Autowired
    protected PizzaService pizzaService;

    protected Pizza getPizzaById(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID<0");
        }
        Pizza pizza = pizzaService.getById(id);
        if (pizza == null) {
            throw new NotFoundPizzaException("Pizza id" + id + " not found");
        }
        return pizza;
    }

    @InitBinder
    private void pizzaBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Pizza.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String pizzaId) {
                        Pizza pizza = null;
                        if (pizzaId != null && !pizzaId.trim().isEmpty()) {
                            Integer id = Integer.valueOf(pizzaId);
                            pizza = getPizzaById(id);
                        }
                        setValue(pizza);
                    }
                }
        );
    }
	
}
