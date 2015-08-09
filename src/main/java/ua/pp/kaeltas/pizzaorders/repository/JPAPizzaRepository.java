package ua.pp.kaeltas.pizzaorders.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;
import ua.pp.kaeltas.pizzaorders.domain.PizzaType;

@Repository
public class JPAPizzaRepository implements PizzaRepository {

	@PersistenceContext(unitName="HibernatePostgreSQL")
	EntityManager em;
	
	@Override
	public Pizza getPizzaByID(Integer id) {
		return em.find(Pizza.class, id);
	}

	@Override
	@Transactional
	public void savePizza(Pizza pizza) {
		em.persist(pizza);
	}

	@Override
	public List<Pizza> getAllPizzas() {
		
		TypedQuery<Pizza> typedQuery = em.createQuery("select p from Pizza p", Pizza.class);
		return typedQuery.getResultList();
		
	}

	@Override
	@Transactional
	public void createDefaultPizzas() {
		if (getAllPizzas().isEmpty()) {
			
			Pizza pizza = new Pizza();
			pizza.setName("4 Meat");
			pizza.setPrice(66);
			pizza.setType(PizzaType.MEAT);
			savePizza(pizza);
			
			Pizza pizza2 = new Pizza();
			pizza2.setName("4 Cheese");
			pizza2.setPrice(54);
			pizza2.setType(PizzaType.VEGETARIAN);
			savePizza(pizza2);
			
			Pizza pizza3 = new Pizza();
			pizza3.setName("Mussels");
			pizza3.setPrice(82);
			pizza3.setType(PizzaType.SEA);
			savePizza(pizza3);

		}
		
	}

}
