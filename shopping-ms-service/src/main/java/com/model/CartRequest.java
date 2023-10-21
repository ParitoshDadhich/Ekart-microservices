package com.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartRequest {
// which product want to purchanse and how much quantity
	
	private Long productId;
	private Integer quantity;
	
}
