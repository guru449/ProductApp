package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;

@Repository
@Transactional
public class ReviewDAOJpaImpl implements ReviewDAO {
	@Autowired
	EntityManager em;

	// crud
	@Override
	public Review findById(int id) {
		return em.find(Review.class, id);
	}

	@Override
	public void deleteById(int id) {
		Review r = em.find(Review.class, id);
		em.remove(r);
	}

	@Override
	public List<Review> findAll() {
		return null;
	}

	@Override
	public Review save(Review toBeSaved) {
		// TODO Auto-generated method stub
		
		
		em.persist(toBeSaved);
		return toBeSaved;
	}

	@Override
	public List<Review> findByProductId(int pid) {
		// TODO Auto-generated method stub
		TypedQuery<Review> q=
				em.createQuery("select r form REview r where r.product.id=:pid", Review.class);
		q.setParameter("pid", pid);
		return q.getResultList();
	}

}
