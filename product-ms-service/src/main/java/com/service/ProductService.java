package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDAO;
import com.model.Product;

@Service
public interface ProductService {
	
	public Product addProduct(Product product);
	
	public List<Product> getProducts();
	
	public List<Product> getProductsByIds(List<Long> pList);
}
