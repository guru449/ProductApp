package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rakuten.training.domain.Product;

@Repository
@Transactional
public class ProductDAOJpaImpl implements ProductDAO {
	@Autowired
	EntityManager em;

	@Override
	public Product save(Product toBeSaved) {
		// TODO Auto-generated method stub
		
		em.persist(toBeSaved);
		return toBeSaved;
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		Product p=em.find(Product.class, 1);
		//System.out.println("this product has"+p.getReviews().size()+"reviews");
		return em.find(Product.class,id);
		
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		Query q=em.createQuery("select p from Product p");
		List<Product>all=q.getResultList();
		return all;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		//Product p=em.find(Product.class, id);
		Product p=em.getReference(Product.class, id);//optimization
		em.remove(p);
		
//		Query q=em.createQuery("delete from Product p where p.id=:idParam ");
//		q.setParameter("idParam", id);
//		q.executeUpdate();
	}

	

}
