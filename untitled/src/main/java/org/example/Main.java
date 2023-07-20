package org.example;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://94.198.50.185:7081/api/users";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String cookies = responseEntity.getHeaders().get("Set-Cookie").get(0);
        System.out.println(cookies);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Cookie", cookies);


        User user = new User(3L, "James", "Brown", (byte) 45);
        ResponseEntity<String> createUserResponse = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<>(user, httpHeaders), String.class);
        String C1 = createUserResponse.getBody();


        User user2 = new User(3L, "Thomas", "Shelby", (byte) 5);
        ResponseEntity<String> updateUser = restTemplate.exchange(url, HttpMethod.PUT,
                new HttpEntity<>(user2, httpHeaders), String.class);
        String C2 = updateUser.getBody();


        ResponseEntity<String> deleteUser = restTemplate.exchange(url + "/3" ,HttpMethod.DELETE,
                new HttpEntity<>(user, httpHeaders), String.class);
        String C3 = deleteUser.getBody();

        System.out.println(C1+C2+C3);
    }
}
