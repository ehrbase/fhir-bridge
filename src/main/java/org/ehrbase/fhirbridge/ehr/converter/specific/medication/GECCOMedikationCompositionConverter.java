package org.ehrbase.fhirbridge.ehr.converter.specific.medication;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.AceHemmerObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.AntikoagulanzienObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.Covid19TherapieObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.ImmunglobulineObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.GECCOMedikationComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.KategorieDefiningCode;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.Optional;

public class GECCOMedikationCompositionConverter extends MedicationStatementToCompositionConverter<GECCOMedikationComposition> {

    private static final String PHARMACOLOGICAL_THERAPY_URL = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pharmacological-therapy";
    private static final String PHARMACOLOGICAL_ACE_INHIB_URL = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pharmacological-therapy-ace-inhibitors" ;
    private static final String PHARMACOLOGICAL_ANTICOAGULATNS =  "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pharmacological-therapy-anticoagulants";
    private static final String PHARMACOLOGICAL_IMMUNOGLOBULINS = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pharmacological-therapy-immunoglobulins";

    @Override
    protected GECCOMedikationComposition convertInternal(MedicationStatement resource) {
        GECCOMedikationComposition geccoMedikationComposition = new GECCOMedikationComposition();
        setObservation(resource, geccoMedikationComposition);
        getKategorieDefiningCode(resource).ifPresent(geccoMedikationComposition::setKategorieDefiningCode);
        return geccoMedikationComposition;
    }

    private Optional<KategorieDefiningCode> getKategorieDefiningCode(MedicationStatement resource) {
        if(resource.hasCategory()){
         return mapCategory(resource);
        }else {
            return Optional.empty();
        }
    }

    private Optional<KategorieDefiningCode> mapCategory(MedicationStatement resource) {
        String category = resource.getCategory().getCoding().get(0).getCode();
        if (KategorieDefiningCode.COMMUNITY.getCode().equals(category)) {
            return Optional.of(KategorieDefiningCode.COMMUNITY);
        }else if (KategorieDefiningCode.INPATIENT.getCode().equals(category)){
            return Optional.of(KategorieDefiningCode.INPATIENT);
        }else if(KategorieDefiningCode.OUTPATIENT.getCode().equals(category)){
            return Optional.of(KategorieDefiningCode.OUTPATIENT);
        }else if(KategorieDefiningCode.PATIENT_SPECIFIED.getCode().equals(category)){
            return Optional.of(KategorieDefiningCode.PATIENT_SPECIFIED);
        }else{
            throw new ConversionException("The category code" +category+ "is not supported by the Fhir bridge");
        }
    }

    private void setObservation(MedicationStatement resource, GECCOMedikationComposition geccoMedikationComposition) {
        String resourceUrl = resource.getMeta().getProfile().get(0).getValue();
        switch (resourceUrl) {
            case PHARMACOLOGICAL_THERAPY_URL:
                geccoMedikationComposition.setCovid19Therapie(new Covid19TherapieObservationConverter().convert(resource));
                break;
            case PHARMACOLOGICAL_ACE_INHIB_URL:
                geccoMedikationComposition.setAceHemmer(new AceHemmerObservationConverter().convert(resource));
                break;
            case PHARMACOLOGICAL_ANTICOAGULATNS:
                geccoMedikationComposition.setAntikoagulanzien(new AntikoagulanzienObservationConverter().convert(resource));
                break;
            case PHARMACOLOGICAL_IMMUNOGLOBULINS:
                geccoMedikationComposition.setImmunglobuline(new ImmunglobulineObservationConverter().convert(resource));
                break;
            default:
                throw new ConversionException("The profile " + resourceUrl + " is not supported by the FHIR bridge for MedicationStatements");
        }
    }
}
