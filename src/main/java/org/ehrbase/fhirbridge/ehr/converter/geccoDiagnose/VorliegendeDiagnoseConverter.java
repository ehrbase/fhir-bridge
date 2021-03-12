package org.ehrbase.fhirbridge.ehr.converter.geccoDiagnose;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.KoerperstelleCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseEvaluation;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

import java.util.List;
import java.util.Optional;

public class VorliegendeDiagnoseConverter {
    private final String SNOMED_SYSTEM = "http://snomed.info/sct";
    private Optional<VorliegendeDiagnoseEvaluation> vorliegendeDiagnoseEvaluationOptional = Optional.empty();

    Optional<VorliegendeDiagnoseEvaluation> map(Condition condition) {
        // Map name des problems
        mapNameDesProblemsDerDiagnose(condition);
        mapBodySite(condition);
        mapSeverity(condition);
        mapKommentar(condition);
        mapDates(condition);
        return vorliegendeDiagnoseEvaluationOptional;
    }


    private void mapKommentar(Condition condition) {
        if (!condition.getNote().isEmpty()) {
            StringBuilder kommentar = new StringBuilder();

            for (Annotation annotation : condition.getNote()) {
                kommentar.append(annotation.getText());
            }

            getVorliegendeDiagnose().setKommentarValue(kommentar.toString());
        }

    }

    private void mapDates(Condition condition) {
        if (condition.getOnsetDateTimeType() != null && condition.getOnsetDateTimeType().getValueAsCalendar() != null) {
            getVorliegendeDiagnose().setDatumZeitpunktDesAuftretensDerErstdiagnoseValue(condition.getOnsetDateTimeType().getValueAsCalendar().toZonedDateTime());
        }

        if (condition.getAbatementDateTimeType() != null && condition.getAbatementDateTimeType().getValueAsCalendar() != null) {
            getVorliegendeDiagnose().setDatumZeitpunktDerGenesungValue(condition.getAbatementDateTimeType().getValueAsCalendar().toZonedDateTime());
        }

    }

    private void mapSeverity(Condition condition) {
        if (!condition.getSeverity().isEmpty()) {
            Coding severity = condition.getSeverity().getCoding().get(0);

            if (severity.getSystem().equals(SNOMED_SYSTEM) && GeccoDiagnoseCodeDefiningCodeMaps.getSchweregradMap().containsKey(severity.getCode())) {
                getVorliegendeDiagnose().setSchweregradDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getSchweregradMap().get(severity.getCode()));
            } else {
                throw new ConversionException("Severity not processable.");
            }
        }
    }

    private void mapBodySite(Condition condition) {
        if (!condition.getBodySite().isEmpty()) {
            for (Coding bodySite : condition.getBodySite().get(0).getCoding()) {
                if (bodySite.getSystem().equals(SNOMED_SYSTEM) && GeccoDiagnoseCodeDefiningCodeMaps.getKoerperstelleMap().containsKey(bodySite.getCode())) {
                    KoerperstelleCluster korperstelleCluster = new KoerperstelleCluster();
                    korperstelleCluster.setNameDerKoerperstelleDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getKoerperstelleMap().get(bodySite.getCode()));
                    addKoerperstelleCluster(korperstelleCluster);
                } else {
                    throw new ConversionException("Body site not processable.");
                }
            }
        }

    }

    private void addKoerperstelleCluster(KoerperstelleCluster korperstelleCluster) {
        if (getVorliegendeDiagnose().getKoerperstelle() == null || getVorliegendeDiagnose().getKoerperstelle().size() == 0) {
            getVorliegendeDiagnose().setKoerperstelle(List.of(korperstelleCluster));
        } else {
            getVorliegendeDiagnose().getKoerperstelle().add(korperstelleCluster);
        }
    }

    private void mapNameDesProblemsDerDiagnose(Condition condition) {
        Coding problem = condition.getCode().getCoding().get(0);
        if (problem.getSystem().equals(SNOMED_SYSTEM) &&
                GeccoDiagnoseCodeDefiningCodeMaps.getProblemDiagnoseMap().containsKey(problem.getCode())) {
            getVorliegendeDiagnose().setNameDesProblemsDerDiagnoseDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getNameDesProblemDiagnoseMap().get(problem.getCode()));
        }
    }

    private VorliegendeDiagnoseEvaluation getVorliegendeDiagnose() {
        if (vorliegendeDiagnoseEvaluationOptional.isEmpty()) {
            VorliegendeDiagnoseEvaluation vorliegendeDiagnose = new VorliegendeDiagnoseEvaluation();
            vorliegendeDiagnose.setLanguage(Language.DE);
            vorliegendeDiagnose.setSubject(new PartySelf());
            vorliegendeDiagnoseEvaluationOptional = Optional.of(vorliegendeDiagnose);
            return vorliegendeDiagnoseEvaluationOptional.get();
        } else {
            return vorliegendeDiagnoseEvaluationOptional.get();
        }
    }
}
