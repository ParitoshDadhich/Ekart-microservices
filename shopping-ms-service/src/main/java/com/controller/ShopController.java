package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.CartRequest;
import com.model.CartResponse;
import com.service.ShoppingService;

@RestController
@RequestMapping("/shoppingCart")
public class ShopController {

	@Autowired
	private ShoppingService shoppingService;

	/*
	 * addProduct - use to add products into the cart and calculate total cost of cart
	 * userId - user'id
	 * requestList - products that are to be added into the cart
	 */
	@PostMapping("/{userId}/products")
	public ResponseEntity<?> addProduct(@PathVariable Long userId, @RequestBody List<CartRequest> requestList) {
		try {
			CartResponse cartResponse = shoppingService.processAndrequest(userId, requestList);
			return ResponseEntity.status(HttpStatus.CREATED).body(cartResponse);
		} catch (Exception ex) {
			String exceptionMessage = "Exception occured while adding products to cart. Exception msg: ";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage + ex.getMessage());
		}
	}
}
