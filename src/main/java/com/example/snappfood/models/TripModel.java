package com.example.snappfood.models;

import com.example.snappfood.entity.Trip;
import com.example.snappfood.models.status.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class TripModel extends AbstractModel<Trip> {

    private TripStatus status;

    public void setStatus(String status) {
        this.status = TripStatus.valueOf(status);
    }
}
