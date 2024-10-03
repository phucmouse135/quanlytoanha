package com.javaweb.service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCodes);
    List<BuildingSearchResponse> findAll(BuildingSearchBuilder buildingSearchBuilder);

    List<BuildingSearchResponse> findAll();

    BuildingDTO findById(Long id);

    void delete(Long id);

    void delete(List<Long> ids);

    List<StaffResponseDTO> getStaffs(Long id);

    void assignmentBuilding(Long id, List<Long> staffIds);

    BuildingDTO addOrUpdate(BuildingDTO buildingDTO);
}
