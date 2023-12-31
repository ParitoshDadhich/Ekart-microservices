package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.dao.ProductDAO;
import com.model.Product;

public interface ProductService {
	
	public Product addProduct(Product product);
	
	public List<Product> getProducts();
	
	public List<Product> getProductsByIds(List<Long> pList);
	
	public Product updateProductDetailsById(Long productId, Product product);

	public Product updateProductQuantityById(Long productId, Product product);

	public Product updateProductAmountById(Long productId, Product product);

	public Product updateProductNameById(Long productId, Product product);

	public void deleteProductById(Long productId);

	void deleteAllProducts();
}
