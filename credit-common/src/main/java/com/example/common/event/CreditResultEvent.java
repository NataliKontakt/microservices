package com.example.common.event;

import java.io.Serializable;
import java.util.UUID;

public class CreditResultEvent implements Serializable {
    public UUID getApplicationId() {
        return applicationId;
    }

    public void setId(UUID applicationId) {
        this.applicationId = applicationId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        APPROVED, REJECTED
    }

    private UUID applicationId;
    private Status status;


}
