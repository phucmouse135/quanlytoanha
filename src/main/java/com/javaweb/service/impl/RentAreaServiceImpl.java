package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.RentAreaSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RentAreaServiceImpl implements RentAreaSevice {

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public void deleteByBuildingId(Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        rentAreaRepository.deleteByBuildingEntity(buildingEntity);
    }

}