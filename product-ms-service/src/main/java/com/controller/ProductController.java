package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
//
	@Autowired
	ProductService service;
	
	
	@GetMapping("/getproducts")
	public List<Product> findAll(){
//		System.out.println("Data from products props: " + message);
		return service.getProducts();
	}
	
	@PostMapping("/addproduct")
	public Product insert(@RequestBody Product product) {
		return service.addProduct(product);
	}
	
	@GetMapping("/getproducts/{plist}")
	public List<Product> getProductsByIds(@PathVariable List<Long> plist){
		return service.getProductsByIds(plist);
	}
}
