package com.rakuten.training.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

public class ProductServiceImplTest {

	@Test
	public void addNewProduct_Returns_Valid_Id_When_ProductVaule_GTEQ_MInValue() {
		///aaa
		//arrange
		ProductServiceImpl service=new ProductServiceImpl();
		Product toBeAdded=new Product("test",10000,2);//notice 10000*2>=10000
		ProductDAO mockDAO=Mockito.mock(ProductDAO.class);
		Product saved=new Product("test",10000,2);
		saved.setId(1);
		Mockito.when(mockDAO.save(toBeAdded)).thenReturn(saved);
		service.setDao(mockDAO);
		//act
		int id=service.addNewProduct(toBeAdded);
		//assert
		assertTrue(id>0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void addNewProduct_Throws_When_ProductVaule_LT_MaxValue() {
		//arrange
		ProductServiceImpl service=new ProductServiceImpl();
		Product toBeAdded=new Product("test",9999,1);
		//act
		service.addNewProduct(toBeAdded);
	}
	@Test
	public void deleteProduct_If_LessThan_MinValue()
	{
		ProductServiceImpl service=new ProductServiceImpl();
		Product saved=new Product("test",1000,2);
		saved.setId(1);
		ProductDAO mockDAO=Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.findById(1)).thenReturn(saved);
		service.setDao(mockDAO);
		service.removeProduct(saved.getId());
		Mockito.verify(mockDAO).deleteById(saved.getId());
		
	}
	@Test(expected = IllegalStateException.class)
	public void deleteProduct_Returns_Exception_If_Null()
	{
		ProductServiceImpl service=new ProductServiceImpl();
		Product saved=null;
		ProductDAO mockDAO=Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.findById(1)).thenReturn(saved);
		service.setDao(mockDAO);
		service.removeProduct(1);
		
	}
	@Test(expected = IllegalStateException.class)
	public void deleteProduct_If_Greater_MinValue_Throw_Excpetion()
	{
		ProductServiceImpl service=new ProductServiceImpl();
		Product saved=new Product("test",100000,2);
		saved.setId(1);
		ProductDAO mockDAO=Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.findById(1)).thenReturn(saved);
		service.setDao(mockDAO);
		service.removeProduct(saved.getId());
		Mockito.verify(mockDAO);
		

}
}
