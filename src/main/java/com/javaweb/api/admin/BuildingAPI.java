package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController(value="buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @GetMapping(value = "/list")
    public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
                                         @RequestParam(value = "typeCode", required = false) List<String> typeCodes) {
        return buildingService.findAll(params, typeCodes);
    }

    @PostMapping(value = "/")
    public ResponseEntity<BuildingDTO> saveBuilding(@RequestBody BuildingDTO buildingDTO) {
        return ResponseEntity.ok(buildingService.addOrUpdate(buildingDTO));
    }

    @DeleteMapping(value = "/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids) {
        buildingService.delete(ids);
    }

    @GetMapping(value = "/{id}/staffs")
    public List<StaffResponseDTO>  getStaffs(@PathVariable Long id) {
        return buildingService.getStaffs(id);
    }

    @PostMapping("assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        Long buildingId = assignmentBuildingDTO.getBuildingId();
        List<Long> staffIds = assignmentBuildingDTO.getStaffs();
        buildingService.assignmentBuilding(buildingId, staffIds);
    }
}
