package com.TmsService.microservicetransporter.Dao;

import com.TmsService.microservicetransporter.Entities.TransporterEntity;
import com.TmsService.microservicetransporter.Models.ResponseVo;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface TransporterDao {
    ResponseEntity<ResponseVo> addTransporter(TransporterEntity transporter);
    ResponseEntity<ResponseVo> getTransporters();
    ResponseEntity<ResponseVo> getTransporter(UUID idTransporter);
    ResponseEntity<ResponseVo> lockOrUnlockTransporter(UUID idTransporter,Boolean lock);
    ResponseEntity<ResponseVo> updateTransporter(TransporterEntity transporter);


}
