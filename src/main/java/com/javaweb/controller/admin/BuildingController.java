package com.javaweb.controller.admin;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuildingConverter;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingSearchBuildingConverter buildingSearchBuildingConverter;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView list(@ModelAttribute BuildingSearchRequest buildingSearchRequest) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modalSearch", buildingSearchRequest);
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        try {
            BuildingSearchBuilder buildingSearchBuilder;
            if (buildingSearchRequest == null) {
                buildingSearchBuilder = new BuildingSearchBuilder.Builder().build();
            } else {
                buildingSearchBuilder = buildingSearchBuildingConverter.toBuildingSearchBuilder(buildingSearchRequest);
            }
            buildingSearchResponses = buildingService.findAll(buildingSearchBuilder);

        } catch (Exception e) {
            // Log the exception
        }
        mav.addObject("buildingReponse", buildingSearchResponses);
        mav.addObject("listStaffs", userService.getStaffs());
        mav.addObject("listDistrict", districtCode.type());
        mav.addObject("listBuildingTypes", buildingType.type());
        return mav;
    }


    @GetMapping(value = "/admin/building-edit")
    public ModelAndView edit(@ModelAttribute BuildingDTO buildingDTO, @RequestParam(value = "id", required = false) Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        if (id != null) {
            buildingDTO = buildingService.findById(id);
        }
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("listDistrict", districtCode.type());
        mav.addObject("listBuildingTypes", buildingType.type());
        return mav;
    }
}
