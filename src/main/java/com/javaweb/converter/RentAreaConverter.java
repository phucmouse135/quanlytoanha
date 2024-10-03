package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.springframework.stereotype.Component;

@Component
public class RentAreaConverter {
    public RentAreaEntity toRentAreaEntity(BuildingDTO buildingDTO , long value) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setId(buildingDTO.getId());
        RentAreaEntity rentAreaEntity = new RentAreaEntity();
        rentAreaEntity.setValue(value);
        rentAreaEntity.setBuildingEntity(buildingEntity);
        return rentAreaEntity;
    }
}
