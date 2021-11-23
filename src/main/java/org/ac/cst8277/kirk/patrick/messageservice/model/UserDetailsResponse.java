package org.ac.cst8277.kirk.patrick.messageservice.model;

import org.springframework.http.HttpStatus;

public class UserDetailsResponse {
    private HttpStatus httpStatus;
    private String message;
    private UserDetails data;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public UserDetails getData() {
        return data;
    }
}
