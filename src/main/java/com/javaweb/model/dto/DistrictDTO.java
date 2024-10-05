package com.javaweb.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DistrictDTO {
    @JsonProperty(value = "Buildingids")
    @NotBlank(message = "Buildingids must not be blank")
    private Long[] Buildingids;

    public Long[] getBuildingids() {
        return Buildingids;
    }

    public void setBuildingids(Long[] buildingids) {
        Buildingids = buildingids;
    }
}
