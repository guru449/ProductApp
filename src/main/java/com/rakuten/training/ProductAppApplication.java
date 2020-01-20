package com.rakuten.training;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

@SpringBootApplication
public class ProductAppApplication {

	public static void main(String[] args) {
		//ApplicationContext springContainer=
				SpringApplication.run(ProductAppApplication.class, args);
	//ProductConsoleUI ui=springContainer.getBean(ProductConsoleUI.class);
//ui.createProductWithUI();
//ReviewDAO reviewDAO=springContainer.getBean(ReviewDAO.class);
//Review sample=new Review("self","this is good",5);
//Review savedReview=reviewDAO.save(sample, 1);
//System.out.println("created review with id "+savedReview.getId());
//		ProductDAO productDAO=springContainer.getBean(ProductDAO.class);
//		Product p=productDAO.findById(1);
//		System.out.println(p.getName());
//		System.out.println("this product has"+p.getReviews().size()+"reviews");
//		ProductDAO productDAOO=springContainer.getBean(ProductDAO.class);
//		productDAOO.deleteById(1);
//		ProductDAO productDAOOO=springContainer.getBean(ProductDAO.class);
//		List<Product>listpro=productDAOOO.findAll();
//		for(int i=0;i<listpro.size();i++)
//		{
//			System.out.println(listpro.get(i).getId()+" " + listpro.get(i).getName());
//			
//		}
		
}
}
