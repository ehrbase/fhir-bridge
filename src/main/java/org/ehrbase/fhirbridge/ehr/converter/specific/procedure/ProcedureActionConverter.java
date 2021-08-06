package org.ehrbase.fhirbridge.ehr.converter.specific.procedure;

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

public class ProcedureActionConverter extends ProcedureToProcedureActionConverter<ProzedurAction> {
    @Override
    protected ProzedurAction convertInternal(Procedure resource) {
        ProzedurAction action = new ProzedurAction();
        mapNameDerProzedur(resource).ifPresent(action :: setNameDerProzedurValue);
        mapBodysite(action, resource);
        mapNote(resource).ifPresent(action :: setFreitextbeschreibungValue);
        action.setCurrentStateDefiningCode(CurrentStateDefiningCode.COMPLETED);
        return action;

    }

    private Optional<String> mapNameDerProzedur (Procedure resource) {
        for (Coding loop : resource.getCode().getCoding()){
            if (loop.hasDisplay()){
                return Optional.of(loop.getDisplay());
            }
        }
        return  Optional.empty();
    }

    private Optional<String> mapNote(Procedure resource) {
        for (Annotation loop : resource.getNote()){
            if (!loop.isEmpty()){
                return Optional.of(loop.getText());
            }
        }
        return Optional.empty();
    }

    public void mapBodysite(ProzedurAction action, Procedure resource){
        for (CodeableConcept loop : resource.getBodySite()){
            DetailsZurKoerperstelleCluster anatomicalLocationCluster = new DetailsZurKoerperstelleCluster();
            getBodysiteCoding(loop).ifPresent(anatomicalLocationCluster :: setNameDerKoerperstelleValue);
            action.setDetailsZurKoerperstelle(new ArrayList<>());
            action.getDetailsZurKoerperstelle().add(anatomicalLocationCluster);
        }
    }

    private Optional<String> getBodysiteCoding (CodeableConcept bodysite){
        for (Coding loop : bodysite.getCoding()){
            if (!loop.isEmpty()){
                return Optional.of(loop.getDisplay());
            }
        }
        return Optional.empty();
    }

}
