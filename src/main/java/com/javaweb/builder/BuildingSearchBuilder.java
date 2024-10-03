package com.javaweb.builder;


import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
    private String name;
    private String ward;
    private String street;
    private String district;
    private Integer numberOfBasement;

    private Integer floorArea;
    private List<String> typeCode = new ArrayList<>();
    private String managerName;
    private String managerPhone;
    private Long AreaFrom;
    private Long AreaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private Long staffId;
    public BuildingSearchBuilder( Builder builder) {
        this.name = builder.name;
        this.ward = builder.ward;
        this.street = builder.street;
        this.district = builder.district;
        this.numberOfBasement = builder.numberOfBasement;
        this.floorArea = builder.floorArea;
        this.typeCode = builder.typeCode;
        this.managerName = builder.managerName;
        this.managerPhone = builder.managerPhone;
        this.AreaFrom = builder.AreaFrom;
        this.AreaTo = builder.AreaTo;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.staffId = builder.staffId;
    }
    public String getName() {
        return name;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public Long getAreaFrom() {
        return AreaFrom;
    }

    public Long getAreaTo() {
        return AreaTo;
    }

    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Long getRentPriceTo() {
        return rentPriceTo;
    }

    public Long getStaffId() {
        return staffId;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public Example<BuildingEntity> getSpecification() {
        return null;
    }

    public static class Builder{
        private String name;
        private String ward;
        private String street;
        private String district;
        private Integer numberOfBasement;
        private Integer floorArea;
        private List<String> typeCode = new ArrayList<>();
        private String managerName;
        private String managerPhone;
        private Long AreaFrom;
        private Long AreaTo;
        private Long rentPriceFrom;
        private Long rentPriceTo;

        private Long staffId;
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }


        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setFloorArea(Integer floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setManagerPhone(String managerPhoneNumber) {
            this.managerPhone = managerPhoneNumber;
            return this;
        }

        public Builder setAreaFrom(Long areaFrom) {
            AreaFrom = areaFrom;
            return this;
        }

        public Builder setAreaTo(Long areaTo) {
            AreaTo = areaTo;
            return this;
        }

        public Builder setRentPriceFrom(Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Builder setRentPriceTo(Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }
        public BuildingSearchBuilder build(){
            return new BuildingSearchBuilder(this);
        }
    }
}
