package org.ac.cst8277.kirk.patrick.messageservice.dao;

import org.ac.cst8277.kirk.patrick.messageservice.model.Message;
import org.ac.cst8277.kirk.patrick.messageservice.model.Subscription;

import java.util.List;
import java.util.UUID;

public interface MessageDatabase {

    void open();
    void close();

    void insertMessage(Message message);
    void updateMessage(Message message);
    void deleteMessage(UUID id);

    boolean isPublisher(UUID id);
    void insertPublisher(UUID id);
    void deletePublisher(UUID id);

    List<Message> getAllMessages();
    List<Message> getMessagesByPublisher(UUID publisherId);
    List<Message> getMessagesForSubscriber(UUID subscriberId);

    void insertSubscription(Subscription subscription);
    void deleteSubscription(Subscription subscription);
    List<UUID> getSubscribersTo(UUID publisherId);
}
