package com.example.taxidriverrestapplication.repositories;

import com.example.taxidriverrestapplication.entity.TaxiDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiDriverRepository extends JpaRepository<TaxiDriver, Integer> {

}
