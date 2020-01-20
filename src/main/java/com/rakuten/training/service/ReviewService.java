package com.rakuten.training.service;

import java.util.List;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Review;

public interface ReviewService {
	
	public int addReview(int pid,Review toBeAdded);
	public List<Review> getByProductId(int pid);

}
