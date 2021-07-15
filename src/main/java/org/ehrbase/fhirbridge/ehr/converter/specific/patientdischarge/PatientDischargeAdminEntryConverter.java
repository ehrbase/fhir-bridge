package org.ehrbase.fhirbridge.ehr.converter.specific.patientdischarge;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition.ArtDerEntlassungDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition.EntlassungsartAdminEntry;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.SNOMED;

public class PatientDischargeAdminEntryConverter extends EntryEntityConverter<Observation, EntlassungsartAdminEntry> {

    @Override
    protected EntlassungsartAdminEntry convertInternal(Observation resource) {
        EntlassungsartAdminEntry adminEntry = new EntlassungsartAdminEntry();
        convertArtDerEntlassung(resource, adminEntry);
        return adminEntry;
    }

    private void convertArtDerEntlassung(Observation resource, EntlassungsartAdminEntry adminEntry) {
        if(resource.hasValueCodeableConcept()){
            String code = getSnomedCodeObservation(resource);
            switch(code) {
                case "261665006":
                    adminEntry.setArtDerEntlassungDefiningCode(ArtDerEntlassungDefiningCode.UNKNOWN_QUALIFIER_VALUE);
                    break;
                case "32485007":
                    adminEntry.setArtDerEntlassungDefiningCode(ArtDerEntlassungDefiningCode.HOSPITAL_ADMISSION_PROCEDURE);
                    break;
                case "419099009":
                    adminEntry.setArtDerEntlassungDefiningCode(ArtDerEntlassungDefiningCode.DEAD_FINDING);
                    break;
                case "371827001":
                    adminEntry.setArtDerEntlassungDefiningCode(ArtDerEntlassungDefiningCode.PATIENT_DISCHARGED_ALIVE_FINDING);
                    break;
                case "3457005":
                    adminEntry.setArtDerEntlassungDefiningCode(ArtDerEntlassungDefiningCode.PATIENT_REFERRAL_PROCEDURE);
                    break;
                case "306237005":
                    adminEntry.setArtDerEntlassungDefiningCode(ArtDerEntlassungDefiningCode.REFERRAL_TO_PALLIATIVE_CARE_SERVICE_PROCEDURE);
                    break;
                default:
                    throw new ConversionException("Value code " + resource.getValueCodeableConcept().getCoding().get(0).getCode() + " is not supported");
            }
        }else{
            throw new ConversionException("ValueCodeableConcept missing but is required by the FHIR-Bridge.");
        }
    }

    private void checkForSnomedSystem(String systemCode) {
        if (!SNOMED.getUrl().equals(systemCode)) {
            throw new ConversionException("The system is not correct. " +
                    "It should be '" + SNOMED.getUrl() + "', but it was '" + systemCode + "'.");
        }
    }

    private String getSnomedCodeObservation(Observation fhirObservation) {
        Coding code = fhirObservation.getValueCodeableConcept().getCoding().get(0);
        checkForSnomedSystem(code.getSystem());
        return code.getCode();
    }
}