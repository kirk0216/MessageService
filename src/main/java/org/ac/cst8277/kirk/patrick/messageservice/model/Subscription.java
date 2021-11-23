package org.ac.cst8277.kirk.patrick.messageservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    private UUID subscriberId;
    private UUID publisherId;

    public UUID getSubscriberId() {
        return subscriberId;
    }

    public UUID getPublisherId() {
        return publisherId;
    }
}
