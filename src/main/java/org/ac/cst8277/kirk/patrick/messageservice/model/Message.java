package org.ac.cst8277.kirk.patrick.messageservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

/**
 * A data object containing information about a message.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private UUID id;
    private UUID authorId;
    private String content;
    private Date date;

    public UUID getId() {
        return id;
    }

    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
