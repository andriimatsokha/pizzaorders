package ua.pp.kaeltas.pizzaorders.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pp.kaeltas.pizzaorders.domain.Pizza;

@Repository
public class JPAPizzaRepository implements PizzaRepository {

	@PersistenceContext(unitName="HibernatePostgreSQL")
	EntityManager em;
	
	@Override
	public Pizza getPizzaByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
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

}
