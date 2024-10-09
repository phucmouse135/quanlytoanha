package com.javaweb.controller.admin;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.BuildingSearchBuildingConverter;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    public ModelAndView list(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modalSearch", buildingSearchRequest);
        mav.addObject("listStaffs", userService.getStaffs());
        mav.addObject("listDistrict", districtCode.type());
        mav.addObject("listBuildingTypes", buildingType.type());
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        try {
            BuildingSearchBuilder buildingSearchBuilder;
            if (buildingSearchRequest == null) {
                buildingSearchBuilder = new BuildingSearchBuilder.Builder().build();
            } else {
                buildingSearchBuilder = buildingSearchBuildingConverter.toBuildingSearchBuilder(buildingSearchRequest);
            }
            if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
                buildingSearchBuilder = new BuildingSearchBuilder.Builder().setStaffId(SecurityUtils.getPrincipal().getId()).build();
            }
            buildingSearchResponses = buildingService.findAll(buildingSearchBuilder);

        } catch (Exception e) {
            // Log the exception
        }
        mav.addObject("buildingSearchResponses", buildingSearchResponses);
        BuildingSearchResponse model = new BuildingSearchResponse();
        DisplayTagUtils.of(request, model);
        List<BuildingSearchResponse> buildingDisplay = buildingService.getAllBuilding(new PageRequest(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(buildingDisplay);
        model.setTotalItem(buildingDisplay.size());
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, buildingSearchResponses);
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, List<BuildingSearchResponse> buildingSearchResponses) {
        if (buildingSearchResponses.size() == 0) {
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, "No building found");
        }
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
        mav.addObject("listStaffs", userService.getStaffs());
        return mav;
    }
}
