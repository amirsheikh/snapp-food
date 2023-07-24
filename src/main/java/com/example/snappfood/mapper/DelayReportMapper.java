package com.example.snappfood.mapper;

import com.example.snappfood.entity.DelayReport;
import com.example.snappfood.models.DelayReportModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DelayReportMapper extends AbstractMapper<DelayReport, DelayReportModel> {

}
