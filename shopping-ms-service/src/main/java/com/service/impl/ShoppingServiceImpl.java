package com.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import com.dao.ShoppingDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.CartEntity;
import com.model.CartRequest;
import com.model.CartResponse;
import com.model.Product;
import com.model.util.ProductConstants;

public class ShoppingServiceImpl {
	@Autowired
	@Qualifier("webclient")
	private WebClient.Builder builder;
	@Autowired
	private ShoppingDAO repo;

	public CartResponse processAndrequest(Long userId, List<CartRequest> shoppingCartRequestList) {
		// calling Product API
		String productServiceURL = ProductConstants.GET_PRODUCTS + shoppingCartRequestList.stream()
											.map(e -> String.valueOf(e.getProductId()))
											.collect(Collectors.joining(","));
		
		List<Product> productServiceList = builder.build()
											.get()
											.uri(productServiceURL)
											.retrieve()
											.bodyToFlux(Product.class)
											.collectList()
											.block();
		
		System.out.println(productServiceURL);
		System.out.println(productServiceList);

		//calculate cart total cost
		Double[] totalCost = cartTotalCost(productServiceList, shoppingCartRequestList);
		
		// create cartEntity
		CartEntity cartEntity = createCartEntity(userId, productServiceList, totalCost);

		//save cart to DB
		cartEntity = repo.save(cartEntity);

		// generate a response
		CartResponse response = generateResponse(cartEntity, productServiceList);
				
		return response;
	}
	
	private Double[] cartTotalCost(List<Product> productServiceList, List<CartRequest> shoppingCartRequestList) {
		final Double[] totalCost = { 0.0 };
		
		productServiceList.forEach(psl -> {
			shoppingCartRequestList.forEach(scr -> {
				if (psl.getProductId() == scr.getProductId()) {
					psl.setQuantity(scr.getQuantity());
					totalCost[0] = totalCost[0] + psl.getAmount() * scr.getQuantity();
				}
			});
		});
		
		return totalCost;
	}
	
	private CartEntity createCartEntity(Long userId, List<Product> productServiceList, Double[] totalCost) {
		CartEntity cartEntity = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			cartEntity = CartEntity.builder()
					.userId(userId)
					.cartId((long) (Math.random() * Math.pow(10, 10)))
					.totalItems(productServiceList.size())
					.totalCosts(totalCost[0])
					.products(mapper.writeValueAsString(productServiceList))
					.build();

		}

		catch (Exception e) {
		}
		
		return cartEntity;
	}
	
	private CartResponse generateResponse(CartEntity cartEntity, List<Product> productServiceList) {
		return CartResponse.builder()
				.cartId(cartEntity.getCartId())
				.userId(cartEntity.getUserId())
				.toatalItems(cartEntity.getTotalItems())
				.totalCosts(cartEntity.getTotalCosts())
				.products(productServiceList)
				.build();
	}
	
}
