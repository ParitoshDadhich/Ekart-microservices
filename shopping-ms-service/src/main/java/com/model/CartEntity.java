package com.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartEntity {
	@Id
	private Long userId;
	private Long cartId;
	private Integer totalItems;
	private Double totalCosts;
	private String products;
}
