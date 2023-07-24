package com.example.snappfood.integrations.mocky;

import com.example.snappfood.integrations.mocky.dto.MockyResponseDTO;
import com.example.snappfood.integrations.mocky.dto.NewEstimateTimeDTO;
import com.example.snappfood.utils.ApplicationContextUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class MockyRestServiceTest {
    private MockyRestService mockyRestService;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        mockyRestService = new MockyRestService(getMockApplicationContextUtil());
        mockyRestService.restTemplate = restTemplate;
    }

    private ApplicationContextUtil getMockApplicationContextUtil() {
        ApplicationContextUtil contextUtil = mock(ApplicationContextUtil.class);
        when(contextUtil.getProperty("mocky.url")).thenReturn("http://example.com");
        return contextUtil;
    }

    @Test
    public void testGetNewEstimateTime() {
        MockyResponseDTO mockResponse = new MockyResponseDTO();
        mockResponse.setData(new NewEstimateTimeDTO());
        mockResponse.getData().setEstimate(15);
        ResponseEntity<MockyResponseDTO> mockResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(restTemplate.exchange(
                eq("http://example.com/v3/122c2796-5df4-461c-ab75-87c1192b17f7"),
                eq(HttpMethod.GET),
                eq(null),
                eq(MockyResponseDTO.class)
        )).thenReturn(mockResponseEntity);

        // Calling the method to test
        MockyResponseDTO responseDTO = mockyRestService.getNewEstimateTime();

        // Assertions
        assertEquals(15, responseDTO.getData().getEstimate());
    }
}