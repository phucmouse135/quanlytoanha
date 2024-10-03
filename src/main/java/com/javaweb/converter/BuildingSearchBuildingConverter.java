package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingSearchBuildingConverter {

    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCodes) {
        BuildingSearchBuilder.Builder builder = new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObject(params.get("name"), String.class))
                .setWard(MapUtils.getObject(params.get("ward"), String.class))
                .setStreet(MapUtils.getObject(params.get("street"), String.class))
                .setDistrict(MapUtils.getObject(params.get("districtid"), String.class))
                .setNumberOfBasement(MapUtils.getObject(params.get("numberOfBasement"), Integer.class))
                .setFloorArea(MapUtils.getObject(params.get("floorArea"), Integer.class))
                .setTypeCode(typeCodes)
                .setManagerName(MapUtils.getObject(params.get("managerName"), String.class))
                .setManagerPhone(MapUtils.getObject(params.get("managerPhoneNumber"), String.class))
                .setAreaFrom(MapUtils.getObject(params.get("areaFrom"), Long.class))
                .setAreaTo(MapUtils.getObject(params.get("areaTo"), Long.class))
                .setRentPriceFrom(MapUtils.getObject(params.get("rentPriceFrom"), Long.class))
                .setRentPriceTo(MapUtils.getObject(params.get("rentPriceTo"), Long.class))
                .setStaffId(MapUtils.getObject(params.get("staffId"), Long.class));


        return builder.build();
    }
    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder.Builder builder = new BuildingSearchBuilder.Builder()
                .setName(buildingSearchRequest.getName())
                .setWard(buildingSearchRequest.getWard())
                .setStreet(buildingSearchRequest.getStreet())
                .setDistrict(buildingSearchRequest.getDistrict())
                .setNumberOfBasement(buildingSearchRequest.getNumberOfBasement())
                .setFloorArea(buildingSearchRequest.getFloorArea())
                .setManagerName(buildingSearchRequest.getManagerName())
                .setManagerPhone(buildingSearchRequest.getManagerPhone())
                .setAreaFrom(buildingSearchRequest.getAreaFrom())
                .setAreaTo(buildingSearchRequest.getAreaTo())
                .setRentPriceFrom(buildingSearchRequest.getRentPriceFrom())
                .setRentPriceTo(buildingSearchRequest.getRentPriceTo())
                .setStaffId(buildingSearchRequest.getStaffId());
        return builder.build();
    }
}