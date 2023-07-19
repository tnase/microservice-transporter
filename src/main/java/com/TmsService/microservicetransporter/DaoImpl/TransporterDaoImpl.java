package com.TmsService.microservicetransporter.DaoImpl;

import com.TmsService.microservicetransporter.Dao.TransporterDao;
import com.TmsService.microservicetransporter.Entities.TransporterEntity;
import com.TmsService.microservicetransporter.Models.ResponseVo;
import com.TmsService.microservicetransporter.Repositories.TransporterRepository;
import com.TmsService.microservicetransporter.TransporterUtils.TransporterConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TransporterDaoImpl implements TransporterDao {
    @Autowired
    TransporterRepository transporterRepository;
    @Override
    public ResponseEntity<ResponseVo> addTransporter(TransporterEntity transporter) {
        ResponseVo responseVo=new ResponseVo();
        transporter.setIdUser(UUID.randomUUID());
        responseVo.setVo(transporterRepository.save(transporter));
        responseVo.setSuccessMsg(TransporterConstants.SUCCESS_CREATE_TRANSPORTER);
        return ResponseEntity.ok(responseVo);
    }

    @Override
    public ResponseEntity<ResponseVo> getTransporters() {
        ResponseVo responseVo=new ResponseVo();
        responseVo.setVo(transporterRepository.findAll());
        return ResponseEntity.ok(responseVo);
    }
//retourne un transporter a partir de son id 
    @Override
    public ResponseEntity<ResponseVo> getTransporter(UUID idCustomer) {
        ResponseVo responseVo=new ResponseVo();
        TransporterEntity transporter=transporterRepository.findById(idCustomer).isPresent()?transporterRepository.findById(idCustomer).get():null;
       if(transporter==null){
           responseVo.setWarningMsg(TransporterConstants.TRANSPORTER_NOT_FOUND);
       }
        responseVo.setVo(transporter);
        return ResponseEntity.ok(responseVo);
    }

    @Override
    public ResponseEntity<ResponseVo> lockOrUnlockTransporter(UUID idTransporter,Boolean lock) {
        ResponseVo responseVo=new ResponseVo();
        TransporterEntity transporter=transporterRepository.findById(idTransporter).isPresent()?transporterRepository.findById(idTransporter).get():null;
        if(transporter==null){
            responseVo.setErrorsMsg(TransporterConstants.TRANSPORTER_NOT_FOUND);
        } else if (lock!=null){
            if(Boolean.TRUE.equals(lock)){
                transporter.setBoo_isActif(Boolean.FALSE);
            }else {
                transporter.setBoo_isActif(Boolean.TRUE);
            }
            transporter.setDate_update(new Date());
            transporter=transporterRepository.save(transporter);
        }else{
            responseVo.setErrorsMsg(TransporterConstants.NULL_LOCK_ACTION);
        }
        responseVo.setVo(transporter);
        return ResponseEntity.ok(responseVo);
    }


    @Override
    public ResponseEntity<ResponseVo> updateTransporter(TransporterEntity transporter) {
        ResponseVo responseVo=new ResponseVo();
        if (transporter!=null && transporter.getIdTransporter()!=null){
            TransporterEntity existingCustomer=transporterRepository.findById(transporter.getIdTransporter()).isPresent()?transporterRepository.findById(transporter.getIdTransporter()).get():null;
            if (existingCustomer!=null){
                transporter.setDate_update(new Date());
                transporter.setIdUser(existingCustomer.getIdUser());
                existingCustomer=transporterRepository.save(transporter);
            }else{
                responseVo.setErrorsMsg(TransporterConstants.TRANSPORTER_NOT_FOUND);
            }
            responseVo.setVo(existingCustomer);
        }else{
            responseVo.setErrorsMsg(TransporterConstants.NULL_TRANSPORTER_OR_NOT_ID);
            responseVo.setVo(transporter);
        }
        return ResponseEntity.ok(responseVo);
    }
}
