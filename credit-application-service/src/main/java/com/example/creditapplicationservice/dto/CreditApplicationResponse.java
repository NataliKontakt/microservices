package com.example.creditapplicationservice.dto;

import java.util.UUID;

public class CreditApplicationResponse {
    private UUID id;

    public CreditApplicationResponse(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
