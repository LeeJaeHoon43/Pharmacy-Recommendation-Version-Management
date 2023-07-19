package com.example.pharmacyRecommend.pharmacy.repository;

import com.example.pharmacyRecommend.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
