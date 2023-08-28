package com.TmsService.microservicetransporter.Repositories;

import com.TmsService.microservicetransporter.Entities.TransporterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransporterRepository extends JpaRepository<TransporterEntity, UUID> {
    @Query("SELECT t FROM TransporterEntity t WHERE t.date_deleted IS NULL")
    List<TransporterEntity> findTransportersNotDeleted();
    @Query("SELECT t FROM TransporterEntity t WHERE t.date_deleted IS NULL AND (LOWER(t.tva) LIKE LOWER(CONCAT('%', :tva, '%')) OR LOWER(t.reasonSociale) LIKE LOWER(CONCAT('%', :tva, '%')))")
    List<TransporterEntity> findTransportersByTvaReason(String tva);

}
