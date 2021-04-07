package org.ehrbase.fhirbridge.ehr.converter.specific.procedure;

import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.ProcedureToProcedureActionConverter;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition.CurrentStateDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition.DetailsZurKoerperstelleCluster;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition.ProzedurAction;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Procedure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProzedurActionConverter extends ProcedureToProcedureActionConverter<ProzedurAction> {
    @Override
    protected ProzedurAction convertInternal(Procedure resource) {
        ProzedurAction action = new ProzedurAction();
        action.setNameDerProzedurValue(resource.getCode().getCoding().get(0).getDisplay());
        setBodysite(action, resource);
        setNote(action, resource);
        action.setCurrentStateDefiningCode(CurrentStateDefiningCode.COMPLETED);
        return action;

    }

    private void setNote(ProzedurAction action, Procedure resource) {
        Optional<Annotation> note = Optional.empty();
        List<Annotation> notes = resource.getNote();
        if (!notes.isEmpty()) {
            note = Optional.of(resource.getNote().get(0)); // could be empty
        }
        if (note.isEmpty()) {
            action.setFreitextbeschreibungValue(note.get().getText());
        }
    }

    public void setBodysite(ProzedurAction action, Procedure resource){
        List<CodeableConcept> bodySites = resource.getBodySite();
        Optional<Coding> bodySite = Optional.empty();
        if (!bodySites.isEmpty()) {
            CodeableConcept bodySiteCodes = bodySites.get(0); // could be empty
            if (bodySiteCodes != null) {
                bodySite = Optional.of(bodySiteCodes.getCoding().get(0));
            }
        }
        if (bodySite.isPresent()) {
            DetailsZurKoerperstelleCluster anatomicalLocationCluster = new DetailsZurKoerperstelleCluster();
            anatomicalLocationCluster.setNameDerKoerperstelleValue(bodySite.get().getDisplay());
            action.setDetailsZurKoerperstelle(new ArrayList<>());
            action.getDetailsZurKoerperstelle().add(anatomicalLocationCluster);
        }
    }

}
