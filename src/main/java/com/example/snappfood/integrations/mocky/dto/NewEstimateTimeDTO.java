package com.example.snappfood.integrations.mocky.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewEstimateTimeDTO {
    @JsonProperty("eta")
    private int estimate;
}
