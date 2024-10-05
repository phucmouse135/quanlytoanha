package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Autowired
    private UserRepository userRepository;

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
        buildingEntity.setRentAreas(rentAreaConverter.toRentAreaEntities(buildingDTO, buildingEntity));
        buildingEntity.setDistrict(buildingDTO.getDistrict());
//        List<UserEntity> staffs = new ArrayList<>();
//        UserEntity userEntity = new UserEntity();
//        Long userId = Long.valueOf(buildingDTO.getManagerName());
//        userEntity = userRepository.findUserEntitiesById(userId);
//        staffs.add(userEntity);
//        buildingEntity.setStaffs(staffs);
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