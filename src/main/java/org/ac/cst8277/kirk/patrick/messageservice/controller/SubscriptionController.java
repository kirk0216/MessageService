package org.ac.cst8277.kirk.patrick.messageservice.controller;

import org.ac.cst8277.kirk.patrick.messageservice.dao.MessageDatabase;
import org.ac.cst8277.kirk.patrick.messageservice.dao.MySQLMessageDatabase;
import org.ac.cst8277.kirk.patrick.messageservice.model.Response;
import org.ac.cst8277.kirk.patrick.messageservice.model.Subscription;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class SubscriptionController {
    @PostMapping(value = "/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createSubscription(@RequestBody Subscription subscription) {
        Response response = new Response();

        MessageDatabase database = new MySQLMessageDatabase();
        database.open();

        if (database.isPublisher(subscription.getPublisherId())) {
            database.insertSubscription(subscription);

            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Success");
            response.setData(subscription);
        }
        else {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("User is not a publisher.");
        }

        database.close();

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping(value = "/unsubscribe")
    public ResponseEntity<Response> deleteSubscription(@RequestParam String subscriberId, @RequestParam String publisherId) {
        Response response = new Response();

        MessageDatabase database = new MySQLMessageDatabase();
        database.open();

        Subscription subscription = new Subscription(UUID.fromString(subscriberId), UUID.fromString(publisherId));

        database.deleteSubscription(subscription);
        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("Success");

        database.close();

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping(value = "/subscribers/{id}")
    public ResponseEntity<Response> getSubscribersFor(@PathVariable String id) {
        Response response = new Response();

        MessageDatabase database = new MySQLMessageDatabase();
        database.open();

        List<UUID> userIds = database.getSubscribersTo(UUID.fromString(id));
        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("Success");
        response.setData(userIds);

        database.close();

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
