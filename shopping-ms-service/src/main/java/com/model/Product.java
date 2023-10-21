package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	public class Product {
		private Long productId;
		private String productName;
		private Integer quantity;
		private Double amount;
	}


