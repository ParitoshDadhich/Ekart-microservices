package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDAO;
import com.exception.ProductException;
import com.model.Product;
import com.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO dao;

	public Product addProduct(Product product) {
		return dao.save(product);
	}

	public List<Product> getProducts() {
		return (List<Product>) dao.findAll();
	}

	public List<Product> getProductsByIds(List<Long> pList) {
		return (List<Product>) dao.findAllById(pList);
	}

	@Override
	public Product updateProductDetailsById(Long productId, Product product){
		Product productToBeUpdated = dao.findById(productId).orElse(null);
		if(productToBeUpdated == null) {
			throw new ProductException("Product doesn't exist!");
		}
		try {
			productToBeUpdated.setAmount(product.getAmount());
			productToBeUpdated.setProductName(product.getProductName());
			productToBeUpdated.setQuantity(product.getQuantity());
		} catch(Exception ex) {
			String exceptionMessage = "Error occured while updatingProductDetailsById. Exception msg: ";
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
		return dao.save(productToBeUpdated);
	}
}
