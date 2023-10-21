package com.model;

import java.util.List;

import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartResponse {

	@Id
	private Long userId;
	private Long cartId;
	private Integer toatalItems;
	private Double totalCosts;
	private List<Product> products;
	
}
