package com.cogent.main;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService 
{
	@Autowired
	private DiscountEntityRepository repository;
	
	@Autowired
	private UserClient userClient;
	
	public List<DiscountDao> getDiscounts(String token) 
	{
		if(!userClient.validAdminToken(token)) return null;
		
		List<DiscountEntity> deList = repository.findAll();
		List<DiscountDao> ddList = new LinkedList<DiscountDao>();
		
		for(DiscountEntity de: deList)
		{
			ddList.add(DiscountDao.builder()
					.id(de.getId())
					.name(de.getName())
					.discount(de.getDiscount())
					.discountCode(de.getDiscountCode())
					.build());
		}
		
		return ddList;
	}

	public DiscountDao addDiscount(DiscountDao discountDao, String token)
	{
		if(!userClient.validAdminToken(token)) return null;
		
		DiscountEntity de = repository.save(DiscountEntity.builder()
				.name(discountDao.getName())
				.discount(discountDao.getDiscount())
				.discountCode(discountDao.getDiscountCode())
				.build());
		
		return DiscountDao.builder()
				.id(de.getId())
				.name(de.getName())
				.discount(de.getDiscount())
				.discountCode(de.getDiscountCode())
				.build();
	}

	public DiscountDao updateDiscount(int discountId, DiscountDao discountDao, String token) 
	{
		if(!userClient.validAdminToken(token)) return null;
		
		DiscountEntity de = repository.save(DiscountEntity.builder()
				.id(discountId)
				.name(discountDao.getName())
				.discount(discountDao.getDiscount())
				.discountCode(discountDao.getDiscountCode())
				.build());
		
		return DiscountDao.builder()
				.id(de.getId())
				.name(de.getName())
				.discount(de.getDiscount())
				.discountCode(de.getDiscountCode())
				.build();
	}

	public boolean deleteDicountById(int discountId, String token) 
	{
		if(!userClient.validAdminToken(token)) return false;
		if(repository.findById(discountId).isEmpty()) return false;
		repository.deleteById(discountId);
		return repository.findById(discountId).isEmpty();
	}

	public DiscountDao getDiscountByCode(String discountCode, String token) 
	{
		if(!userClient.validToken(token)) return null;
		
		DiscountEntity de = repository.findByDiscountCode(discountCode).get();
		
		return DiscountDao.builder()
				.id(de.getId())
				.name(de.getName())
				.discount(de.getDiscount())
				.discountCode(de.getDiscountCode())
				.build();
	}

}
