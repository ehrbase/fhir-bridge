package org.ehrbase.fhirbridge.ehr.converter.specificconverters.symptom;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.ehr.converter.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.SymptomComposition;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.AusgeschlossenesSymptomEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.UnbekanntesSymptomAussageUeberDieFehlendeInformationElement;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.UnbekanntesSymptomEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomAnatomischeLokalisationElement;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomObservation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymptomCompositionConverter extends CompositionConverter<Condition, SymptomComposition> {

    private static final Map<String, KrankheitsanzeichenCode> krankheitszeichenMap = new HashMap<>();

    private static final Map<String, SchweregradSymptomCode> schweregradMap = new HashMap<>();


    static {
        for (KrankheitsanzeichenCode krankheitszeichen : KrankheitsanzeichenCode.values()) {
            krankheitszeichenMap.put(krankheitszeichen.getCode(), krankheitszeichen);
        }

        for (SchweregradSymptomCode schweregrad : SchweregradSymptomCode.values()) {
            schweregradMap.put(schweregrad.getCode(), schweregrad);
        }
    }

    @Override
    public SymptomComposition convertInternal(@NonNull Condition resource) {
        SymptomComposition result = new SymptomComposition();

        if (resource.getVerificationStatus().isEmpty()) {
            mapUnknown(resource, result);
        } else if (resource.getVerificationStatus().getCoding().get(0).getCode().equals("confirmed")) {
            mapPresent(resource, result);
        } else {
            mapAbsent(resource, result);
        }

        result.setStartTimeValue(resource.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());


        // ======================================================================================
        // Required fields by API

        result.setStatusDefiningCode(StatusDefiningCode.FINAL);
        result.setKategorie(KategorieDefiningCodeSymptom.N753251.toDvCodedText());

        result.setLanguage(Language.DE);
        result.setLocation("test");
        result.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningCode(Category.EVENT);
        result.setComposer(new PartySelf());


        return result;
    }

    private void mapPresent(Condition condition, SymptomComposition composition) {
        VorliegendesSymptomObservation vorliegendesSymptom = new VorliegendesSymptomObservation();

        try {

            Coding coding = condition.getCode().getCoding().get(0);

            KrankheitsanzeichenCode krankheitszeichen = null;

            // Neue Systeme werden eingepflegt sobald sie in KrankheitsanzeichenDefiningcode definiert sind.
            if (coding.getSystem().equals("http://snomed.info/sct")) {
                krankheitszeichen = krankheitszeichenMap.get(coding.getCode());
            }

            if (krankheitszeichen == null) {
                throw new ConversionException("Unbekanntes Krankheitszeichen.");
            }

            //       vorliegendesSymptom.setNameDesSymptomsKrankheitsanzeichensDefiningcode(krankheitszeichen);
            vorliegendesSymptom.setNameDesSymptomsKrankheitsanzeichens(krankheitszeichen.toDvCodedText());


            if (condition.getOnset() != null && !condition.getOnset().isEmpty()) {
                vorliegendesSymptom.setBeginnDerEpisodeValue(
                        condition.getOnsetDateTimeType().getValueAsCalendar().toZonedDateTime());
            }

            vorliegendesSymptom.setTimeValue(condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());

            if (condition.getAbatement() != null && !condition.getAbatement().isEmpty()) {
                vorliegendesSymptom.setDatumUhrzeitDesRueckgangsValue(
                        condition.getAbatementDateTimeType().getValueAsCalendar().toZonedDateTime());
            }


            if (!condition.getBodySite().isEmpty()) {
                for (CodeableConcept bodySite : condition.getBodySite()) {

                    VorliegendesSymptomAnatomischeLokalisationElement lokalisation =
                            new VorliegendesSymptomAnatomischeLokalisationElement();
                    lokalisation.setValue(bodySite.getCoding().get(0).getCode());

                    vorliegendesSymptom.getAnatomischeLokalisation().add(lokalisation);
                }
            }

            if (condition.getSeverity() != null && !condition.getSeverity().isEmpty()) {

                SchweregradSymptomCode schweregrad = null;

                if (condition.getSeverity().getCoding().get(0).getSystem().equals("http://snomed.info/sct")) {
                    schweregrad = schweregradMap.get(condition.getSeverity().getCoding().get(0).getCode());
                }

                if (schweregrad == null) {
                    throw new ConversionException("Schweregrad has unknown system");
                }

                vorliegendesSymptom.setSchweregrad(schweregrad.toDvCodedText());
            }


            vorliegendesSymptom.setOriginValue(condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());

        } catch (Exception e) {
            throw new ConversionException("Some parts of the condition did not contain the required elements. "
                    + e.getMessage(), e);
        }

        vorliegendesSymptom.setLanguage(Language.DE);
        vorliegendesSymptom.setSubject(new PartySelf());

        composition.setVorliegendesSymptom(vorliegendesSymptom);

    }

    private void mapAbsent(Condition condition, SymptomComposition composition) {

        AusgeschlossenesSymptomEvaluation ausgeschlossenesSymptom = new AusgeschlossenesSymptomEvaluation();

        try {
            Coding coding = condition.getCode().getCoding().get(0);

            KrankheitsanzeichenCode krankheitszeichen = null;

            if (coding.getSystem().equals("http://snomed.info/sct")) {
                krankheitszeichen = krankheitszeichenMap.get(coding.getCode());
            }

            if (krankheitszeichen == null) {
                throw new ConversionException("Unbekanntes Diagnose/Problem.");
            }

            ausgeschlossenesSymptom.setProblemDiagnose(krankheitszeichen.toDvCodedText());
        } catch (Exception e) {
            throw new ConversionException("Some parts of the condition did not contain the required elements. "
                    + e.getMessage(), e);
        }


        // Only one value possible.

        ausgeschlossenesSymptom.setAussageUeberDenAusschluss(AussageUberDenAusschlussDefiningCode.N410594000.toDvCodedText());


        ausgeschlossenesSymptom.setSubject(new PartySelf());
        ausgeschlossenesSymptom.setLanguage(Language.DE);

        composition.setAusgeschlossenesSymptom(ausgeschlossenesSymptom);

    }

    private void mapUnknown(Condition condition, SymptomComposition composition) {

        UnbekanntesSymptomEvaluation unbekanntesSymptom = new UnbekanntesSymptomEvaluation();

        try {
            Coding coding = condition.getCode().getCoding().get(0);

            KrankheitsanzeichenCode krankheitszeichen = null;

            // Neue Systeme werden eingepflegt sobald sie in KrankheitsanzeichenDefiningcode definiert sind.
            if (coding.getSystem().equals("http://snomed.info/sct")) {
                krankheitszeichen = krankheitszeichenMap.get(coding.getCode());
            }

            if (krankheitszeichen == null) {
                throw new ConversionException("Unbekanntes <unbekanntes Symptom>");
            }

            unbekanntesSymptom.setUnbekanntesSymptom(krankheitszeichen.toDvCodedText());

        } catch (Exception e) {
            throw new ConversionException("Some parts of the condition did not contain the required elements. "
                    + e.getMessage(), e);
        }

        // UnbekanntesSymptomAussage can only have one value.
        UnbekanntesSymptomAussageUeberDieFehlendeInformationElement aussageUberDieFehlendeInformationElement = new UnbekanntesSymptomAussageUeberDieFehlendeInformationElement();
        aussageUberDieFehlendeInformationElement.setValue(DefiningCode.N261665006.toDvCodedText());

        if (unbekanntesSymptom.getAussageUeberDieFehlendeInformation() == null) {
            unbekanntesSymptom.setAussageUeberDieFehlendeInformation(new ArrayList<>());
        }

        unbekanntesSymptom.getAussageUeberDieFehlendeInformation().add(aussageUberDieFehlendeInformationElement);
        unbekanntesSymptom.setSubject(new PartySelf());
        unbekanntesSymptom.setLanguage(Language.DE);
        composition.setUnbekanntesSymptom(unbekanntesSymptom);
    }
}
