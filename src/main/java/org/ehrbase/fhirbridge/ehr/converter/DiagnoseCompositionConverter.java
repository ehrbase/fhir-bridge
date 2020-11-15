package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.Composition;
import org.ehrbase.fhirbridge.ehr.mapper.CommonData;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.DiagnoseComposition;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.AtiopathogeneseSchweregradDvcodedtext;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.DiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition.SchweregradDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.DerDiagnoseDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class DiagnoseCompositionConverter implements CompositionConverter {

    private final Logger logger = LoggerFactory.getLogger(DiagnoseCompositionConverter.class);

    @Override
    public Condition fromComposition(Composition composition) {
        if (composition == null) {
            return null;
        }
        DiagnoseComposition that = (DiagnoseComposition) composition;

        Condition result = new Condition();
        TemporalAccessor temporal;
        String text;
        Coding coding;

        // mapping back to FHIR

        // the severity code stored in openEHR is the atcode of the constraint, is not the SNOMED code
        // this is because the OPT was designed this way and the generated code from the client lib
        // generates a ENUM with those codes.

        // severity code
        text = ((AtiopathogeneseSchweregradDvcodedtext) that.getDiagnose().getSchweregrad()).getSchweregradDefiningcode().getCode();

        // transforms atcodes in snomed codes
        switch (text) {
            case "at0049": // TODO: the enum classes need a method to create the Enum from the code value to avoid hardcoding
                text = "24484000";
                break;
            case "at0048":
                text = "6736007";
                break;
            case "at0047":
                text = "255604002";
                break;
            // TODO: define what to do when the code is not mappeable
            default:
                throw new IllegalArgumentException();
        }

        coding = result.getSeverity().addCoding();
        coding.setCode(text);
        coding.setSystem("http://snomed.info/sct");

        // diagnose code
        text = that.getDiagnose().getDerDiagnoseDefiningcode().getCode();
        coding = result.getCode().addCoding();
        coding.setCode(text);
        coding.setSystem("http://fhir.de/CodeSystem/dimdi/icd-10-gm");

        // date onset
        temporal = that.getDiagnose().getDerErstdiagnoseValue();
        result.getOnsetDateTimeType().setValue(Date.from(((OffsetDateTime) temporal).toInstant()));

        // body site
        text = that.getDiagnose().getKorperstelleValueStructure();
        result.addBodySite().addCoding().setDisplay(text);

        // FIXME: all FHIR resources need an ID, currently we are using the compo.uid as the resource ID,
        // this is a workaround, might not work on all cases.
        result.setId(that.getVersionUid().toString());
        return result;
    }

    @Override
    public Composition toComposition(Object object) {
        if (object == null) {
            return null;
        }
        Condition that = (Condition) object;

        DiagnoseComposition result = new DiagnoseComposition();
        FeederAudit fa = CommonData.constructFeederAudit(that);
        result.setFeederAudit(fa);

        // ========================================================================================
        // FHIR values
        DateTimeType fhirOnsetDateTime = that.getOnsetDateTimeType();
        Coding fhirSeverity = that.getSeverity().getCoding().get(0);
        Coding fhirDiagnosis = that.getCode().getCoding().get(0);


        // mapping to openEHR
        DiagnoseEvaluation evaluation = new DiagnoseEvaluation();
        evaluation.setLanguage(Language.EN);
        evaluation.setSubject(new PartySelf());
        evaluation.setZuletztAktualisiertValueTree("last update");
        evaluation.setZuletztAktualisiertValue(OffsetDateTime.now());

        // severity
        SchweregradDefiningcode openEHRSeverity;
        if (!fhirSeverity.getSystem().equalsIgnoreCase("http://snomed.info/sct")) {
            throw new UnprocessableEntityException("severity code system should be http://snomed.info/sct, found " + fhirSeverity.getSystem());
        }
        switch (fhirSeverity.getCode()) {
            case "24484000":
                openEHRSeverity = SchweregradDefiningcode.SCHWER;
                break;
            case "6736007":
                openEHRSeverity = SchweregradDefiningcode.MAIG;
                break;
            case "255604002":
                openEHRSeverity = SchweregradDefiningcode.LEICHT;
                break;
            default:
                throw new UnprocessableEntityException("Unexpected value: " + fhirSeverity.getCode());
        }

        AtiopathogeneseSchweregradDvcodedtext severityCoded = new AtiopathogeneseSchweregradDvcodedtext();
        severityCoded.setSchweregradDefiningcode(openEHRSeverity);
        evaluation.setSchweregrad(severityCoded);


        // der diagnose
        // the OPT uses only ICD10 codes from the German version of ICD
        // https://www.hl7.org/fhir/icd.html
        DerDiagnoseDefiningcode openEHRDiagnosis;
        if (!fhirDiagnosis.getSystem().equalsIgnoreCase("http://fhir.de/CodeSystem/dimdi/icd-10-gm")) {
            throw new UnprocessableEntityException("code.system should be http://fhir.de/CodeSystem/dimdi/icd-10-gm but found" + fhirDiagnosis.getSystem());
        }
        switch (fhirDiagnosis.getCode()) {
            case "B97.2":
                openEHRDiagnosis = DerDiagnoseDefiningcode.B972;
                break;
            case "U07.1":
                openEHRDiagnosis = DerDiagnoseDefiningcode.U071;
                break;
            case "U07.2":
                openEHRDiagnosis = DerDiagnoseDefiningcode.U072;
                break;
            case "B34.2":
                openEHRDiagnosis = DerDiagnoseDefiningcode.B342;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fhirDiagnosis.getCode());
        }
        evaluation.setDerDiagnoseDefiningcode(openEHRDiagnosis);


        // date onset
        evaluation.setDerErstdiagnoseValue(fhirOnsetDateTime.getValueAsCalendar().toZonedDateTime());
        evaluation.setDerErstdiagnoseValueZeitpunktDesAuftretens("date onset");


        // body site
        if (that.getBodySite().size() == 1) {
            String bodySiteName = that.getBodySite().get(0).getCoding().get(0).getDisplay();
            evaluation.setKorperstelleValue("body site");
            evaluation.setKorperstelleValueStructure(bodySiteName);
        }


        result.setDiagnose(evaluation);

        // ======================================================================================
        // Required fields by API
        result.setLanguage(Language.EN);
        result.setLocation("test");
        result.setSettingDefiningcode(SettingDefiningcode.EMERGENCY_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningcode(CategoryDefiningcode.EVENT);

        // check if the condition has a recorded date, if not, use the onset
        DateTimeType aDate = that.getRecordedDateElement();
        logger.debug("recorded is {}", aDate);
        if (aDate.isEmpty()) {
            logger.debug("recorded date is null trying onset");
            aDate = that.getOnsetDateTimeType();
            logger.debug("onset is {}", aDate);
        }
        result.setStartTimeValue(aDate.getValueAsCalendar().toZonedDateTime());

        // https://github.com/ehrbase/ehrbase_client_library/issues/31
        PartyIdentified composer = new PartyIdentified();
        DvIdentifier identifier = new DvIdentifier();
        identifier.setId(that.getRecorder().getReference());
        composer.addIdentifier(identifier);
        result.setComposer(composer);
        return result;
    }
}
