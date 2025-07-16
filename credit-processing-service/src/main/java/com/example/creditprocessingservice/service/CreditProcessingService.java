package com.example.creditprocessingservice.service;

import com.example.creditprocessingservice.event.CreditApplicationEvent;
import com.example.creditprocessingservice.event.CreditResultEvent;
import com.example.creditprocessingservice.event.CreditResultEvent.Status;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CreditProcessingService {
    private final RabbitTemplate rabbitTemplate;

    public CreditProcessingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @KafkaListener(topics = "credit-applications", groupId = "credit-processing-group")
    public void process(CreditApplicationEvent event) {

        BigDecimal monthlyPayment = event.getAmount()
                .divide(BigDecimal.valueOf(event.getTerm()), 2, RoundingMode.HALF_UP);

        BigDecimal totalObligation = event.getLiabilities().add(monthlyPayment);
        BigDecimal maxAllowed = event.getIncome().multiply(BigDecimal.valueOf(0.5));

        Status decision = totalObligation.compareTo(maxAllowed) <= 0
                ? Status.APPROVED
                : Status.REJECTED;

        CreditResultEvent result = new CreditResultEvent();
        result.setId(event.getId());
        result.setStatus(decision);

        rabbitTemplate.convertAndSend("credit_responses", result);
    }
}

