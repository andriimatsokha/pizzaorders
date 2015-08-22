package ua.pp.kaeltas.pizzaorders.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pp.kaeltas.pizzaorders.domain.AccumulativeCard;

@Repository
public class JPAAccumulativeCardRepository implements AccumulativeCardRepository {

	@PersistenceContext(unitName = "HibernatePostgreSQL")
	EntityManager em;
	
	@Override
	public AccumulativeCard find(Integer id) {
		return em.find(AccumulativeCard.class, id);
	}

	@Override
	@Transactional
	public Integer save(AccumulativeCard accumulativeCard) {
		em.persist(accumulativeCard);
		return accumulativeCard.getId();
	}

	@Override
	@Transactional
	public void update(AccumulativeCard accumulativeCard) {
		em.merge(accumulativeCard);
	}

}
