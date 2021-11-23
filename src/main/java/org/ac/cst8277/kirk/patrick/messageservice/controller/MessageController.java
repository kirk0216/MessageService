package org.ac.cst8277.kirk.patrick.messageservice.controller;

import org.ac.cst8277.kirk.patrick.messageservice.UserService;
import org.ac.cst8277.kirk.patrick.messageservice.dao.MessageDatabase;
import org.ac.cst8277.kirk.patrick.messageservice.dao.MySQLMessageDatabase;
import org.ac.cst8277.kirk.patrick.messageservice.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Provides the REST API for interacting with the MessageService.
 */
@RestController
public class MessageController {
    @PostMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> publishMessage(@RequestBody Message message) {
        Response response = new Response();

        UserDetails userDetails = new UserService()
                .getUserDetails(message.getAuthorId());

        if (userDetails != null && userDetails.isPublisher()) {
            MessageDatabase database = new MySQLMessageDatabase();
            database.open();

            message.generateId();
            database.insertMessage(message);

            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setData(message);

            database.close();
        }
        else {
            response.setHttpStatus(HttpStatus.UNAUTHORIZED);
            response.setMessage("User is not a publisher.");
        }

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping(value = "/message")
    public ResponseEntity<Response> deleteMessage(@RequestParam String id) {
        Response response = new Response();

        MessageDatabase database = new MySQLMessageDatabase();
        database.open();

        database.deleteMessage(UUID.fromString(id));

        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("Success");

        database.close();

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateMessage(@RequestBody Message message) {
        Response response = new Response();

        MessageDatabase database = new MySQLMessageDatabase();
        database.open();

        database.updateMessage(message);

        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("Success");
        response.setData(message);

        database.close();

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping(value = "/message")
    public ResponseEntity<Response> getAllMessages() {
        Response response = new Response();

        MessageDatabase database = new MySQLMessageDatabase();
        database.open();

        List<Message> messages = database.getAllMessages();
        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("Success");
        response.setData(messages);

        database.close();

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping(value = "/message/publisher/{id}")
    public ResponseEntity<Response> getMessagesBy(@PathVariable UUID id) {
        Response response = new Response();

        MessageDatabase database = new MySQLMessageDatabase();
        database.open();

        List<Message> messages = database.getMessagesByPublisher(id);
        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("Success");
        response.setData(messages);

        database.close();

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping(value = "/message/subscriber/{id}")
    public ResponseEntity<Response> getMessagesFor(@PathVariable UUID id) {
        Response response = new Response();

        MessageDatabase database = new MySQLMessageDatabase();
        database.open();

        List<Message> messages = database.getMessagesForSubscriber(id);
        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("Success");
        response.setData(messages);

        database.close();

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
