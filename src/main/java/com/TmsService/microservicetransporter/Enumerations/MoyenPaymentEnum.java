package com.TmsService.microservicetransporter.Enumerations;

import com.TmsService.microservicetransporter.TransporterUtils.TransporterConstants;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MoyenPaymentEnum {
    CARTE_BANCAIRE(TransporterConstants.CARTE_BANCAIRE),
    VIREMENT(TransporterConstants.VIREMENT),
    CHEQUE(TransporterConstants.CHEQUE);
    private final String libelle;

    private MoyenPaymentEnum(String libelle) {
        this.libelle = libelle;
    }

    @JsonValue
    public String getLibelle() {
        return libelle;
    }
}
