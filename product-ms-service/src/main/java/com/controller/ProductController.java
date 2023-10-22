package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Product;
import com.service.ProductService;

@RestController
@RequestMapping("/products")
@RefreshScope
public class ProductController {
//	@Value("${product.message}")
//	String message;

	@Autowired
	ProductService productService;
	private ObjectMapper objectMapper;
	
	/*
	 * getAllProducts - This product is used to fetch all the products
	 * 					It returns 
	 */
	
	@GetMapping(value="/getProducts", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllProducts(){
		try {
			objectMapper = new ObjectMapper();
			List<Product> products = productService.getProducts();
			String allProducts = objectMapper.writeValueAsString(products);
			return ResponseEntity.ok(allProducts);
		}
		catch (Exception ex) {
			String exceptionMessage = "Exception occured wile fetching all products. Exception msg: ";
			return new ResponseEntity<String>(exceptionMessage + ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * getProductsByIds - This api is used to fetch product details with their productIds
	 */
	
	@GetMapping(value="/getProducts/{plist}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProductsByIds(@PathVariable List<Long> plist){
		try {
			return ResponseEntity.ok(productService.getProductsByIds(plist));
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during inserting a product using productIds. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
	
	/*
	 * addProducts - This api is used to add products
	 */
	
	@PostMapping(value="/addProduct", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProducts(@RequestBody Product product) {
		try {
			productService.addProduct(product);
			return ResponseEntity.status(HttpStatus.OK).body("Product has been added!");
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during inserting a product. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
	
	/*
	 * updateProductDetailsById - this api is used to update:
	 * 							  Product amount
	 * 							  Product quantity
	 * 							  Product name
	 */
	
	@PutMapping(value="/updateProductById", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProductDetailsById(@RequestParam(name="productId", required=true) Long productId, @RequestBody Product product) {
		try {
			productService.updateProductDetailsById(productId, product);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product details has been updated!");
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during updating product details. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
}
