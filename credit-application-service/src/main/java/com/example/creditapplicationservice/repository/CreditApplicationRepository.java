package com.example.creditapplicationservice.repository;

import com.example.creditapplicationservice.entity.CreditApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplicationEntity, UUID> {
}