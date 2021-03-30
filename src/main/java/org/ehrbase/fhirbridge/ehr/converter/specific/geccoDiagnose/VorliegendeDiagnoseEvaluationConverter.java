package org.ehrbase.fhirbridge.ehr.converter.specific.geccoDiagnose;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.KoerperstelleCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseEvaluation;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

import java.util.List;
import java.util.Optional;

public class VorliegendeDiagnoseEvaluationConverter extends EntryEntityConverter<Condition, VorliegendeDiagnoseEvaluation> {

    boolean isEmpty = true;

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
        if (!condition.getNote().isEmpty()) {
            StringBuilder kommentar = new StringBuilder();
            for (Annotation annotation : condition.getNote()) {
                kommentar.append(annotation.getText());
            }
            vorliegendeDiagnose.setKommentarValue(kommentar.toString());
            isEmpty=false;
        }

    }

    private void mapDates(Condition condition, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
        if (condition.getOnsetDateTimeType() != null && condition.getOnsetDateTimeType().getValueAsCalendar() != null) {
            vorliegendeDiagnose.setDatumZeitpunktDesAuftretensDerErstdiagnoseValue(condition.getOnsetDateTimeType().getValueAsCalendar().toZonedDateTime());
            isEmpty=false;
        }

        if (condition.getAbatementDateTimeType() != null && condition.getAbatementDateTimeType().getValueAsCalendar() != null) {
            vorliegendeDiagnose.setDatumZeitpunktDerGenesungValue(condition.getAbatementDateTimeType().getValueAsCalendar().toZonedDateTime());
            isEmpty=false;
        }

    }

    private void mapSeverity(Condition condition, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
        if (!condition.getSeverity().isEmpty()) {
            Coding severity = condition.getSeverity().getCoding().get(0);
            if (severity.getSystem().equals(CodeSystem.SNOMED.getUrl()) && GeccoDiagnoseCodeDefiningCodeMaps.getSchweregradMap().containsKey(severity.getCode())) {
                vorliegendeDiagnose.setSchweregradDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getSchweregradMap().get(severity.getCode()));
                isEmpty=false;
            } else {
                throw new ConversionException("Severity not processable.");
            }
        }
    }

    private void mapBodySite(Condition condition, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
        if (!condition.getBodySite().isEmpty()) {
            for (Coding bodySite : condition.getBodySite().get(0).getCoding()) {
                if (bodySite.getSystem().equals(CodeSystem.SNOMED.getUrl()) && GeccoDiagnoseCodeDefiningCodeMaps.getKoerperstelleMap().containsKey(bodySite.getCode())) {
                    KoerperstelleCluster korperstelleCluster = new KoerperstelleCluster();
                    korperstelleCluster.setNameDerKoerperstelleDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getKoerperstelleMap().get(bodySite.getCode()));
                    addKoerperstelleCluster(korperstelleCluster, vorliegendeDiagnose);
                } else {
                    throw new ConversionException("Body site not processable.");
                }
            }
        }
    }

    private void addKoerperstelleCluster(KoerperstelleCluster korperstelleCluster, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
        if (vorliegendeDiagnose.getKoerperstelle() == null || vorliegendeDiagnose.getKoerperstelle().size() == 0) {
            vorliegendeDiagnose.setKoerperstelle(List.of(korperstelleCluster));
            isEmpty=false;

        } else {
            vorliegendeDiagnose.getKoerperstelle().add(korperstelleCluster);
            isEmpty=false;
        }
    }

    private void mapNameDesProblemsDerDiagnose(Condition condition, VorliegendeDiagnoseEvaluation vorliegendeDiagnose) {
        Coding problem = condition.getCode().getCoding().get(0);
        if (problem.getSystem().equals(CodeSystem.SNOMED.getUrl()) &&
                GeccoDiagnoseCodeDefiningCodeMaps.getProblemDiagnoseMap().containsKey(problem.getCode())) {
            vorliegendeDiagnose.setNameDesProblemsDerDiagnoseDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getNameDesProblemDiagnoseMap().get(problem.getCode()));
            isEmpty=false;
        }
    }

    public boolean getIsEmpty(){
        return isEmpty;
    }
}

