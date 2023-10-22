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
	
	@GetMapping("/getProducts")
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
	
	@GetMapping(value="/getProducts/{plist}")
	public ResponseEntity<?> getProductsByIds(@PathVariable List<Long> plist){
		try {
			List<Product> products = productService.getProductsByIds(plist);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(products);
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during fetching a product using productIds. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
	
	/*
	 * addProducts - This api is used to add products
	 */
	
	@PostMapping("/addProduct")
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
	
	@PutMapping("/updateProductById")
	public ResponseEntity<String> updateProductDetailsById(@RequestParam(name="productId", required=true) Long productId, @RequestBody Product product) {
		try {
			productService.updateProductDetailsById(productId, product);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product details has been updated!");
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during updating product details. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
	
	/*
	 * updateProductDetailsById - this api is used to update:
	 * 							  Product quantity
	 */
	
	@PutMapping("/updateProductQuantityById")
	public ResponseEntity<String> updateProductQuantityById(@RequestParam(name="productId", required=true) Long productId, @RequestBody Product product) {
		try {
			productService.updateProductQuantityById(productId, product);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product quantity has been updated!");
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during updating product quantity. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
	
	/*
	 * updateProductDetailsById - this api is used to update:
	 * 							  Product amount
	 */
	
	@PutMapping("/updateProductAmountById")
	public ResponseEntity<String> updateProductAmountById(@RequestParam(name="productId", required=true) Long productId, @RequestBody Product product) {
		try {
			productService.updateProductAmountById(productId, product);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product amount has been updated!");
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during updating product amount. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
	
	/*
	 * updateProductDetailsById - this api is used to update:
	 * 							  Product amount
	 * 							  Product quantity
	 * 							  Product name
	 */
	
	@PutMapping("/updateProductNameById")
	public ResponseEntity<String> updateProductNameById(@RequestParam(name="productId", required=true) Long productId, @RequestBody Product product) {
		try {
			productService.updateProductNameById(productId, product);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product name has been updated!");
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during updating product name. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
		
	/*
	 * deleteProductById - this api is used to delete product by id:
	 */
	
	@PutMapping("/deleteProducById")
	public ResponseEntity<String> deleteProductById(@RequestParam(name="productId", required=true) Long productId) {
		try {
			productService.deleteProductById(productId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product has been deleted!");
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during deleting product. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
	
	/*
	 * updateProductDetailsById - this api is used to update:
	 * 							  Product amount
	 * 							  Product quantity
	 * 							  Product name
	 */
	
	@PutMapping("/deleteAllProducts")
	public ResponseEntity<String> deleteAllProducts() {
		try {
			productService.deleteAllProducts();
			return ResponseEntity.status(HttpStatus.OK).body("All product has been deleted!");
		} catch(Exception ex) {
			String exceptionMessage = "Exception occured during deleting product. Excetion msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
	
}
