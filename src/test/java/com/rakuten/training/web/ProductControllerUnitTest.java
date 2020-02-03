package com.rakuten.training.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;
import com.rakuten.training.web.ProductController;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class ProductControllerUnitTest  {

	@Autowired
	MockMvc mockMvc;
	@MockBean // asks spring to mock bean and inject this mock 
	ProductService service;
	@MockBean
	ReviewService ser;
	@MockBean
	ProductDAO DAO;

	@Test
	public void getProductById_Returns_OK_With_Correct_Product_If_Found() throws Exception {
		//arrange
		Product found=new Product("test",123.0f,100);
		
		int id=1;
		found.setId(id);
		Mockito.when(service.findById(id)).thenReturn(found);
		//act and assert
		//mockMvc.perform(MockMvcRequestBuilders.ge)
		mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}",1))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)));
	}
	@Test
	public void addProduct_Returns_CREATED_With_Value_GT_10000() throws Exception
	{
		//arrange
		Product added = new Product("test", 1.0f, 0);
	    int id = 2;
	    added.setId(id);

	    ObjectMapper mapper = new ObjectMapper();

	    // Act & Assert
	    Mockito.when(service.addNewProduct(Mockito.any(Product.class))).thenReturn(id);
	    
	    mockMvc
	        .perform(
	            MockMvcRequestBuilders.post("/products")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(mapper.writeValueAsString(added)))
	        .andExpect(MockMvcResultMatchers.status().isCreated())
	        .andExpect(MockMvcResultMatchers.header().string("location", "/products/"+ id));

	    Mockito.verify(service, Mockito.times(1)).addNewProduct(Mockito.any(Product.class));
	}
//	@Test
//	public void addProduct_Returns_BADREQUEST_With_Value_LT_10000() throws Exception
//	{
//		Product added = new Product("test", 123.0f, 1);
//	    int id = 2;
//	    added.setId(id);
//	    ObjectMapper mapper = new ObjectMapper();
//	    Product threw=null;
//
// Mockito.when(service.addNewProduct(Mockito.any(Product.class))).thenThrow(IllegalArgumentException.class);
//	    
//	    mockMvc
//	        .perform(
//	            MockMvcRequestBuilders.post("/products")
//	                .contentType(MediaType.APPLICATION_JSON)
//	                .content(mapper.writeValueAsString(added)))
//	        .andExpect(MockMvcResultMatchers.status().isBadRequest()); 
//	}
	@Test
	void deleteProductById_Returns_OK_If_Deleted() throws Exception
	{
		Product p=new Product("test",123333.0f,100);
		int id=1;
		p.setId(id);
		//try {
		Mockito.doNothing().when(service).removeProduct(id);
		//service.removeProduct(id);
		
		//}
		//catch(IllegalArgumentException e)
		//{
			mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}",id))
			.andExpect(MockMvcResultMatchers.status().isOk());
			//service.removeProduct(id);
			Mockito.verify(service,Mockito.times(1)).removeProduct(id);
		//}
	}
//	@Test
//	void deleteProductById_Returns_BADREQ_If_NotDeleted() throws Exception
//	{
//		Product p=new Product("test",333333.0f,1);
//		int id=1;
//		p.setId(id);
//		//try {
//		Mockito.doThrow(IllegalArgumentException.class).when(service).removeProduct(id);
//		//service.removeProduct(id);
//		
//		//}
//		//catch(IllegalArgumentException e)
//		//{
//			mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}",id))
//			.andExpect(MockMvcResultMatchers.status().isBadRequest());
//			//service.removeProduct(id);
//			Mockito.verify(service,Mockito.times(1)).removeProduct(id);
//		//}
//		
//	}
	

}
