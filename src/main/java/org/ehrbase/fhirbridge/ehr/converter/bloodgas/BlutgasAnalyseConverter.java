package org.ehrbase.fhirbridge.ehr.converter.bloodgas;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.StatusDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import java.util.List;
import java.util.Optional;

public class BlutgasAnalyseConverter {

    public BlutgasAnalyseConverter() {
    }

    private StatusDefiningcode mapStatus(Observation fhirObservation) {
        switch (fhirObservation.getStatusElement().getCode()) {
            case "registered":
                return StatusDefiningcode.REGISTRIERT;
            case "final":
                return StatusDefiningcode.FINAL;
            case "amended":
                return StatusDefiningcode.GEANDERT;
            case "preliminary":
                return StatusDefiningcode.VORLAUFIG;
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


/*    private void checkHasMemberResources(BloodGasPanel bloodGasPanelBundle) {
        List<Reference> members = bloodGasPanelBundle.getBloodGasPanel().getHasMember();
        checkReferenceContained(bloodGasPanelBundle.getCarbonDioxidePartialPressure(), members);
        checkReferenceContained(bloodGasPanelBundle.getOxygenPartialPressure(), members);
        checkReferenceContained(bloodGasPanelBundle.getOxygenSaturation(), members);
        checkReferenceContained(bloodGasPanelBundle.getpH(), members);
        allMembersContained(members);
    }*/

    private void checkReferenceContained(Optional<Observation> fhirObservation, List<Reference> members) {
        if (fhirObservation.isPresent()) {
            String extractedReferenceId = fhirObservation.get().getId().substring(fhirObservation.get().getId().indexOf("Observation"));
            isReferenceContained(members, extractedReferenceId);
        }
    }

    private void isReferenceContained(List<Reference> members, String extractedReferenceId) {
        for (Reference reference : members) {
            if (reference.getReference().equals(extractedReferenceId)) {
                members.remove(reference);
                return;
            }
        }
        throw new UnprocessableEntityException("BloodgasPanel references a set of Fhir resources as members, that need to be contained in this bundle. Nevertheless the id " + extractedReferenceId + " is missing.");
    }

    private void allMembersContained(List<Reference> members){
        if (members.size() > 0) {
            throw new UnprocessableEntityException("BloodgasPanel contains references to a resource/s that is not contained within this bundle, please check the hasMembers within the blood gas panel resource to match the amount and value of the resources contained in the bundle.");
        }
    }

    private void setMandatoryFields(BefundDerBlutgasanalyseComposition befundDerBlutgasanalyseComposition) {
        befundDerBlutgasanalyseComposition.setLanguage(Language.DE);
        befundDerBlutgasanalyseComposition.setLocation("test");
        befundDerBlutgasanalyseComposition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        befundDerBlutgasanalyseComposition.setTerritory(Territory.DE);
        befundDerBlutgasanalyseComposition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        befundDerBlutgasanalyseComposition.setComposer(new PartySelf());
    }

  /*  public BefundDerBlutgasanalyseComposition convert(BloodGasPanel bloodGasPanelBundle) {
        Observation bloodGasPanel = bloodGasPanelBundle.getBloodGasPanel();
        BefundDerBlutgasanalyseComposition befundDerBlutgasanalyseComposition = new BefundDerBlutgasanalyseComposition();
        setMandatoryFields(befundDerBlutgasanalyseComposition);

        befundDerBlutgasanalyseComposition.setStatusDefiningcode(mapStatus(bloodGasPanel));
        befundDerBlutgasanalyseComposition.setKategorieValue(mapKategorie(bloodGasPanel));
        befundDerBlutgasanalyseComposition.setStartTimeValue(bloodGasPanel.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        befundDerBlutgasanalyseComposition.setLaborergebnis(LaborergebnisBefundConverter.map(bloodGasPanelBundle));
        checkHasMemberResources(bloodGasPanelBundle);
        return befundDerBlutgasanalyseComposition;
    }*/
}
