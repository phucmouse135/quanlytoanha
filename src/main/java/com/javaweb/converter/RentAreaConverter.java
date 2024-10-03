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
    public RentAreaEntity toRentAreaEntity(BuildingEntity buildingEntity , long value) {
        RentAreaEntity res = new RentAreaEntity();
        res.setValue(value);
        res.setBuildingEntity(buildingEntity);
        return res;
    }

    public List<RentAreaEntity> toRentAreaEntities(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String[] rentArea = buildingDTO.getRentArea().trim().split(",");
        List<RentAreaEntity> res = new ArrayList<>();
        for (String area : rentArea) {
            rentAreaSevice.deleteByBuildingId(buildingDTO.getId());
            RentAreaEntity rentAreaEntity = toRentAreaEntity(buildingEntity, Long.parseLong(area));
            res.add(rentAreaEntity);
        }
        return res;
    }
}
