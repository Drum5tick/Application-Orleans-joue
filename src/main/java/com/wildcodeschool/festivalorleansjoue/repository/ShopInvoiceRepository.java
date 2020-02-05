package com.wildcodeschool.festivalorleansjoue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Invoice;
import com.wildcodeschool.festivalorleansjoue.entity.ShopInvoice;

@Repository
public interface ShopInvoiceRepository extends JpaRepository<ShopInvoice, Long>{

	public ShopInvoice findTopByOrderByIdDesc();
}
