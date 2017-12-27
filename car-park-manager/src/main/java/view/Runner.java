package view;

import entities.Bus;
import entities.UserForm;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        UserForm userForm = new UserForm();
        userForm.setUsername("AlexandrRomanovskij");
        userForm.setPassword("11111111");
        RestTemplate restTemplate = new RestTemplate();
        String userAuthenticationUrl
                = "http://localhost:8080/login/authorize";
        ResponseEntity<String> response = restTemplate.postForEntity(userAuthenticationUrl,
                userForm, String.class);
        String token = response.getHeaders().getFirst("X-Auth-Token");

        if (token != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Auth-Token", token);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            String busByIdUrl = "http://localhost:8080/rest/entity/getBus/2";
            ResponseEntity<Bus> busResponseEntity = restTemplate
                    .exchange(busByIdUrl, HttpMethod.GET, httpEntity, Bus.class);
            System.out.println(busResponseEntity.getBody());
            String allBusesUrl = "http://localhost:8080/rest/entity/getBuses";
            ResponseEntity<List<Bus>> listResponseEntity =
                    restTemplate.exchange(allBusesUrl, HttpMethod.GET, httpEntity,
                            new ParameterizedTypeReference<List<Bus>>() {});
            System.out.println(listResponseEntity.getBody());
        }
    }
}
