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
		Product productToBeUpdated = findProductToBeUpdated(productId);
		
		try {
			productToBeUpdated.setAmount(product.getAmount());
			productToBeUpdated.setProductName(product.getProductName());
			productToBeUpdated.setQuantity(product.getQuantity());
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in updateProductDetailsById. Exception msg: ";
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
		return dao.save(productToBeUpdated);
	}

	@Override
	public Product updateProductQuantityById(Long productId, Product product) {	
		Product productToBeUpdated = findProductToBeUpdated(productId);
		
		try {
			productToBeUpdated.setQuantity(product.getQuantity());
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in updateProductQuantityById. Exception msg: ";
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
		
		return dao.save(productToBeUpdated);
	}

	@Override
	public Product updateProductAmountById(Long productId, Product product) {
		Product productToBeUpdated = findProductToBeUpdated(productId);
		
		try {
			productToBeUpdated.setAmount(product.getAmount());
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in updateProductAmountById. Exception msg: ";
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
		
		return dao.save(productToBeUpdated);
	}

	@Override
	public Product updateProductNameById(Long productId, Product product) {
		Product productToBeUpdated = findProductToBeUpdated(productId);
		
		try {
			productToBeUpdated.setProductName(product.getProductName());
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in updateProductNameById. Exception msg: ";
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
		
		return dao.save(productToBeUpdated);
	}
	
	private Product findProductToBeUpdated(Long productId) {
		Product productToBeUpdated = dao.findById(productId).orElse(null);
		if(productToBeUpdated == null) {
			throw new ProductException("Product doesn't exist!");
		}
		
		return productToBeUpdated;
	}

	@Override
	public void deleteProductById(Long productId) {
		Product productToBeUpdated = findProductToBeUpdated(productId);

		try {
			dao.delete(productToBeUpdated);
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in deleteProductById. Exception msg: ";
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
	}
	
	@Override
	public void deleteAllProducts() {
		try {
			dao.deleteAll();
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in deleteAllProducts. Exception msg: ";
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
	}
}
