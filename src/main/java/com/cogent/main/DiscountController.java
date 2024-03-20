package com.cogent.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/discount")
public class DiscountController 
{
	@Autowired
	private DiscountService service;
	
	@GetMapping("/admin")
	public List<DiscountDao> getDiscounts(@RequestHeader("Authorization") String token)
	{
		return service.getDiscounts(token);
	}
	
	@PostMapping("/admin")
	public DiscountDao addDiscount(@RequestBody DiscountDao discountDao, @RequestHeader("Authorization") String token) 
	{
		return service.addDiscount(discountDao, token);
	}
	
	@PutMapping("/admin/{discountId}")
	public DiscountDao updateDiscount(@PathVariable int discountId, @RequestBody DiscountDao discountDao, @RequestHeader("Authorization") String token) 
	{
		return service.updateDiscount(discountId, discountDao, token);
	}
	
	@DeleteMapping("/admin/{discountId}")
	public boolean deleteDicountById(@PathVariable int discountId, @RequestHeader("Authorization") String token)
	{
		return service.deleteDicountById(discountId, token);
	}
	
	@GetMapping("/{discountCode}")
	public DiscountDao getDiscountByCode(@PathVariable String discountCode, @RequestHeader("Authorization") String token) 
	{
		return service.getDiscountByCode(discountCode, token);
	}
	
	
	
}
