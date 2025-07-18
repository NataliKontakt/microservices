package com.example.creditapplicationservice.controller;

import com.example.creditapplicationservice.dto.CreditApplicationRequest;
import com.example.creditapplicationservice.dto.CreditApplicationResponse;
import com.example.creditapplicationservice.service.CreditApplicationService;
import com.example.creditapplicationservice.entity.CreditApplicationEntity.Status;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/credit")
public class CreditApplicationController {


    private final CreditApplicationService service;

    public CreditApplicationController(CreditApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public CreditApplicationResponse createCreditApplication(@RequestBody CreditApplicationRequest request) {
        UUID id = service.createApplication(request);
        return new CreditApplicationResponse(id);
    }

    @GetMapping("/{id}/status")
    public Status getApplicationStatus(@PathVariable UUID id) {
        return service.getApplicationStatus(id);
    }
}

