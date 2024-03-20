package com.cogent.main;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDao 
{
	private int id;
	private String name;
	private float discount;
	private String discountCode;
}
