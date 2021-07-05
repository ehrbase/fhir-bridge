package org.ehrbase.fhirbridge.ehr.converter.specific.symptom;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ConditionToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.KrankheitsanzeichenCode;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.SchweregradSymptomCode;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomAnatomischeLokalisationElement;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomObservation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

public class VorliegendesSymptomObservationConverter extends ConditionToObservationConverter<VorliegendesSymptomObservation> {

    @Override
    protected VorliegendesSymptomObservation convertInternal(Condition condition) {
        VorliegendesSymptomObservation vorliegendesSymptomObservation = new VorliegendesSymptomObservation();
        setNameDesSymptoms(condition, vorliegendesSymptomObservation);
        setBeginnDerEpisode(condition, vorliegendesSymptomObservation);
        setAnamtomischeLokalisation(condition, vorliegendesSymptomObservation);
        setSchweregrad(condition, vorliegendesSymptomObservation);
        setDatumUhrzeitDesRueckgangs(condition, vorliegendesSymptomObservation);
        return vorliegendesSymptomObservation;
    }

    private void setDatumUhrzeitDesRueckgangs(Condition condition, VorliegendesSymptomObservation vorliegendesSymptomObservation) {
        if (condition.getAbatement() != null && !condition.getAbatement().isEmpty()) {
            vorliegendesSymptomObservation.setDatumUhrzeitDesRueckgangsValue(
                    condition.getAbatementDateTimeType().getValueAsCalendar().toZonedDateTime());
        }
    }

    private void setSchweregrad(Condition condition, VorliegendesSymptomObservation vorliegendesSymptomObservation) {
        if (condition.getSeverity() != null && !condition.getSeverity().isEmpty()) {
            SchweregradSymptomCode schweregrad = null;
            if (condition.getSeverity().getCoding().get(0).getSystem().equals("http://snomed.info/sct")) {
                schweregrad = SchweregradSymptomCode.getCodesAsMap().get(condition.getSeverity().getCoding().get(0).getCode());
            }
            if (schweregrad == null) {
                throw new ConversionException("Schweregrad has unknown system");
            }
            vorliegendesSymptomObservation.setSchweregrad(schweregrad.toDvCodedText());
        }
    }

    private void setAnamtomischeLokalisation(Condition condition, VorliegendesSymptomObservation vorliegendesSymptomObservation) {
        if (!condition.getBodySite().isEmpty()) {
            for (CodeableConcept bodySite : condition.getBodySite()) {
                VorliegendesSymptomAnatomischeLokalisationElement lokalisation =
                        new VorliegendesSymptomAnatomischeLokalisationElement();
                lokalisation.setValue(bodySite.getCoding().get(0).getCode());
                vorliegendesSymptomObservation.getAnatomischeLokalisation().add(lokalisation);
            }
        }
    }

    private void setBeginnDerEpisode(Condition condition, VorliegendesSymptomObservation vorliegendesSymptomObservation) {
        if (condition.getOnset() != null && !condition.getOnset().isEmpty()) {
            vorliegendesSymptomObservation.setBeginnDerEpisodeValue(
                    condition.getOnsetDateTimeType().getValueAsCalendar().toZonedDateTime());
        }
    }

    private void setNameDesSymptoms(Condition condition, VorliegendesSymptomObservation vorliegendesSymptomObservation) {
        Coding coding = condition.getCode().getCoding().get(0);
        if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl()) &&  KrankheitsanzeichenCode.getCodesAsMap().containsKey(coding.getCode())) {
            vorliegendesSymptomObservation.setNameDesSymptomsKrankheitsanzeichens(KrankheitsanzeichenCode.getCodesAsMap().get(coding.getCode()).toDvCodedText());
        }else{
            throw new ConversionException("Unbekanntes <unbekanntes Symptom>");
        }
    }

}
