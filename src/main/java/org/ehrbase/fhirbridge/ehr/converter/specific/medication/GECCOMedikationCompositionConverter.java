package org.ehrbase.fhirbridge.ehr.converter.specific.medication;

import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.acehemmer.AceHemmerObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.antikoagulanzien.AntikoagulanzienObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.covid19therapie.Covid19TherapieObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.immunoglobuline.ImmunglobulineObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.GECCOMedikationComposition;
import org.hl7.fhir.r4.model.MedicationStatement;

public class GECCOMedikationCompositionConverter extends MedicationStatementToCompositionConverter<GECCOMedikationComposition> {

    private static final String PHARMACOLOGICAL_THERAPY_URL = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pharmacological-therapy";
    private static final String PHARMACOLOGICAL_ACE_INHIB_URL = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pharmacological-therapy-ace-inhibitors";
    private static final String PHARMACOLOGICAL_ANTICOAGULATNS = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pharmacological-therapy-anticoagulants";
    private static final String PHARMACOLOGICAL_IMMUNOGLOBULINS = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pharmacological-therapy-immunoglobulins";

    @Override
    protected GECCOMedikationComposition convertInternal(MedicationStatement resource) {
        GECCOMedikationComposition composition = new GECCOMedikationComposition();
        setObservation(resource, composition);
        if (resource.hasCategory()) {
            composition.setKategorie(CodingToDvCodedTextConverter.getInstance().convert(resource.getCategory().getCoding().get(0)));
        }
        return composition;
    }

    private void setObservation(MedicationStatement resource, GECCOMedikationComposition geccoMedikationComposition) {
        if (resource.hasMeta()) {
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
}
