package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    public static void JoinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        if (buildingSearchBuilder == null) {
            return;
        }
        Object staffIdObj = buildingSearchBuilder.getStaffId();
        if (staffIdObj != null && StringUtils.check(staffIdObj.toString())) {
            sql.append(" inner JOIN assignmentbuilding as a on b.id = a.buildingid ");
        }
        Long areaTo = buildingSearchBuilder.getAreaTo();
        Long areaFrom = buildingSearchBuilder.getAreaFrom();
        if( areaFrom != null && areaTo != null ) {
            sql.append("inner join rentarea on rentarea.buildingid = b.id ");
        }
    }

    public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        if (buildingSearchBuilder == null) {
            return;
        }
        try {
            // get all field of BuildingSearchBuilder
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                // set accessible to get value of field
                field.setAccessible(true);
                String name = field.getName();
                if(name.equals("staffId") || name.equals("areaFrom") || name.equals("areaTo") || name.equals("rentPriceFrom") || name.equals("rentPriceTo")) {
                    continue;
                }
                if (field.get(buildingSearchBuilder) != null) {
                    if (field.getType().getName().equals("java.lang.String")) {
                        String value = (String) field.get(buildingSearchBuilder);
                        if (StringUtils.check(value)) {
                            sql.append(" AND b." + field.getName() + " LIKE '%" + value + "%' ");
                        }
                    } else if (field.getType().getName().equals("java.lang.Integer")) {
                        Integer value = (Integer) field.get(buildingSearchBuilder);
                        if (value != null) {
                            sql.append(" AND b." + field.getName() + " = " + value + " ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        if (buildingSearchBuilder == null) {
            return;
        }
        Object staffIdObj = buildingSearchBuilder.getStaffId();
        if (staffIdObj != null && StringUtils.check(staffIdObj.toString())) {
            sql.append(" AND a.staffid = " + staffIdObj + " ");
        }
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        if (rentAreaFrom != null && rentAreaTo != null) {
            sql.append("and rentarea.value between " + rentAreaFrom + " and " + rentAreaTo + " ");
        } else if (rentAreaFrom != null) {
            sql.append("and rentarea.value >= " + rentAreaFrom + " ");
        } else if (rentAreaTo != null) {
            sql.append("and rentarea.value <= " + rentAreaTo + " ");
        }
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        if (rentPriceFrom != null && rentPriceTo != null) {
            sql.append(" and b.rentprice between " + rentPriceFrom + " and " + rentPriceTo + " ");
        } else if (rentPriceFrom != null) {
            sql.append(" and b.rentprice >= " + rentPriceFrom + " ");
        } else if (rentPriceTo != null) {
            sql.append(" and b.rentprice <= " + rentPriceTo + " ");
        }
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && typeCode.size() > 0) {
            sql.append(" AND b.type in (");
            for (String type : typeCode) {
                sql.append("'" + type + "',");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(") ");
        }
    }
    // find all building
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BuildingEntity> findAlls(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT b.*, rentarea.value ");
        sql.append("FROM building AS b ");
        sql.append("LEFT JOIN rentarea ON rentarea.buildingid = b.id ");
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        List<BuildingEntity> result = new ArrayList<>();
        JoinTable(buildingSearchBuilder, sql);
        queryNormal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        where.append(" GROUP BY b.id;");
        sql.append(where);
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        result = query.getResultList();
        return result;
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest) {
        return null;
    }
}
