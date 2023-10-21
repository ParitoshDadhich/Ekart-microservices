package com.service;

import java.util.List;
import java.util.stream.Collectors;

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

@Service
public interface ShoppingService {
	public CartResponse processAndrequest(Long userId, List<CartRequest> shoppingCartRequestList);
}
