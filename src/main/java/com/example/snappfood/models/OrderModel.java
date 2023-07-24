package com.example.snappfood.models;

import com.example.snappfood.entity.Order;
import com.example.snappfood.entity.Vendor;
import com.example.snappfood.models.status.OrderStatus;
import com.example.snappfood.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class OrderModel extends AbstractModel<Order> {

    private long price;
    private OrderStatus status;
    private List<OrderItemModel> orderItems;
    private CustomerModel customer;
    private Vendor vendor;
    private int deliveryTimeInMin;
    private Date actionNeedDate;
    private TripModel trip;

    public Date getDeliveryDate() {
        return DateUtils.getXMinAfterDate(getCreateDate(), getDeliveryTimeInMin());
    }

    public boolean isDelayed() {
        return getDeliveryDate().before(new Date());
    }

    public void setStatus(String status) {
        this.status = OrderStatus.valueOf(status);
    }

    public long getDelayInMin() {
        if (!isDelayed())
            return 0;
        else
            return DateUtils.getDatesDiffInMin(new Date(), getDeliveryDate());
    }
}
