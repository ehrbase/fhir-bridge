package org.ehrbase.fhirbridge.ehr.converter.specific.patientdischarge;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
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

        if(resource.hasValueCodeableConcept()){

            String code = getSnomedCodeObservation(resource);

            switch(code) {
                case "261665006":
                    adminEntry.setArtDerEntlassung(ArtDerEntlassungDefiningCode.UNKNOWN_QUALIFIER_VALUE.toDvCodedText());
                    break;
                case "32485007":
                    adminEntry.setArtDerEntlassung(ArtDerEntlassungDefiningCode.HOSPITAL_ADMISSION_PROCEDURE.toDvCodedText());
                    break;
                case "419099009":
                    adminEntry.setArtDerEntlassung(ArtDerEntlassungDefiningCode.DEAD_FINDING.toDvCodedText());
                    break;
                case "371827001":
                    adminEntry.setArtDerEntlassung(ArtDerEntlassungDefiningCode.PATIENT_DISCHARGED_ALIVE_FINDING.toDvCodedText());
                    break;
                case "3457005":
                    adminEntry.setArtDerEntlassung(ArtDerEntlassungDefiningCode.PATIENT_REFERRAL_PROCEDURE.toDvCodedText());
                    break;
                case "306237005":
                    adminEntry.setArtDerEntlassung(ArtDerEntlassungDefiningCode.REFERRAL_TO_PALLIATIVE_CARE_SERVICE_PROCEDURE.toDvCodedText());
                    break;
                default:
                    throw new UnprocessableEntityException("Value code " + resource.getValueCodeableConcept().getCoding().get(0).getCode() + " is not supported");
            }
        }

        return adminEntry;
    }

    private void checkForSnomedSystem(String systemCode) {
        if (!SNOMED.getUrl().equals(systemCode)) {
            throw new UnprocessableEntityException("The system is not correct. " +
                    "It should be '" + SNOMED.getUrl() + "', but it was '" + systemCode + "'.");
        }
    }

    private String getSnomedCodeObservation(Observation fhirObservation) {
        Coding code = fhirObservation.getValueCodeableConcept().getCoding().get(0);
        checkForSnomedSystem(code.getSystem());
        return code.getCode();
    }
}