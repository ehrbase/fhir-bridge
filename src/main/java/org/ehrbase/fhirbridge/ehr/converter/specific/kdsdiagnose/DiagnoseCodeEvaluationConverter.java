package org.ehrbase.fhirbridge.ehr.converter.specific.kdsdiagnose;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.InvalidStatusCodeException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.KlinischerStatusCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.KlinischerStatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.SchweregradDefiningCode;
import org.hl7.fhir.r4.model.*;

public abstract class DiagnoseCodeEvaluationConverter< E extends EntryEntity> extends EntryEntityConverter<Condition, E> {

     final Extension extension;

    public DiagnoseCodeEvaluationConverter(Extension extension) {
        this.extension = extension;
    }

     SchweregradDefiningCode transformSchweregrad(Coding fhirSeverity) {
        SchweregradDefiningCode openEHRSeverity;
        if (!fhirSeverity.getSystem().equalsIgnoreCase("http://snomed.info/sct")) {
            throw new ConversionException("severity code system should be http://snomed.info/sct, found " + fhirSeverity.getSystem());
        }
        switch (fhirSeverity.getCode()) {
            case "24484000":
                openEHRSeverity = SchweregradDefiningCode.SEVERE_SEVERITY_MODIFIER_QUALIFIER_VALUE;
                break;
            case "6736007":
                openEHRSeverity = SchweregradDefiningCode.MODERATE_SEVERITY_MODIFIER_QUALIFIER_VALUE;
                break;
            case "255604002":
                openEHRSeverity = SchweregradDefiningCode.MILD_QUALIFIER_VALUE;
                break;
            default:
                throw new ConversionException("Unexpected value: " + fhirSeverity.getCode());
        }
        return openEHRSeverity;
    }

    KlinischerStatusCluster transformKlinischerStatus (Coding clinicalStatus){
        KlinischerStatusCluster klinischerStatusCluster = new KlinischerStatusCluster();
        if (!clinicalStatus.getSystem().equalsIgnoreCase("http://terminology.hl7.org/CodeSystem/condition-clinical")) {
            throw new ConversionException("severity code system should be http://terminology.hl7.org/CodeSystem/condition-clinical, found " + clinicalStatus.getSystem());
        }
        switch (clinicalStatus.getCode()) {
            case "active":
                klinischerStatusCluster.setKlinischerStatusDefiningCode(KlinischerStatusDefiningCode.AKTIV);
                break;
            case "recurrence":
                klinischerStatusCluster.setKlinischerStatusDefiningCode(KlinischerStatusDefiningCode.);
                break;
            case "relapse":
                klinischerStatusCluster.setKlinischerStatusDefiningCode(KlinischerStatusDefiningCode.AKTIV);
                break;
            case "inactive":
                klinischerStatusCluster.setKlinischerStatusDefiningCode(KlinischerStatusDefiningCode.AKTIV);
                break;
            case "remission":
                klinischerStatusCluster.setKlinischerStatusDefiningCode(KlinischerStatusDefiningCode.AKTIV);
                break;
            case "resolved":
                klinischerStatusCluster.setKlinischerStatusDefiningCode(KlinischerStatusDefiningCode.AKTIV);
                break;
            case "unknown":
                klinischerStatusCluster.setKlinischerStatusDefiningCode(KlinischerStatusDefiningCode.AKTIV);
                break;
            default:
                throw new InvalidStatusCodeException(clinicalStatus.getCode());
        }

        return klinischerStatusCluster;
    }

}