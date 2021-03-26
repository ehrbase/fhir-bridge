package org.ehrbase.fhirbridge.ehr.converter.specific.diagnose;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.DiagnoseComposition;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.AnatomischeLokalisationCluster;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.NameDesProblemsDerDiagnoseDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.ProblemDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.ProblemDiagnoseSchweregradDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.SchweregradDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;
import java.util.List;

public class DiagnoseCompositionConverter extends CompositionConverter<Condition, DiagnoseComposition> {

    private static final Logger LOG = LoggerFactory.getLogger(DiagnoseCompositionConverter.class);

    @Override
    public DiagnoseComposition convertInternal(@NonNull Condition resource) {
        DiagnoseComposition composition = new DiagnoseComposition();

        DateTimeType fhirOnsetDateTime = resource.getOnsetDateTimeType();
        Coding fhirSeverity = resource.getSeverity().getCoding().get(0);
        Coding fhirDiagnosis = resource.getCode().getCoding().get(0);
        ProblemDiagnoseEvaluation evaluation = new ProblemDiagnoseEvaluation();
        evaluation.setLanguage(Language.EN);
        evaluation.setSubject(new PartySelf());
        evaluation.setLetztesDokumentationsdatumValue(OffsetDateTime.now());

        // severity
        SchweregradDefiningCode openEHRSeverity;
        if (!fhirSeverity.getSystem().equalsIgnoreCase("http://snomed.info/sct")) {
            throw new UnprocessableEntityException("severity code system should be http://snomed.info/sct, found " + fhirSeverity.getSystem());
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
        evaluation.setSchweregrad(severityCoded);


        // der diagnose
        // the OPT uses only ICD10 codes from the German version of ICD
        // https://www.hl7.org/fhir/icd.html
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
        evaluation.setNameDesProblemsDerDiagnoseDefiningCode(openEHRDiagnosis);
        // date onset
        evaluation.setDatumDesAuftretensDerErstdiagnoseValue(fhirOnsetDateTime.getValueAsCalendar().toZonedDateTime());
        // body site
        if (resource.getBodySite().size() == 1) {
            String bodySiteName = resource.getBodySite().get(0).getCoding().get(0).getDisplay();
            evaluation.setLokalisationValue("body site");
            AnatomischeLokalisationCluster anatomischeLokalisationCluster = new AnatomischeLokalisationCluster();
            anatomischeLokalisationCluster.setNameDerKoerperstelleValue(bodySiteName);
            evaluation.setAnatomischeLokalisation(List.of(anatomischeLokalisationCluster));
        }


        composition.setProblemDiagnose(evaluation);


        // check if the condition has a recorded date, if not, use the onset
        DateTimeType aDate = resource.getRecordedDateElement();
        LOG.debug("recorded is {}", aDate);
        if (aDate.isEmpty()) {
            LOG.debug("recorded date is null trying onset");
            aDate = resource.getOnsetDateTimeType();
            LOG.debug("onset is {}", aDate);
        }
        composition.setStartTimeValue(aDate.getValueAsCalendar().toZonedDateTime());

        // https://github.com/ehrbase/ehrbase_client_library/issues/31
        PartyIdentified composer = new PartyIdentified();
        DvIdentifier identifier = new DvIdentifier();
        identifier.setId(resource.getRecorder().getReference());
        composer.addIdentifier(identifier);
        composition.setComposer(composer);

        //composition.setComposer(new PartySelf());

        return composition;

    }
}
