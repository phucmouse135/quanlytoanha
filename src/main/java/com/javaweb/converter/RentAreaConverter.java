package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.service.RentAreaSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentAreaConverter {
    @Autowired
    private RentAreaSevice rentAreaSevice;

    public RentAreaEntity toRentAreaEntity(BuildingEntity buildingEntity, Long value) {
        RentAreaEntity res = new RentAreaEntity();
        res.setValue(value);
        res.setBuildingEntity(buildingEntity);
        return res;
    }

    public List<RentAreaEntity> toRentAreaEntities(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String rentAreaStr = buildingDTO.getRentArea();
        if (rentAreaStr == null || rentAreaStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Rent area cannot be null or empty");
        }

        if (buildingEntity.getId() != null) {
            rentAreaSevice.deleteByBuildingId(buildingEntity.getId());
        }

        String[] rentArea = rentAreaStr.trim().split(",");
        List<RentAreaEntity> res = new ArrayList<>();

        for (String area : rentArea) {
            try {
                Long value = Long.parseLong(area.trim());
                RentAreaEntity rentAreaEntity = toRentAreaEntity(buildingEntity, value);
                res.add(rentAreaEntity);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid rent area value: " + area, e);
            }
        }

        return res;
    }
}