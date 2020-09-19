package com.mymax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymax.entity.MasterProductStatus;

@Repository
public interface MasterProductStatusRepository extends JpaRepository<MasterProductStatus, String>{

}
