package org.ac.cst8277.kirk.patrick.messageservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * A data object containing details about a user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("roles")
    private String[] roles;

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String[] getRoles() {
        return roles;
    }

    public boolean isPublisher() {
        for (int i = 0; i < roles.length; i++) {
            if (roles[i].equals("Publisher")) {
                return true;
            }
        }

        return false;
    }
}
