package app.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlackListService {
    private final RestTemplate restTemplate;

    public BlackListService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<String> getBlackList(){
        String url = "http://localhost:8081/getBlackList";
        return (ArrayList<String>)this.restTemplate.getForObject(url, ArrayList.class);
    }
}
