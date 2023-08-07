package com.TmsService.microservicetransporter.Controller;

import com.TmsService.microservicetransporter.Dao.TransporterDao;
import com.TmsService.microservicetransporter.Entities.TransporterEntity;
import com.TmsService.microservicetransporter.Models.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/transporters")
public class TransporterController {

    @Autowired
    private TransporterDao transporterDao;
    @GetMapping
    public ResponseEntity getAllTransporters(){
        return  transporterDao.getTransporters();
    }
    @GetMapping("/{idTransporter}")
    public  ResponseEntity getTransporterById(@PathVariable UUID idTransporter){
        return transporterDao.getTransporter(idTransporter) ;
    }

    @GetMapping("/find/{tva}")
    public  ResponseEntity getTransporterByTva(@PathVariable String tva){
        return transporterDao.findByTva(tva) ;
    }

    @PutMapping("/{idTransporter}")
    public  ResponseEntity deleteTransporter(@PathVariable UUID idTransporter){
        return transporterDao.deleteTransporter(idTransporter) ;
    }

    @PostMapping
    public ResponseEntity createTransporter(@RequestBody TransporterEntity transporter) {
        return transporterDao.addTransporter(transporter);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseVo> updateTransporter(@RequestBody TransporterEntity transporter) {
       return  transporterDao.updateTransporter(transporter) ;
    }

    @PutMapping("/lock/{idTransporter}/{lock}")
    public ResponseEntity<ResponseVo> lockTransporter(@PathVariable UUID idTransporter, @PathVariable  Boolean lock) {
        return  transporterDao.lockOrUnlockTransporter(idTransporter,lock) ;
    }


}
