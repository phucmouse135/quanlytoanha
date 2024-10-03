package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuildingConverter;
import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.BuildingRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaSevice;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingSearchBuildingConverter buildingSearchBuildingConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentAreaSevice rentAreaSevice;

    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCodes) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuildingConverter.toBuildingSearchBuilder(params, typeCodes);
        List<BuildingEntity> buildingEntities = buildingRepository.findAlls(buildingSearchBuilder);
        List<BuildingDTO> buildingDTOs = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities) {
            BuildingDTO buildingDTO = buildingConverter.toBuildingDTO(buildingEntity);
            buildingDTOs.add(buildingDTO);
        }
        return buildingDTOs;
    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAlls(buildingSearchBuilder);
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingConverter.toBuildingSearchResponse(buildingEntity);
            buildingSearchResponses.add(buildingSearchResponse);
        }
        return buildingSearchResponses;
    }

    @Override
    public List<BuildingSearchResponse> findAll() {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingConverter.toBuildingSearchResponse(buildingEntity);
            buildingSearchResponses.add(buildingSearchResponse);
        }
        return buildingSearchResponses;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        if (buildingEntity == null) {
            return null;
        }
        return buildingConverter.toBuildingDTO(buildingEntity);
    }

    @Override
    public void delete(Long id) {
        rentAreaSevice.deleteByBuildingId(id);
        buildingRepository.deleteById(id);
    }
    @Override
    public void delete(List<Long> ids) {
        ids.forEach(id -> {
            rentAreaSevice.deleteByBuildingId(id);
            buildingRepository.deleteById(id);
        });
    }

    @Override
    public void save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        rentAreaSevice.deleteByIds(Collections.singletonList(buildingDTO.getId()));
        rentAreaSevice.addRentArea(buildingDTO);
        buildingRepository.save(buildingEntity);
    }


    @Override
    public List<StaffResponseDTO> getStaffs(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        List<Long> staffIds = new ArrayList<>();
        if (buildingEntity != null) {
            buildingEntity.getStaffs().forEach(staff -> {
                staffIds.add(staff.getId());
            });
        }
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<StaffResponseDTO> staffResponseDTOs = new ArrayList<>();
        userEntities.forEach(userEntity -> {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(userEntity.getId());
            staffResponseDTO.setFullName(userEntity.getFullName());
            if(staffIds.contains(userEntity.getId())) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOs.add(staffResponseDTO);
        });
        return staffResponseDTOs;
    }

    @Override
    public void assignmentBuilding(Long id, List<Long> staffIds) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        if(buildingEntity != null) {
            List<UserEntity> userEntities = userRepository.findByIdIn(staffIds);
            buildingEntity.setStaffs(userEntities);
        }
        if (buildingEntity == null) {
            throw new IllegalArgumentException("Building entity not found for ID: " + id);
        }
        buildingRepository.save(buildingEntity);
    }

    @Override
    public BuildingDTO addOrUpdate(BuildingDTO buildingDTO) {
        Long id = buildingDTO.getId();
        BuildingEntity buildingEntity = null;
        if(id != null){
            buildingEntity = buildingRepository.findById(id).orElse(null);
        }
        else {
            buildingEntity = new BuildingEntity();
        }
        buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        buildingEntity.setAvatar(buildingDTO.getAvatar());
        saveThumbnail(buildingDTO, buildingEntity);
        buildingRepository.save(buildingEntity);
        buildingDTO.setId(buildingEntity.getId());
        if(StringUtils.check(buildingDTO.getRentArea())){
            rentAreaSevice.addRentArea(buildingDTO);
        }
        return buildingDTO;
    }

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
       String path = "/building/" + buildingDTO.getAvatarName();
       if(buildingDTO.getAvatar() != null){
              if(buildingEntity.getAvatar() != null){
                  if(!path.equals(buildingEntity.getAvatar())){
                        File file = new File("uploads" + buildingEntity.getAvatar());
                        if(file.exists()){
                            file.delete();
                        }
                  }
              }
              buildingEntity.setAvatar(path);
       }
    }

    private String removeAccent(List<String> typeCodes) {
        StringBuilder type = new StringBuilder();
        for (String typeCode : typeCodes) {
            type.append(typeCode).append(",");
        }
        return type.toString();
    }
}
