package com.example.creditapplicationservice.controller;

import com.example.creditapplicationservice.model.CreditApplication;
import com.example.creditapplicationservice.service.CreditApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/credit-applications")
public class CreditController {

    private final CreditApplicationService creditService;

    public CreditController(CreditApplicationService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    public ResponseEntity<CreditApplication> createCreditApplication(@RequestBody CreditApplication application) {
        CreditApplication created = creditService.createApplication(application); // Метод из сервиса
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<String> getStatus(@PathVariable UUID id) {
        return creditService.getApplicationById(id)
                .map(app -> app.getStatus().name())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

