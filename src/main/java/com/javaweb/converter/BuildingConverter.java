package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        buildingDTO.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + buildingEntity.getDistrict());
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
        StringBuilder areaResult = new StringBuilder();
        for (RentAreaEntity rentAreaEntity : rentAreaEntities) {
            areaResult.append(rentAreaEntity.getValue()).append(", ");
        }
        if (areaResult.length() > 0) {
            areaResult.deleteCharAt(areaResult.length() - 2);
        }
        buildingDTO.setRentArea(areaResult.toString().trim());
        List<String> typeCode = Arrays.asList(buildingEntity.getType().split(","));
        buildingDTO.setType(buildingEntity.getType().trim());
        buildingDTO.setTypeCodes(typeCode);
        buildingDTO.setAvatar(buildingEntity.getAvatar());
        return buildingDTO;
    }

    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        if (buildingDTO.getTypeCodes() != null) {
            buildingEntity.setType(String.join(",", buildingDTO.getTypeCodes()));
        }
        buildingEntity.setDistrict(buildingDTO.getDistrict());
        return buildingEntity;
    }

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity buildingEntity) {
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        buildingSearchResponse.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + buildingEntity.getDistrict());
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
        StringBuilder areaResult = new StringBuilder();
        rentAreaEntities.forEach(rentAreaEntity -> areaResult.append(rentAreaEntity.getValue()).append(", "));
        if (areaResult.length() > 0) {
            areaResult.deleteCharAt(areaResult.length() - 2);
        }
        buildingSearchResponse.setRentArea(areaResult.toString().trim());
        return buildingSearchResponse;
    }
}