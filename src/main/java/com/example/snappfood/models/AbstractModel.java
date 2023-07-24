package com.example.snappfood.models;

import com.example.snappfood.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class AbstractModel<T extends AbstractEntity> {

    @JsonProperty("uid")
    private String extuid;

    private Date createDate;
}
