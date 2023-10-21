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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	@GetMapping(value="/getProducts", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll(){
		try {
			return ResponseEntity.ok(productService.getProducts());
		}
		catch (Exception ex) {
			String exceptionMessage = "Exception occured wile fetching all products. Exception msg: ";
			return new ResponseEntity<String>(exceptionMessage + ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/getProducts/{plist}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProductsByIds(@PathVariable List<Long> plist){
		try {
			return ResponseEntity.ok(productService.getProductsByIds(plist));
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during inserting a product using productIds. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
	
	@PostMapping(value="/addProduct", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insert(@RequestBody Product product) {
		try {
			productService.addProduct(product);
			return ResponseEntity.status(HttpStatus.OK).body("Product has been added!");
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during inserting a product. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
}
