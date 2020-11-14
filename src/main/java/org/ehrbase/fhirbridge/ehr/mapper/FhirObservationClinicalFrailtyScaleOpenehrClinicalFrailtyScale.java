package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class FhirObservationClinicalFrailtyScaleOpenehrClinicalFrailtyScale {

    private static class ClinicalFrailty_Mapping_Assessment {

        private final static DvOrdinal ClinFrailty_Beurteilung_1_SEHR_FIT = new DvOrdinal(1L,
                new DvCodedText("1", new CodePhrase(new TerminologyId("local"), "at0005")));
        private final static DvOrdinal ClinFrailty_Beurteilung_2_DURCHSCHNITTLICH_AKTIV = new DvOrdinal(2L,
                new DvCodedText("2", new CodePhrase(new TerminologyId("local"), "at0006")));
        private final static DvOrdinal ClinFrailty_Beurteilung_3_GUT_ZURECHTKOMMEND = new DvOrdinal(3L,
                new DvCodedText("3", new CodePhrase(new TerminologyId("local"), "at0007")));
        private final static DvOrdinal ClinFrailty_Beurteilung_4_VULNERABEL = new DvOrdinal(4L,
                new DvCodedText("4", new CodePhrase(new TerminologyId("local"), "at0008")));
        private final static DvOrdinal ClinFrailty_Beurteilung_5_GERINGGRADIG_FRAIL = new DvOrdinal(5L,
                new DvCodedText("5", new CodePhrase(new TerminologyId("local"), "at0009")));
        private final static DvOrdinal ClinFrailty_Beurteilung_6_MITTELGRADIG_FRAIL = new DvOrdinal(6L,
                new DvCodedText("6", new CodePhrase(new TerminologyId("local"), "at0010")));
        private final static DvOrdinal ClinFrailty_Beurteilung_7_AUSGEPRAGT_FRAIL = new DvOrdinal(7L,
                new DvCodedText("7", new CodePhrase(new TerminologyId("local"), "at0011")));
        private final static DvOrdinal ClinFrailty_Beurteilung_8_EXTREM_FRAIL = new DvOrdinal(8L,
                new DvCodedText("8", new CodePhrase(new TerminologyId("local"), "at0012")));
        private final static DvOrdinal ClinFrailty_Beurteilung_9_TERMINAL_ERKRANKT = new DvOrdinal(9L,
                new DvCodedText("9", new CodePhrase(new TerminologyId("local"), "at0013")));

        public DvOrdinal getDVOrdinal(int code) {
            DvOrdinal ret;
            switch (code) {
                case 1:
                    ret = ClinFrailty_Beurteilung_1_SEHR_FIT;
                    break;
                case 2:
                    ret = ClinFrailty_Beurteilung_2_DURCHSCHNITTLICH_AKTIV;
                    break;
                case 3:
                    ret = ClinFrailty_Beurteilung_3_GUT_ZURECHTKOMMEND;
                    break;
                case 4:
                    ret = ClinFrailty_Beurteilung_4_VULNERABEL;
                    break;
                case 5:
                    ret = ClinFrailty_Beurteilung_5_GERINGGRADIG_FRAIL;
                    break;
                case 6:
                    ret = ClinFrailty_Beurteilung_6_MITTELGRADIG_FRAIL;
                    break;
                case 7:
                    ret = ClinFrailty_Beurteilung_7_AUSGEPRAGT_FRAIL;
                    break;
                case 8:
                    ret = ClinFrailty_Beurteilung_8_EXTREM_FRAIL;
                    break;
                case 9:
                    ret = ClinFrailty_Beurteilung_9_TERMINAL_ERKRANKT;
                    break;
                default:
                    throw new UnprocessableEntityException("Cannot match beurteilung\"" + code + "\"");
            }
            return ret;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(FhirObservationClinicalFrailtyScaleOpenehrClinicalFrailtyScale.class);

    private FhirObservationClinicalFrailtyScaleOpenehrClinicalFrailtyScale() {
    }

    public static KlinischeFrailtySkalaComposition map(Observation fhirObservation) {

        KlinischeFrailtySkalaComposition composition = new KlinischeFrailtySkalaComposition();
        KlinischeFrailtySkalaCfsObservation observation = new KlinischeFrailtySkalaCfsObservation();

        DateTimeType fhirEffectiveDateTime = null;
        try {

            // default for every observation
            fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
            observation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            observation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            observation.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
            observation.setSubject(new PartySelf());

            // special mapping content

            // is this the correct equivalent?
            //Fhir-Example: Observation.valueCodeableConcept[0].coding[0].code[0]
            // I tried to access the object, but it crashes during runtime:
            String string_assessment = fhirObservation.getValueCodeableConcept().getCoding().get(0).getCode();
            int assessment = Integer.parseInt(string_assessment);

            // get the mapping to the DV_Ordinal from inner class
            ClinicalFrailty_Mapping_Assessment mapping = new ClinicalFrailty_Mapping_Assessment();
            DvOrdinal ord_assessment = mapping.getDVOrdinal(assessment);

            observation.setBeurteilung(ord_assessment);

            //observation.setMesswertMagnitude(fhirValueNumeric.doubleValue());
            //observation.setMesswertUnits(fhirValue.getUnit());


        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        composition.setKlinischeFrailtySkalaCfs(observation);

        // Required fields by API
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        composition.setComposer(new PartySelf()); // FIXME: id ausdenken oder weglassen?

        return composition;
    }
}

