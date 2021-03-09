package org.ehrbase.fhirbridge.ehr.converter.bloodgas;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class BlutgasAnalyseConverter {

    public BlutgasAnalyseConverter() {
    }

    private StatusDefiningCode mapStatus(Observation fhirObservation) {
        switch (fhirObservation.getStatusElement().getCode()) {
            case "registered":
                return StatusDefiningCode.REGISTRIERT;
            case "final":
                return StatusDefiningCode.FINAL;
            case "amended":
                return StatusDefiningCode.GEAENDERT;
            case "preliminary":
                return StatusDefiningCode.VORLAEUFIG;
            default:
                throw new IllegalStateException("Invalid Code " + fhirObservation.getStatusElement().getCode() + "" +
                        " for mapping of 'status', valid codes are: registered, final, amended and preliminary");
        }
    }

    private String mapKategorie(Observation fhirObservation) {
        Optional<String> categoryCode;
        for (CodeableConcept codingEntry : fhirObservation.getCategory()) {
            categoryCode = getObservationCategory(codingEntry);
            if (categoryCode.isPresent()) {
                return categoryCode.get();
            }
        }
        throw new IllegalArgumentException("Category code is not defined in blood gas panel, therefore the bundle is incomplete. Please add category observation category to the panel");

    }

    private Optional<String> getObservationCategory(CodeableConcept codings) {
        for (Coding categoryEntry : codings.getCoding()) {
            if (isObservationCategory(categoryEntry)) {
                return Optional.of(categoryEntry.getCode());
            }
        }
        return Optional.empty();
    }

    private boolean isObservationCategory(Coding categories) {
        return categories.getSystem().equals("http://terminology.hl7.org/CodeSystem/observation-category");
    }


    private void setMandatoryFields(BefundDerBlutgasanalyseComposition befundDerBlutgasanalyseComposition) {
        befundDerBlutgasanalyseComposition.setLanguage(Language.DE);
        befundDerBlutgasanalyseComposition.setLocation("test");
        befundDerBlutgasanalyseComposition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        befundDerBlutgasanalyseComposition.setTerritory(Territory.DE);
        befundDerBlutgasanalyseComposition.setCategoryDefiningCode(Category.EVENT);
        befundDerBlutgasanalyseComposition.setComposer(new PartySelf());
    }

    public BefundDerBlutgasanalyseComposition convert(BloodGasPanel bloodGasPanelBundle, FeederAudit feederAudit) {
        Observation bloodGasPanel = bloodGasPanelBundle.getBloodGasPanel();
        BefundDerBlutgasanalyseComposition befundDerBlutgasanalyseComposition = new BefundDerBlutgasanalyseComposition();
        befundDerBlutgasanalyseComposition.setFeederAudit(feederAudit);
        setMandatoryFields(befundDerBlutgasanalyseComposition);

        befundDerBlutgasanalyseComposition.setStatusDefiningCode(mapStatus(bloodGasPanel));
        befundDerBlutgasanalyseComposition.setKategorieValue(mapKategorie(bloodGasPanel));
        befundDerBlutgasanalyseComposition.setStartTimeValue(bloodGasPanel.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        befundDerBlutgasanalyseComposition.setLaborergebnis(LaborergebnisBefundConverter.map(bloodGasPanelBundle));
        return befundDerBlutgasanalyseComposition;
    }
}
