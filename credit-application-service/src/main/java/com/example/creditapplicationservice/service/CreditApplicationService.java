package com.example.creditapplicationservice.service;

import com.example.creditapplicationservice.dto.CreditApplicationRequest;
import com.example.creditapplicationservice.entity.CreditApplicationEntity;
import com.example.creditapplicationservice.event.CreditApplicationEvent;
import com.example.creditapplicationservice.event.CreditResultEvent;
import com.example.creditapplicationservice.repository.CreditApplicationRepository;
import com.example.creditapplicationservice.entity.CreditApplicationEntity.Status;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;

@Service
public class CreditApplicationService {

    private final CreditApplicationRepository repository;
    private final KafkaTemplate<String, CreditApplicationEvent> kafkaTemplate;

    public CreditApplicationService(CreditApplicationRepository repository, KafkaTemplate<String, CreditApplicationEvent> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public UUID createApplication(CreditApplicationRequest request) {
        CreditApplicationEntity application = new CreditApplicationEntity();
        application.setId(UUID.randomUUID());
        application.setAmount(request.getAmount());
        application.setTerm(request.getTerm());
        application.setIncome(request.getIncome());
        application.setLiabilities(request.getLiabilities());
        application.setCreditScore(request.getCreditScore());
        application.setStatus(Status.IN_PROGRESS);

        repository.save(application);

        CreditApplicationEvent event = new CreditApplicationEvent(
                application.getId(),
                application.getAmount(),
                application.getTerm(),
                application.getIncome(),
                application.getLiabilities(),
                application.getCreditScore()
        );
        kafkaTemplate.send("credit-applications", event);
        return application.getId();
    }

    public Status getApplicationStatus(UUID id) {
        return repository.findById(id)
                .map(CreditApplicationEntity::getStatus)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Application with id " + id + " not found"
                ));
    }

    @RabbitListener(queues = "credit_responses")
    public void handleCreditDecision(CreditResultEvent event) {
        repository.findById(event.getApplicationId())
                .ifPresent(application -> {
                    application.setStatus(event.getStatus() == CreditResultEvent.Status.APPROVED ?
                            Status.APPROVED : Status.REJECTED);
                    repository.save(application);
                });
    }
}

