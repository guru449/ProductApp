package com.rakuten.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.dal.ReviewDAO;
import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{
	
   @Autowired
	ReviewDAO ReviewDAO;
	
   @Autowired
   ProductDAO productDAO;

@Override
public int addReview(int pid, Review toBeAdded) {
	// TODO Auto-generated method stubot
	Product product=productDAO.findById(pid);
	if(product==null)
		throw new NoSuchProductException();
	toBeAdded.setProduct(product);
	Review added=ReviewDAO.save(toBeAdded);
	return added.getId();
}

@Override
public List<Review> getByProductId(int pid) {
	// TODO Auto-generated method stub
	return ReviewDAO.findByProductId(pid);
}
	

}
