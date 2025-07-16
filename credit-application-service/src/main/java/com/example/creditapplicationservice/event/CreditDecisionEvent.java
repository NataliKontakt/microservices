package com.example.creditapplicationservice.event;

import java.util.UUID;
import com.example.creditapplicationservice.model.CreditApplication.Status;

public class CreditDecisionEvent {
    private UUID applicationId;
    private boolean approved;;

    public UUID getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(UUID applicationId) {
        this.applicationId = applicationId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }


}
