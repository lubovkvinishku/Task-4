package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUser(){
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange
                (URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> allUser = responseEntity.getBody();

        return allUser;
    }
    public User getUserId(Long id) {
        User user = restTemplate.getForObject(URL + "/id", User.class);
        return user;
    }
    public void saveUser(User user) {
        Long id = user.getId();
        if(id ==0 ) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, user, String.class);
            System.out.println("New user by create");
            System.out.println(responseEntity.getBody());
        } else  {
            restTemplate.put(URL, user);
            System.out.println("User with ID " + id + " update");
        }
    }
    public void deleteUser(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("User ID: " + id + " delete");
    }

}
