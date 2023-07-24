package com.example.snappfood.repository;

import com.example.snappfood.entity.DelayReport;
import com.example.snappfood.entity.Vendor;
import com.example.snappfood.models.VendorDelayModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Collectors;

public interface DelayReportRepository extends AbstractEntityRepository<DelayReport> {

    @Query("SELECT o.vendor, SUM(dr.delayInMin) " +
            "FROM Order o " +
            "JOIN o.delayReports dr " +
            "GROUP BY o.vendor")
    List<Object[]> getDelayPerVendorData();

    default List<VendorDelayModel> getDelayPerVendor() {
        List<Object[]> result = getDelayPerVendorData();
        return result.stream()
                .map(objArray -> {
                    Vendor vendor = (Vendor) objArray[0];
                    int totalDelay = ((Number) objArray[1]).intValue();
                    return new VendorDelayModel(vendor.getExtuid(), vendor.getName(), totalDelay);
                })
                .collect(Collectors.toList());
    }
}
