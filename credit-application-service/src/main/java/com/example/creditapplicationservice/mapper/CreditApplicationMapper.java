package com.example.creditapplicationservice.mapper;

import com.example.creditapplicationservice.entity.CreditApplicationEntity;
import com.example.creditapplicationservice.model.CreditApplication;

public class CreditApplicationMapper {

    public static CreditApplication toModel(CreditApplicationEntity entity) {
        if (entity == null) return null;

        CreditApplication model = new CreditApplication();
        model.setId(entity.getId());
        model.setAmount(entity.getAmount());
        model.setTerm(entity.getTerm());
        model.setIncome(entity.getIncome());
        model.setLiabilities(entity.getLiabilities());
        model.setCreditScore(entity.getCreditScore());

        model.setStatus(CreditApplication.Status.valueOf(entity.getStatus().name()));

        return model;
    }

    public static CreditApplicationEntity toEntity(CreditApplication model) {
        if (model == null) return null;

        CreditApplicationEntity entity = new CreditApplicationEntity();
        entity.setId(model.getId());
        entity.setAmount(model.getAmount());
        entity.setTerm(model.getTerm());
        entity.setIncome(model.getIncome());
        entity.setLiabilities(model.getLiabilities());
        entity.setCreditScore(model.getCreditScore());

        entity.setStatus(CreditApplicationEntity.Status.valueOf(model.getStatus().name()));

        return entity;
    }
}