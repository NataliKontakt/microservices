package com.example.creditapplicationservice.service;

import com.example.creditapplicationservice.entity.CreditApplicationEntity;
import com.example.creditapplicationservice.mapper.CreditApplicationMapper;
import com.example.creditapplicationservice.model.CreditApplication;
import com.example.creditapplicationservice.repository.CreditApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreditApplicationService {

    private final CreditApplicationRepository repository;

    public CreditApplicationService(CreditApplicationRepository repository) {
        this.repository = repository;
    }

    public CreditApplication createApplication(CreditApplication application) {
        CreditApplicationEntity entity = CreditApplicationMapper.toEntity(application);
        CreditApplicationEntity savedEntity = repository.save(entity);
        return CreditApplicationMapper.toModel(savedEntity);
    }

    public Optional<CreditApplication> getApplicationById(UUID id) {
        return repository.findById(id).map(CreditApplicationMapper::toModel);
    }

    public List<CreditApplication> getAllApplications() {
        return repository.findAll().stream()
                .map(CreditApplicationMapper::toModel)
                .collect(Collectors.toList());
    }

    public CreditApplication updateApplication(UUID id, CreditApplication updatedApplication) {
        Optional<CreditApplicationEntity> entityOpt = repository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new RuntimeException("Application not found");
        }

        CreditApplicationEntity entity = entityOpt.get();
        entity.setAmount(updatedApplication.getAmount());
        entity.setTerm(updatedApplication.getTerm());
        entity.setIncome(updatedApplication.getIncome());
        entity.setLiabilities(updatedApplication.getLiabilities());
        entity.setCreditScore(updatedApplication.getCreditScore());
        entity.setStatus(CreditApplicationEntity.Status.valueOf(updatedApplication.getStatus().name()));

        CreditApplicationEntity savedEntity = repository.save(entity);
        return CreditApplicationMapper.toModel(savedEntity);
    }

    public void deleteApplication(UUID id) {
        repository.deleteById(id);
    }
}

