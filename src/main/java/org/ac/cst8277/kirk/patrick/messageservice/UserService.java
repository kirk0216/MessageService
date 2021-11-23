package org.ac.cst8277.kirk.patrick.messageservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ac.cst8277.kirk.patrick.messageservice.model.UserDetails;
import org.ac.cst8277.kirk.patrick.messageservice.model.UserDetailsResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

/**
 * Provides methods for interacting with the user service.
 */
public class UserService {
    private static final String HOST = "ums";
    private static final int PORT = 8080;
    private static final String USER_DETAILS_PATH = "/users/find";
    private static final String ID_PARAM = "id";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public UserDetails getUserDetails(UUID id) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(HOST)
                .port(PORT)
                .path(USER_DETAILS_PATH)
                .queryParam(ID_PARAM, id.toString())
                .build()
                .toUri();

        System.out.println(uri.toString());

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Accept", "application/json")
                .GET()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                UserDetailsResponse userDetails = objectMapper.readValue(response.body(), UserDetailsResponse.class);

                return userDetails.getData();
            }
        }
        catch (ConnectException e) {
            System.out.println("Connection rejected by: " + uri.toString());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
