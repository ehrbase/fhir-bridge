package org.ehrbase.fhirbridge.ehr.converter.antibodypanel;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.bloodgas.BloodGasPanel;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.StatusDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.GECCOSerologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class AntiBodyPanelCompositionConverter implements CompositionConverter<GECCOSerologischerBefundComposition, Observation> {
    @Override
    public Observation fromComposition(GECCOSerologischerBefundComposition composition) throws CompositionConversionException {
        return null;
    }

    @Override
    public GECCOSerologischerBefundComposition toComposition(Observation observation) throws CompositionConversionException {
        GECCOSerologischerBefundComposition geccoSerologischerBefundComposition = new GECCOSerologischerBefundComposition();
        AntiBodyPanel antiBodyPanel = new AntiBodyPanel(observation);
        return geccoSerologischerBefundComposition;
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
            KategorieDefiningCode
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


    private void setMandatoryFields(GECCOSerologischerBefundComposition geccoSerologischerBefundComposition) {
        geccoSerologischerBefundComposition.setLanguage(Language.DE);
        geccoSerologischerBefundComposition.setLocation("test");
        geccoSerologischerBefundComposition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        geccoSerologischerBefundComposition.setTerritory(Territory.DE);
        geccoSerologischerBefundComposition.setCategoryDefiningCode(Category.EVENT);
        geccoSerologischerBefundComposition.setComposer(new PartySelf());
    }

    public GECCOSerologischerBefundComposition convert(AntiBodyPanel antiBodyPanelBundle) {
        Observation antiBodyPanel = antiBodyPanelBundle.getAntiBodyPanel();
        GECCOSerologischerBefundComposition geccoSerologischerBefundComposition = new GECCOSerologischerBefundComposition();
        setMandatoryFields(geccoSerologischerBefundComposition);
        geccoSerologischerBefundComposition.setStatusDefiningCode(mapStatus(antiBodyPanel));
        geccoSerologischerBefundComposition.setKategorieDefiningCode(mapKategorie(antiBodyPanel));
        geccoSerologischerBefundComposition.setStartTimeValue(antiBodyPanel.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        return geccoSerologischerBefundComposition;
    }
}
