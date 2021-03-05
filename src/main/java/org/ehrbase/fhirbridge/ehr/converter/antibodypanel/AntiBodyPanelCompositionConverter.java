package org.ehrbase.fhirbridge.ehr.converter.antibodypanel;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.GECCOSerologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.GeccoSerologischerBefundKategorieLoincElement;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.KategorieLoincDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;
import java.util.Optional;

public class AntiBodyPanelCompositionConverter implements CompositionConverter<GECCOSerologischerBefundComposition, Observation> {
    @Override
    public Observation fromComposition(GECCOSerologischerBefundComposition composition) throws CompositionConversionException {
        return null;
    }

    @Override
    public GECCOSerologischerBefundComposition toComposition(Observation observation) throws CompositionConversionException {
        GECCOSerologischerBefundComposition geccoSerologischerBefundComposition = new GECCOSerologischerBefundComposition();
        setMandatoryFields(geccoSerologischerBefundComposition);
        geccoSerologischerBefundComposition.setStatusDefiningCode(mapStatus(observation));
        mapKategorie(observation, geccoSerologischerBefundComposition);
        geccoSerologischerBefundComposition.setStartTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        geccoSerologischerBefundComposition.setBefund(new LabratoryTestResultConverter().convert(new AntiBodyPanel(observation)));
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

    private void mapKategorie(Observation fhirObservation, GECCOSerologischerBefundComposition geccoSerologischerBefundComposition) {
        Optional<Coding> categoryCode;
        for (CodeableConcept codingEntry : fhirObservation.getCategory()) {
            categoryCode = getObservationCategory(codingEntry);
            if (isKategorieDefiningCode(categoryCode)) {
                mapKategorieDefiningCode(geccoSerologischerBefundComposition);
            } else if (isKategorieLoincCode(categoryCode)) {
                mapKategorieLoincCode(geccoSerologischerBefundComposition);
            } else {
                throw new IllegalArgumentException("Category code is not defined in anti body panel, therefore the bundle is incomplete. Please add category observation category to the panel");
            }
        }
    }

    private Optional<Coding> getObservationCategory(CodeableConcept codings) {
        for (Coding categoryEntry : codings.getCoding()) {
            if (isObservationCategory(categoryEntry)) {
                return Optional.of(categoryEntry);
            }
        }
        return Optional.empty();
    }

    private boolean isKategorieLoincCode(Optional<Coding> categoryCode) {
        return categoryCode.isPresent() && isLabratory(categoryCode.get().getCode());
    }

    private boolean isKategorieDefiningCode(Optional<Coding> categoryCode) {
        return categoryCode.isPresent() && isLoincCode(categoryCode.get());
    }

    private void mapKategorieLoincCode(GECCOSerologischerBefundComposition geccoSerologischerBefundComposition) {
        GeccoSerologischerBefundKategorieLoincElement loincElement = new GeccoSerologischerBefundKategorieLoincElement();
        loincElement.setValue(KategorieLoincDefiningCode.LABORATORY_STUDIES_SET);
        geccoSerologischerBefundComposition.setKategorieLoinc(List.of(loincElement));
    }

    private void mapKategorieDefiningCode(GECCOSerologischerBefundComposition geccoSerologischerBefundComposition) {
        geccoSerologischerBefundComposition.setKategorieDefiningCode(KategorieDefiningCode.LABORATORY);
    }


    private boolean isLoincCode(Coding code) {
        return AntiBodyPanelCode.LABRATORY_STUDIES_CODE.getCode().equals(code.getCode()) && AntiBodyPanelCode.LABRATORY_STUDIES_CODE.getSystem().equals(code.getSystem());
    }

    private boolean isLabratory(String code) {
        return KategorieDefiningCode.LABORATORY.getCode().equals(code);
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

}
