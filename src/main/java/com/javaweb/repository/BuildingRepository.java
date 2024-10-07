package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> , BuildingRepositoryCustom {
    Optional<BuildingEntity> findById(Long id);
    Optional<BuildingEntity> findBuildingEntityById(Long id);
}
