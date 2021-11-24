package org.ac.cst8277.kirk.patrick.messageservice.controller;

import org.ac.cst8277.kirk.patrick.messageservice.dao.MessageDatabase;
import org.ac.cst8277.kirk.patrick.messageservice.dao.MySQLMessageDatabase;
import org.ac.cst8277.kirk.patrick.messageservice.model.Id;
import org.ac.cst8277.kirk.patrick.messageservice.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PublisherController {
    @PostMapping(value = "/publisher", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createPublisher(@RequestBody Id id) {
        Response response = new Response();

        MessageDatabase database = new MySQLMessageDatabase();
        database.open();

        database.insertPublisher(id.getId());
        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("Success");

        database.close();

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping(value = "/publisher/{id}")
    public ResponseEntity<Response> deletePublisher(@PathVariable String id) {
        Response response = new Response();

        MessageDatabase database = new MySQLMessageDatabase();
        database.open();

        database.deletePublisher(UUID.fromString(id));
        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("Success");

        database.close();

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
