package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;

import java.util.List;

public interface RentAreaSevice {
    void deleteByIds(List<Long> ids);

    void deleteByBuildingId(Long buildingId);

    void addRentArea(BuildingDTO buildingDTO);
}
