package com.rakuten.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;
import com.rakuten.training.service.NoSuchProductException;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

@RestController
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/products/{id}/reviews")
	public ResponseEntity<List<Review>> getReviewsForProduct(@PathVariable("id")int pid)
	{
		Product p=productService.findById(pid);
		if(p==null)
		{
			return new ResponseEntity<List<Review>>(HttpStatus.NOT_FOUND);
		}
		else
			{ List<Review>reviews=reviewService.getByProductId(pid);
			return new ResponseEntity<List<Review>>(reviews,HttpStatus.OK);
			}
	}
	@PostMapping("/products/{id}/reviews")
	public ResponseEntity<Review>addReviewToProduct(@PathVariable("id")int pid,@RequestBody Review review)
	{
		try {
			int id=reviewService.addReview(pid, review);
			HttpHeaders headers=new HttpHeaders();
			headers.setLocation(URI.create("/products/"+pid+"/reviews/"+id));
			return new ResponseEntity<Review>(review,headers,HttpStatus.CREATED);
		} 
		catch(NoSuchProductException e)
		{
			return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
		}
	}

}
