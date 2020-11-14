package org.ehrbase.fhirbridge.mapping;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.mapper.CommonData;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.ProzedurComposition;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition.DetailsZurKorperstelleCluster;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition.ProzedurAction;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.MarkdownType;
import org.hl7.fhir.r4.model.Procedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * FHIR 2 openEHR - Procedure
 */
public class FhirProcedureOpenehrProcedure {

    private static final Logger logger = LoggerFactory.getLogger(FhirProcedureOpenehrProcedure.class);

    private FhirProcedureOpenehrProcedure() {
    }

    public static ProzedurComposition map(Procedure fhirProcedure) {

        ProzedurComposition composition = new ProzedurComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(fhirProcedure);
        composition.setFeederAudit(fa);


        Coding code = fhirProcedure.getCode().getCoding().get(0);

        DateTimeType performed = fhirProcedure.getPerformedDateTimeType();

        Coding bodySite = null;
        List<CodeableConcept> bodySites = fhirProcedure.getBodySite();
        if (bodySites.size() > 0) {
            CodeableConcept bodySiteCodes = bodySites.get(0); // could be empty
            if (bodySiteCodes != null) {
                bodySite = bodySiteCodes.getCoding().get(0);
            }
        }


        Annotation note = null;
        List<Annotation> notes = fhirProcedure.getNote();
        if (notes.size() > 0) {
            note = fhirProcedure.getNote().get(0); // could be empty
        }


        ProzedurAction action = new ProzedurAction();

        action.setTimeValue(performed.getValueAsCalendar().toZonedDateTime());

        action.setNameDerProzedurValue(code.getDisplay());


        if (note != null) {
            action.setFreitextbeschreibungValue(note.getText());
        }


        // anatomical location
        if (bodySite != null) {
            DetailsZurKorperstelleCluster anatomicalLocationCluster = new DetailsZurKorperstelleCluster();

            // mapping
            anatomicalLocationCluster.setNameDerKorperstelleValue(bodySite.getDisplay());

            action.setDetailsZurKorperstelle(new ArrayList<>());
            action.getDetailsZurKorperstelle().add(anatomicalLocationCluster);
        }


        // mandatory ism_transition
        action.setProzedurBeendetDefiningcode(org.ehrbase.fhirbridge.ehr.opt.shareddefinition.ProzedurBeendetDefiningcode.COMPLETED);
        action.setProzedurBeendetDefiningcodeCareflowStep(org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition.ProzedurBeendetDefiningcode.PROZEDUR_BEENDET);

        // mandatory subject
        action.setSubject(new PartySelf());

        // mandatory langauge
        action.setLanguage(Language.DE);


        composition.setProzedur(action);


        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.EMERGENCY_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);

        composition.setStartTimeValue(performed.getValueAsCalendar().toZonedDateTime());

        // https://github.com/ehrbase/ehrbase_client_library/issues/31
        PartyIdentified composer = new PartyIdentified();
        DvIdentifier identifier = new DvIdentifier();
        identifier.setId(fhirProcedure.getRecorder().getReference()); // TODO: if there is no recorder, try with the performer
        composer.addIdentifier(identifier);
        composition.setComposer(composer);

        return composition;
    }

    public static Procedure map(String uid, DvText procedureName, DvText procedureDescription, DvDateTime time, DvText bodyLocation) {
        Procedure procedure = new Procedure();

        // name
        // FIXME: we would need a coded text to get the code not just the string value
        procedure.getCode().setText(procedureName.getValue());

        // description
        // description is optional
        if (procedureDescription != null && procedureDescription.getValue() != null) {
            procedure.addNote(new Annotation(new MarkdownType(procedureDescription.getValue())));
        }

        // time
        procedure.setPerformed(new DateTimeType(Date.from(((OffsetDateTime) time.getValue()).toInstant())));

        // body site
        // FIXME: we would need a coded text to get the code not just the string value
        if (bodyLocation != null) {
            CodeableConcept bodySiteContainer = new CodeableConcept();
            bodySiteContainer.addCoding(new Coding(null, null, bodyLocation.getValue()));
            procedure.addBodySite(bodySiteContainer);
        }

        // id
        procedure.setId(uid);

        return procedure;
    }

    /*
    public static Condition map(DiagnoseComposition compo)
    {
        Condition condition = new Condition();

        TemporalAccessor temporal;
        String text;
        Coding coding;

        // mapping back to FHIR

        // the severity code stored in openEHR is the atcode of the constraint, is not the SNOMED code
        // this is because the OPT was designed this way and the generated code from the client lib
        // generates a ENUM with those codes.

        // severity code
        text = ((AtiopathogeneseSchweregradDvcodedtext)compo.getDiagnose().getSchweregrad()).getSchweregradDefiningcode().getCode();

        // transforms atcodes in snomed codes
        switch (text)
        {
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
        }

        coding = condition.getSeverity().addCoding();
        coding.setCode(text);
        coding.setSystem("http://snomed.info/sct");

        // diagnose code
        text = compo.getDiagnose().getDerDiagnoseDefiningcode().getCode();
        coding = condition.getCode().addCoding();
        coding.setCode(text);
        coding.setSystem("http://fhir.de/CodeSystem/dimdi/icd-10-gm");

        // date onset
        temporal = compo.getDiagnose().getDerErstdiagnoseValue();
        condition.getOnsetDateTimeType().setValue(Date.from(((OffsetDateTime)temporal).toInstant()));

        // body site
        text = compo.getDiagnose().getKorperstelleValueStructure();
        condition.addBodySite().addCoding().setDisplay(text);

        // FIXME: all FHIR resources need an ID, currently we are using the compo.uid as the resource ID,
        // this is a workaround, might not work on all cases.
        condition.setId(compo.getVersionUid().toString());

        return condition;
    }

     */
}