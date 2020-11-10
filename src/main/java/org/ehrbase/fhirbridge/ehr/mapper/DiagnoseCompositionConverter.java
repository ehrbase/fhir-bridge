package org.ehrbase.fhirbridge.ehr.mapper;

import org.ehrbase.fhirbridge.ehr.template.diagnosecomposition.DiagnoseComposition;
import org.ehrbase.fhirbridge.ehr.template.diagnosecomposition.definition.AtiopathogeneseSchweregradDvcodedtext;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.Territory;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;

import java.time.OffsetDateTime;
import java.util.Date;

public class DiagnoseCompositionConverter implements CompositionConverter<DiagnoseComposition, Condition> {

    @Override
    public DiagnoseComposition convertTo(Condition condition) {
        if (condition == null) {
            return null;
        }

        DiagnoseComposition result = new DiagnoseComposition();
        result.setLanguage(Language.EN);
        result.setTerritory(Territory.DE);
        result.setSettingDefiningcode(SettingDefiningcode.EMERGENCY_CARE);
        result.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        return result;
    }

    @Override
    public Condition convertFrom(DiagnoseComposition composition) {
        if (composition == null) {
            return null;
        }

        // @formatter:off
        Condition result = new Condition();
        result.setId(composition.getVersionUid().toString());
        result
            .setSeverity(
                new CodeableConcept(
                    new Coding()
                        .setSystem("http://snomed.info/sct")
                        .setCode(getSeverityCode(composition))))
            .setCode(
                new CodeableConcept(
                    new Coding()
                        .setSystem("http://fhir.de/CodeSystem/dimdi/icd-10-gm")
                        .setCode(composition.getDiagnose().getDerDiagnoseDefiningcode().getCode())))
            .setOnset(
                new DateTimeType(
                        Date.from(((OffsetDateTime) composition.getDiagnose().getDerErstdiagnoseValue()).toInstant())))
            .addBodySite(
                new CodeableConcept(
                    new Coding()
                        .setDisplay(composition.getDiagnose().getKorperstelleValueStructure())));
        return result;
        // @formatter:on
    }

    private String getSeverityCode(DiagnoseComposition composition) {
        String code = ((AtiopathogeneseSchweregradDvcodedtext) composition.getDiagnose()
                .getSchweregrad())
                .getSchweregradDefiningcode()
                .getCode();

        switch (code) {
            case "at0049":
                return "24484000";
            case "at0048":
                return "6736007";
            case "at0047":
                return "255604002";
            default:
                throw new IllegalArgumentException();
        }
    }
}
