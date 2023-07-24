package com.example.snappfood.integrations.mocky;

import com.example.snappfood.integrations.mocky.dto.MockyResponseDTO;
import com.example.snappfood.utils.ApplicationContextUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MockyRestService {

    private final String url;
    RestTemplate restTemplate;

    public MockyRestService(ApplicationContextUtil contextUtil) {
        this.restTemplate = new RestTemplate();
        this.url = contextUtil.getProperty("mocky.url");
    }

    public MockyResponseDTO getNewEstimateTime() {
        String urlWithPath = url + "/v3/122c2796-5df4-461c-ab75-87c1192b17f7";
        ResponseEntity<MockyResponseDTO> response = restTemplate.exchange(
                urlWithPath, HttpMethod.GET, null, MockyResponseDTO.class);
        return response.getBody();
    }


}
