package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDAO;
import com.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO dao;
	
	public Product addProduct(Product product) {
		return dao.save(product);
	}
	
	public List<Product> getProducts(){
		return (List<Product>) dao.findAll();
	}
	
	public List<Product> getProductsByIds(List<Long> pList){
		return (List<Product>) dao.findAllById(pList);
	}
}
