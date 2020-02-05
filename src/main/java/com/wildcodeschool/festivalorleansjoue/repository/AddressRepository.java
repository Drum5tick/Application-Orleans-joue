package com.wildcodeschool.festivalorleansjoue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	public Address findByWording(String wording);

}
