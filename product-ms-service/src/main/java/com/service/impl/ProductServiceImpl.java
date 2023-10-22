package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	public Product addProduct(Product product) {
		logger.info("Product has been added to DB");
		return dao.save(product);
	}

	public List<Product> getProducts() {
		logger.info("Fetching all product details");
		return (List<Product>) dao.findAll();
	}

	public List<Product> getProductsByIds(List<Long> pList) {
		logger.info("Fetching product details by product Ids");
		List<Product> products = new ArrayList<>();
		
		for (Long productId : pList) {
            Product product = dao.findById(productId).orElse(null);

            if (product == null) {
                throw new ProductException("Product with ID " + productId + " not found in the DB");
            }

            products.add(product);
        }

		logger.info("Product details by product Ids has been fetched!");
        return products;
	}

	@Override
	public Product updateProductDetailsById(Long productId, Product product){
		logger.info("Finding product to be updated");
		Product productToBeUpdated = findProductToBeUpdated(productId);
		logger.info("Product has been found");
		
		try {
			logger.info("Updating product details");
			productToBeUpdated.setAmount(product.getAmount());
			productToBeUpdated.setProductName(product.getProductName());
			productToBeUpdated.setQuantity(product.getQuantity());
			logger.info("Product details has been updated");
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in updateProductDetailsById. Exception msg: ";
			logger.error(exceptionMessage + ex.getMessage());
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
		
		logger.info("Saving updated product details into DB");
		return dao.save(productToBeUpdated);
	}

	@Override
	public Product updateProductQuantityById(Long productId, Product product) {	
		logger.info("Finding product to be updated");
		Product productToBeUpdated = findProductToBeUpdated(productId);
		logger.info("Product has been found");
		
		try {
			logger.info("Updating product quantity");
			productToBeUpdated.setQuantity(product.getQuantity());
			logger.info("Product quantity has been updated");
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in updateProductQuantityById. Exception msg: ";
			logger.error(exceptionMessage + ex.getMessage());
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
		
		logger.info("Saving updated product quantity into DB");
		return dao.save(productToBeUpdated);
	}

	@Override
	public Product updateProductAmountById(Long productId, Product product) {
		logger.info("Finding product to be updated");
		Product productToBeUpdated = findProductToBeUpdated(productId);
		logger.info("Product has been found");
		
		try {
			logger.info("Updating product amount");
			productToBeUpdated.setAmount(product.getAmount());
			logger.info("Product amount has been updated");
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in updateProductAmountById. Exception msg: ";
			logger.error(exceptionMessage + ex.getMessage());
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
		
		logger.info("Saving updated product amount into DB");
		return dao.save(productToBeUpdated);
	}

	@Override
	public Product updateProductNameById(Long productId, Product product) {
		logger.info("Finding product to be updated");
		Product productToBeUpdated = findProductToBeUpdated(productId);
		logger.info("Product has been found");
		
		try {
			logger.info("Updating product name");
			productToBeUpdated.setProductName(product.getProductName());
			logger.info("Product name has been updated");
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in updateProductNameById. Exception msg: ";
			logger.error(exceptionMessage + ex.getMessage());
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
		
		logger.info("Saving updated product name into DB");
		return dao.save(productToBeUpdated);
	}
	
	private Product findProductToBeUpdated(Long productId) {
		logger.info("Fetching product details by productId from DB");
		Product productToBeUpdated = dao.findById(productId).orElse(null);
		logger.info("Product has been fetched from DB successfully!");
		if(productToBeUpdated == null) {
			throw new ProductException("Product doesn't exist!");
		}
		
		return productToBeUpdated;
	}

	@Override
	public void deleteProductById(Long productId) {
		logger.info("Finding product to be deleted");
		Product productToBeUpdated = findProductToBeUpdated(productId);
		logger.info("Product has been found");

		try {
			logger.info("Deleting product from DB");
			dao.delete(productToBeUpdated);
			logger.info("Product has been deleted");
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in deleteProductById. Exception msg: ";
			logger.error(exceptionMessage + ex.getMessage());
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
	}
	
	@Override
	public void deleteAllProducts() {
		try {
			logger.info("Deleting all products from DB");
			dao.deleteAll();
			logger.info("All products has been deleted!");
		} catch(Exception ex) {
			String exceptionMessage = "Error occured in deleteAllProducts. Exception msg: ";
			logger.error(exceptionMessage + ex.getMessage());
			throw new ProductException(exceptionMessage + ex.getMessage());
		}
	}
}
