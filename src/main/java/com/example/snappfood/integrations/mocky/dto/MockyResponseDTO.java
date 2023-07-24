package com.example.snappfood.integrations.mocky.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockyResponseDTO {
    private String status;
    private NewEstimateTimeDTO data;
}
