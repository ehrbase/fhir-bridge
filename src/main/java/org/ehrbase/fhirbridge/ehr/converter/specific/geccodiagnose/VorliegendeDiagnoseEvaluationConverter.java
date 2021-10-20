package org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.KoerperstelleCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.SchweregradDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseSchweregradChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseSchweregradDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseSchweregradDvText;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

import java.util.ArrayList;
import java.util.List;

public class VorliegendeDiagnoseEvaluationConverter extends EntryEntityConverter<Condition, VorliegendeDiagnoseEvaluation> {

    private final DvCodedTextParser dvCodedTextParser = DvCodedTextParser.getInstance();

    boolean isEmpty = true;
    private final List<String> severityCodes = new ArrayList<>() {{
        add("255604002");
        add("6736007");
        add("24484000");
        add("442452003");
    }};

    @Override
    protected VorliegendeDiagnoseEvaluation convertInternal(Condition resource) {
        isEmpty = true;
        VorliegendeDiagnoseEvaluation vorliegendeDiagnose = new VorliegendeDiagnoseEvaluation();
        mapNameDesProblemsDerDiagnose(resource, vorliegendeDiagnose);
        mapBodySite(resource, vorliegendeDiagnose);
        mapSeverity(resource, vorliegendeDiagnose);
        mapKommentar(resource, vorliegendeDiagnose);
        mapDates(resource, vorliegendeDiagnose);
        return vorliegendeDiagnose;
    }

    private void mapKommentar(Condition condition, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
        if (condition.hasNote()) {
            StringBuilder kommentar = new StringBuilder();
            for (Annotation annotation : condition.getNote()) {
                kommentar.append(annotation.getText());
            }
            vorliegendeDiagnose.setKommentarValue(kommentar.toString());
            isEmpty = false;
        }

    }

    private void mapDates(Condition condition, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
        if (condition.hasOnset()) {
            vorliegendeDiagnose.setDatumZeitpunktDesAuftretensDerErstdiagnoseValue(TimeConverter.convertConditionTime(condition));
            isEmpty = false;
        }
        if (condition.hasAbatement() && TimeConverter.convertConditionAbatementTime(condition).isPresent()) {
            vorliegendeDiagnose.setDatumZeitpunktDerGenesungValue(TimeConverter.convertConditionAbatementTime(condition).get());
            isEmpty = false;
        }
    }

    private void mapSeverity(Condition condition, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
        if (condition.hasSeverity()) {
            for (Coding coding : condition.getSeverity().getCoding()) {
                convertSevertiy(coding, vorliegendeDiagnose);
            }
        }
    }

    private void convertSevertiy(Coding coding, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) { //  Symptoms Covid-19 requires severity
        if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl()) && severityCodes.contains(coding.getCode())) {
            vorliegendeDiagnose.setSchweregrad(convertDvCodedText(coding));
            isEmpty = false;
        } else {
            vorliegendeDiagnose.setSchweregrad(convertDvText(coding));
            isEmpty = false;
        }
    }

    private VorliegendeDiagnoseSchweregradChoice convertDvText(Coding coding) {
        VorliegendeDiagnoseSchweregradDvText vorliegendeDiagnoseSchweregradDvText = new VorliegendeDiagnoseSchweregradDvText();
        if (coding.hasDisplay()) {
            vorliegendeDiagnoseSchweregradDvText.setSchweregradValue(coding.getDisplay());
        } else {
            vorliegendeDiagnoseSchweregradDvText.setSchweregradValue(coding.getCode());
        }
        return vorliegendeDiagnoseSchweregradDvText;
    }

    private VorliegendeDiagnoseSchweregradDvCodedText convertDvCodedText(Coding coding) {
        VorliegendeDiagnoseSchweregradDvCodedText vorliegendeDiagnoseSchweregradDvCodedText = new VorliegendeDiagnoseSchweregradDvCodedText();
        switch (coding.getCode()) {
            case "255604002":
                vorliegendeDiagnoseSchweregradDvCodedText.setSchweregradDefiningCode(SchweregradDefiningCode.LEICHT);
                break;
            case "6736007":
                vorliegendeDiagnoseSchweregradDvCodedText.setSchweregradDefiningCode(SchweregradDefiningCode.MAESSIG);
                break;
            case "24484000":
            case "442452003":
                vorliegendeDiagnoseSchweregradDvCodedText.setSchweregradDefiningCode(SchweregradDefiningCode.SCHWER);
                break;
            default:
                throw new UnprocessableEntityException("The code " + coding + " is not a valid value!");
        }
        return vorliegendeDiagnoseSchweregradDvCodedText;
    }


    private void mapBodySite(Condition condition, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
        if (condition.hasBodySite()) {
            for (CodeableConcept codeableConcept : condition.getBodySite()) {
                for (Coding coding : codeableConcept.getCoding()) {
                    KoerperstelleCluster korperstelleCluster = new KoerperstelleCluster();
                    dvCodedTextParser.parseFHIRCoding(coding).ifPresent(korperstelleCluster::setNameDerKoerperstelle);
                    addKoerperstelleCluster(korperstelleCluster, vorliegendeDiagnose);
                    isEmpty = false;
                }
            }
        }
    }

    private void addKoerperstelleCluster(KoerperstelleCluster korperstelleCluster, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
        if (vorliegendeDiagnose.getKoerperstelle() == null || vorliegendeDiagnose.getKoerperstelle().size() == 0) {
            vorliegendeDiagnose.setKoerperstelle(List.of(korperstelleCluster));
        } else {
            vorliegendeDiagnose.getKoerperstelle().add(korperstelleCluster);
        }
        isEmpty = false;
    }

    private void mapNameDesProblemsDerDiagnose(Condition condition, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
        for (Coding coding : condition.getCode().getCoding()) {
            if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
                dvCodedTextParser.parseFHIRCoding(coding).ifPresent(vorliegendeDiagnose::setNameDesProblemsDerDiagnose);
                isEmpty = false;
            }
        }
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }
}

