package com.bingogame.backend.Service;

import com.bingogame.backend.Object.BingoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BingoService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public BingoService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public BingoResponse getCards(String playcard_token) {
        String url = "http://www.hyeumine.com/getcard.php?bcode=" + playcard_token;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            try {
                return objectMapper.readValue(responseEntity.getBody(), BingoResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ResponseEntity<String> checkWinningCard(String playerToken) {
        String url = "http://www.hyeumine.com/checkwin.php?playcard_token=" + playerToken;
        return restTemplate.getForEntity(url, String.class);
    }
}
