package com.javaweb.service.impl;

import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.custom.BuildingRepository;
import com.javaweb.service.RentAreaSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RentAreaServiceImpl implements RentAreaSevice {

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Override
    public void deleteByIds(List<Long> ids) {
        rentAreaRepository.deleteRentAreaEntitiesByIdIn(ids);
    }

    @Override
    public void deleteByBuildingId(Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        rentAreaRepository.deleteByBuildingEntity(buildingEntity);
    }

    @Override
    public void addRentArea(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingDTO.getId()).get();
        rentAreaRepository.deleteByBuildingEntity(buildingEntity);
        String[] rentArea = buildingDTO.getRentArea().trim().split(",");
        for (String area : rentArea) {
            RentAreaEntity rentAreaEntity = rentAreaConverter.toRentAreaEntity(buildingDTO, Long.parseLong(area));
            rentAreaRepository.save(rentAreaEntity);
        }
    }
}