package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.GECCODiagnoseComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.ProblemDiagnoseDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.NameDesProblemsDerDiagnoseDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.KategorieDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.SchweregradDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.NameDerKorperstelleDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AusgeschlosseneDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.UnbekannteDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.KorperstelleCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AussageUberDenAusschlussDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AussageUberDieFehlendeInformationDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GECCODiagnoseCompositionConverter implements CompositionConverter<GECCODiagnoseComposition, Condition> {

    private static final Logger LOG = LoggerFactory.getLogger(BloodPressureCompositionConverter.class);

    private static final Map<String, KategorieDefiningcode> kategorieMap = new HashMap<>();
    private static final Map<String, ProblemDiagnoseDefiningcode> problemDiagnoseMap = new HashMap<>();
    private static final Map<String, NameDesProblemsDerDiagnoseDefiningcode> vorliegendesProblemDiagnoseMap = new HashMap<>();
    private static final Map<String, NameDerKorperstelleDefiningcode> koerperstelleMap = new HashMap<>();
    private static final Map<String, SchweregradDefiningcode> schweregradMap = new HashMap<>();

    private static String VERIFICATION_STATUS_PRESENT_CODE = "410605003";
    private static String VERIFICATION_STATUS_ABSENT_CODE = "410594000";

    private static String SNOMED_SYSTEM = "http://snomed.info/sct";


    static {
        for (KategorieDefiningcode kategorie : KategorieDefiningcode.values()) {
            kategorieMap.put(kategorie.getCode(), kategorie);
        }

        for (ProblemDiagnoseDefiningcode problem : ProblemDiagnoseDefiningcode.values()) {
            problemDiagnoseMap.put(problem.getCode(), problem);
        }

        for (NameDesProblemsDerDiagnoseDefiningcode problem : NameDesProblemsDerDiagnoseDefiningcode.values()) {
            vorliegendesProblemDiagnoseMap.put(problem.getCode(), problem);
        }

        for (NameDerKorperstelleDefiningcode korperstelle : NameDerKorperstelleDefiningcode.values()) {
            koerperstelleMap.put(korperstelle.getCode(), korperstelle);
        }

        for (SchweregradDefiningcode schweregrad : SchweregradDefiningcode.values()) {
            schweregradMap.put(schweregrad.getCode(), schweregrad);
        }
    }

    @Override
    public Condition fromComposition(GECCODiagnoseComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public GECCODiagnoseComposition toComposition(Condition condition) {
        GECCODiagnoseComposition composition = new GECCODiagnoseComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(condition);
        composition.setFeederAudit(fa);

        if (condition.getVerificationStatus().isEmpty()) {
            composition.setUnbekannteDiagnose(this.toUnbekannteDiagnose(condition));
        } else {
            Coding verficiationStatus = condition.getVerificationStatus().getCoding().get(
                    condition.getVerificationStatus().getCoding().size() - 1); // snomed code is the last element

            if (verficiationStatus.getSystem().equals(SNOMED_SYSTEM) &&
                    verficiationStatus.getCode().equals(VERIFICATION_STATUS_PRESENT_CODE)) {
                composition.setVorliegendeDiagnose(this.toVorliegendeDiagnose(condition));
            } else if (verficiationStatus.getSystem().equals(SNOMED_SYSTEM) &&
                    verficiationStatus.getCode().equals(VERIFICATION_STATUS_ABSENT_CODE)) {
                composition.setAusgeschlosseneDiagnose(this.toAusgeschlosseneDiagnose(condition));
            } else {
                throw new UnprocessableEntityException("Cant identify the verification status");
            }
        }

        Coding categoryCoding = condition.getCategory().get(0).getCoding().get(0);

        if (categoryCoding.getSystem().equals(SNOMED_SYSTEM) && kategorieMap.containsKey(categoryCoding.getCode())) {
            composition.setKategorieDefiningcode(kategorieMap.get(categoryCoding.getCode()));
        } else {
            throw new UnprocessableEntityException("Category not present");
        }


        composition.setStartTimeValue(condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());


        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test"); // FIXME: Location abfangen?
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);

        composition.setComposer(new PartySelf());

        return composition;
    }

    private VorliegendeDiagnoseEvaluation toVorliegendeDiagnose(Condition condition) {
        VorliegendeDiagnoseEvaluation vorliegendeDiagnose = new VorliegendeDiagnoseEvaluation();


        // Map name des problems
        Coding problem = condition.getCode().getCoding().get(0);
        if (problem.getSystem().equals(SNOMED_SYSTEM) &&
                vorliegendesProblemDiagnoseMap.containsKey(problem.getCode())) {
            vorliegendeDiagnose.setNameDesProblemsDerDiagnoseDefiningcode(vorliegendesProblemDiagnoseMap.get(problem.getCode()));
        }


        // Body Site
        if (!condition.getBodySite().isEmpty()) {

            vorliegendeDiagnose.setKorperstelle(new ArrayList<>());

            for (Coding bodySite : condition.getBodySite().get(0).getCoding()) {
                if (bodySite.getSystem().equals(SNOMED_SYSTEM) && koerperstelleMap.containsKey(bodySite.getCode())) {
                    KorperstelleCluster korperstelleCluster = new KorperstelleCluster();
                    korperstelleCluster.setNameDerKorperstelleDefiningcode(koerperstelleMap.get(bodySite.getCode()));

                    vorliegendeDiagnose.getKorperstelle().add(korperstelleCluster);
                } else {
                    throw new UnprocessableEntityException("Body site not processable.");
                }
            }
        }

        // Severity
        if (!condition.getSeverity().isEmpty()) {
            Coding severity = condition.getSeverity().getCoding().get(0);

            if (severity.getSystem().equals(SNOMED_SYSTEM) && schweregradMap.containsKey(severity.getCode())) {
                vorliegendeDiagnose.setSchweregradDefiningcode(schweregradMap.get(severity.getCode()));
            } else {
                throw new UnprocessableEntityException("Severity not processable.");
            }
        }

        // Note
        if (!condition.getNote().isEmpty()) {
            StringBuilder kommentar = new StringBuilder();

            for (Annotation annotation : condition.getNote()) {
                kommentar.append(annotation.getText());
            }

            vorliegendeDiagnose.setKommentarValue(kommentar.toString());
        }


        if(condition.getOnsetDateTimeType() != null && condition.getOnsetDateTimeType().getValueAsCalendar() != null) {
            vorliegendeDiagnose.setDatumZeitpunktDesAuftretensDerErstdiagnoseValue(condition.getOnsetDateTimeType().getValueAsCalendar().toZonedDateTime());
        }

        if(condition.getAbatementDateTimeType() != null && condition.getAbatementDateTimeType().getValueAsCalendar() != null) {
            vorliegendeDiagnose.setDatumZeitpunktDerGenesungValue(condition.getAbatementDateTimeType().getValueAsCalendar().toZonedDateTime());
        }

        vorliegendeDiagnose.setSubject(new PartySelf());
        vorliegendeDiagnose.setLanguage(Language.DE); // FIXME: we need to grab the language from the template

        return vorliegendeDiagnose;
    }

    private UnbekannteDiagnoseEvaluation toUnbekannteDiagnose(Condition condition) {

        UnbekannteDiagnoseEvaluation unbekannteDiagnose = new UnbekannteDiagnoseEvaluation();

        unbekannteDiagnose.setAussageUberDieFehlendeInformationDefiningcode(AussageUberDieFehlendeInformationDefiningcode.UNKNOWN_QUALIFIER_VALUE);

        // Map problem
        Coding problem = condition.getCode().getCoding().get(0);
        if (problem.getSystem().equals(SNOMED_SYSTEM) &&
                problemDiagnoseMap.containsKey(problem.getCode())) {
            unbekannteDiagnose.setUnbekannteDiagnoseDefiningcode(problemDiagnoseMap.get(problem.getCode()));
        }

        unbekannteDiagnose.setSubject(new PartySelf());
        unbekannteDiagnose.setLanguage(Language.DE); // FIXME: we need to grab the language from the template

        return unbekannteDiagnose;
    }


    private AusgeschlosseneDiagnoseEvaluation toAusgeschlosseneDiagnose(Condition condition) {

        AusgeschlosseneDiagnoseEvaluation ausgeschlosseneDiagnose = new AusgeschlosseneDiagnoseEvaluation();

        ausgeschlosseneDiagnose.setAussageUberDenAusschlussDefiningcode(AussageUberDenAusschlussDefiningcode.KNOWN_ABSENT_QUALIFIER_VALUE);

        // Map problem
        Coding problem = condition.getCode().getCoding().get(0);
        if (problem.getSystem().equals(SNOMED_SYSTEM) &&
                problemDiagnoseMap.containsKey(problem.getCode())) {
            ausgeschlosseneDiagnose.setProblemDiagnoseDefiningcode(problemDiagnoseMap.get(problem.getCode()));
        }

        ausgeschlosseneDiagnose.setSubject(new PartySelf());
        ausgeschlosseneDiagnose.setLanguage(Language.DE); // FIXME: we need to grab the language from the template

        return ausgeschlosseneDiagnose;
    }
}

