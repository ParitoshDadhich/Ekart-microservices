package com.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.dao.ShoppingDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.CartEntity;
import com.model.CartRequest;
import com.model.CartResponse;
import com.model.Product;
import com.service.ShoppingService;
import com.util.ProductConstants;

@Service
public class ShoppingServiceImpl implements ShoppingService{
	@Autowired
	@Qualifier("webclient")
	private WebClient.Builder builder;
	@Autowired
	private ShoppingDAO repo;

	private static final Logger logger = Logger.getLogger(ShoppingServiceImpl.class);
	
	@Override
	public CartResponse processAndrequest(Long userId, List<CartRequest> shoppingCartRequestList) {
		logger.info("Calling getAllProducts api...");
		String productServiceURL = ProductConstants.GET_PRODUCTS + shoppingCartRequestList.stream()
											.map(e -> String.valueOf(e.getProductId()))
											.collect(Collectors.joining(","));
		logger.info("Api call completed!");
		
		List<Product> productServiceList = builder.build()
											.get()
											.uri(productServiceURL)
											.retrieve()
											.bodyToFlux(Product.class)
											.collectList()
											.block();
		
		System.out.println(productServiceURL);
		System.out.println(productServiceList);

		logger.info("Calculating total cart cost...");
		Double[] totalCost = cartTotalCost(productServiceList, shoppingCartRequestList);
		logger.info("Calculation completed!");
		
		logger.info("Creating cart...");
		CartEntity cartEntity = createCartEntity(userId, productServiceList, totalCost);
		logger.info("Cart has been careated!");
		
		logger.info("Adding cart to DB...");
		cartEntity = repo.save(cartEntity);
		logger.info("Cart has been added to DB!");

		logger.info("Generating response...");
		CartResponse response = generateResponse(cartEntity, productServiceList);
		logger.info("Response has been generated!");		
		
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
