package org.ehrbase.fhirbridge.ehr.converter.specific.diagnose;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.AnatomischeLokalisationCluster;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.NameDesProblemsDerDiagnoseDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.ProblemDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.ProblemDiagnoseSchweregradChoice;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.ProblemDiagnoseSchweregradDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.SchweregradDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;

import java.time.OffsetDateTime;
import java.util.List;

public class ProblemDiagnoseEvaluationConverter extends EntryEntityConverter<Condition, ProblemDiagnoseEvaluation> {

    @Override
    protected ProblemDiagnoseEvaluation convertInternal(Condition resource) {

        ProblemDiagnoseEvaluation evaluation = new ProblemDiagnoseEvaluation();
        evaluation.setLetztesDokumentationsdatumValue(OffsetDateTime.now());

        if(resource.hasSeverity() && resource.getSeverity().hasCoding()){
            evaluation.setSchweregrad(getSchweregrad(resource.getSeverity().getCoding().get(0)));
        }
        if(resource.hasCode() && resource.getCode().hasCoding()){
            evaluation.setNameDesProblemsDerDiagnoseDefiningCode(getNameDesProblemsDerDiagnose(resource.getCode().getCoding().get(0)));
        }
        if(resource.hasOnset()){
            DateTimeType fhirOnsetDateTime = resource.getOnsetDateTimeType();
            evaluation.setDatumDesAuftretensDerErstdiagnoseValue(fhirOnsetDateTime.getValueAsCalendar().toZonedDateTime());
        }
        
        if (resource.getBodySite().size() == 1) {
            String bodySiteName = resource.getBodySite().get(0).getCoding().get(0).getDisplay();
            evaluation.setLokalisationValue("body site");
            AnatomischeLokalisationCluster anatomischeLokalisationCluster = new AnatomischeLokalisationCluster();
            anatomischeLokalisationCluster.setNameDerKoerperstelleValue(bodySiteName);
            evaluation.setAnatomischeLokalisation(List.of(anatomischeLokalisationCluster));
        }
        return evaluation;
    }

    private NameDesProblemsDerDiagnoseDefiningCode getNameDesProblemsDerDiagnose(Coding fhirDiagnosis) {
        NameDesProblemsDerDiagnoseDefiningCode openEHRDiagnosis;
        if (!fhirDiagnosis.getSystem().equalsIgnoreCase("http://fhir.de/CodeSystem/dimdi/icd-10-gm")) {
            throw new ConversionException("code.system should be http://fhir.de/CodeSystem/dimdi/icd-10-gm but found" + fhirDiagnosis.getSystem());
        }
        switch (fhirDiagnosis.getCode()) {
            case "B97.2":
                openEHRDiagnosis = NameDesProblemsDerDiagnoseDefiningCode.KORONAVIREN_ALS_URSACHE_VON_KRANKHEITEN_DIE_IN_ANDEREN_KAPITELN_KLASSIFIZIERT_SIND;
                break;
            case "U07.1":
                openEHRDiagnosis = NameDesProblemsDerDiagnoseDefiningCode.COVID19_VIRUS_NACHGEWIESEN;
                break;
            case "U07.2":
                openEHRDiagnosis = NameDesProblemsDerDiagnoseDefiningCode.COVID19_VIRUS_NICHT_NACHGEWIESEN;
                break;
            case "B34.2":
                openEHRDiagnosis = NameDesProblemsDerDiagnoseDefiningCode.INFEKTION_DURCH_KORONAVIREN_NICHT_NAEHER_BEZEICHNETER_LOKALISATION;
                break;
            default:
                throw new ConversionException("Unexpected value: " + fhirDiagnosis.getCode());
        }
        return openEHRDiagnosis;
    }

    private ProblemDiagnoseSchweregradChoice getSchweregrad(Coding fhirSeverity) {
        SchweregradDefiningCode openEHRSeverity;
        if (!fhirSeverity.getSystem().equalsIgnoreCase("http://snomed.info/sct")) {
            throw new ConversionException("severity code system should be http://snomed.info/sct, found " + fhirSeverity.getSystem());
        }
        switch (fhirSeverity.getCode()) {
            case "24484000":
                openEHRSeverity = SchweregradDefiningCode.SCHWER;
                break;
            case "6736007":
                openEHRSeverity = SchweregradDefiningCode.MAESSIG;
                break;
            case "255604002":
                openEHRSeverity = SchweregradDefiningCode.LEICHT;
                break;
            default:
                throw new ConversionException("Unexpected value: " + fhirSeverity.getCode());
        }
        ProblemDiagnoseSchweregradDvCodedText severityCoded = new ProblemDiagnoseSchweregradDvCodedText();
        severityCoded.setSchweregradDefiningCode(openEHRSeverity);
        return severityCoded;
    }
}
