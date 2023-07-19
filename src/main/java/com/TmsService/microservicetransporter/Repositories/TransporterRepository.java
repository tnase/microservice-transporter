package com.TmsService.microservicetransporter.Repositories;

import com.TmsService.microservicetransporter.Entities.TransporterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransporterRepository extends JpaRepository<TransporterEntity, UUID> {

}
