package com.wildcodeschool.festivalorleansjoue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wildcodeschool.festivalorleansjoue.entity.Referent;

@Repository
public interface ReferentRepository extends JpaRepository<Referent, Long> {

}
