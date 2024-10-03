package com.javaweb.enums;


import java.util.Map;
import java.util.TreeMap;

public enum  buildingType {
    TANG_TRET("Tầng Trệt "),
    NGUYEN_CAN("Nguyên Căn "),
    NOI_THAT("Nội Thất ");

    private final String typeName;

    buildingType(String typeName) {
        this.typeName = typeName;
    }

    public String getCode() {
        return typeName;
    }

    public static Map<String,String> type(){
        Map<String,String> listType = new TreeMap<>();
        for(buildingType item : buildingType.values()){
            listType.put(item.toString() , item.typeName);
        }
        return listType;
    }
}
