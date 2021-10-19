package org.ehrbase.fhirbridge.ehr.converter.specific.impfstatus;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ImmunizationToActionConverter;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.CurrentStateDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.ImpfungAction;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.ImpfungImpfungGegenElement;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.VerabreichteDosenCluster;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Immunization;

import java.util.ArrayList;
import java.util.List;

public class ImpfungActionConverter extends ImmunizationToActionConverter<ImpfungAction> {

    private final CodingToDvCodedTextConverter converter = CodingToDvCodedTextConverter.getInstance();

    private Immunization.ImmunizationProtocolAppliedComponent immunizationProtocolAppliedComponent;
    private Boolean hasImmunizationProtocolApplied = false;

    ImpfungActionConverter(Immunization.ImmunizationProtocolAppliedComponent immunizationProtocolAppliedComponent) {
        hasImmunizationProtocolApplied = true;
        this.immunizationProtocolAppliedComponent = immunizationProtocolAppliedComponent;
    }

    ImpfungActionConverter() {
    }

    @Override
    protected ImpfungAction convertInternal(Immunization resource) {
        ImpfungAction action = new ImpfungAction();
        action.setImpfstoff(converter.convert(resource.getVaccineCode().getCoding().get(0)));
        action.setCurrentStateDefiningCode(mapCurentState(resource));
        if (hasImmunizationProtocolApplied) {
            mapImpfungGegenAndDosis(action);
        }
        return action;
    }

    private CurrentStateDefiningCode mapCurentState(Immunization resource) {
        String statusCode = resource.getStatus().toCode();
        if (statusCode.equals(CurrentStateDefiningCode.ABORTED.getValue())) {
            return CurrentStateDefiningCode.ABORTED;
        } else if (statusCode.equals(CurrentStateDefiningCode.COMPLETED.getValue())) {
            return CurrentStateDefiningCode.COMPLETED;
        } else if (statusCode.equals(CurrentStateDefiningCode.ACTIVE.getValue())) {
            return CurrentStateDefiningCode.ACTIVE;
        } else {
            throw new ConversionException("The status code" + statusCode + " is wrong or not supported by the fhir-bridge. Supported are: aborted, completed and active");
        }
    }

    private void mapImpfungGegenAndDosis(ImpfungAction impfungAction) {
        impfungAction.setImpfungGegen(mapImpfungGegen());
        impfungAction.setVerabreichteDosen(convertVerabreichteDosis());
    }

    private List<ImpfungImpfungGegenElement> mapImpfungGegen() {
        List<ImpfungImpfungGegenElement> impfungGegenList = new ArrayList<>();
        for (CodeableConcept targetDisease : immunizationProtocolAppliedComponent.getTargetDisease()) {
            ImpfungImpfungGegenElement element = new ImpfungImpfungGegenElement();
            element.setValue(converter.convert(targetDisease.getCoding().get(0)));
            impfungGegenList.add(element);
        }
        return impfungGegenList;
    }

    private VerabreichteDosenCluster convertVerabreichteDosis() {
        VerabreichteDosenCluster verabreichteDosenCluster = new VerabreichteDosenCluster();
        if (immunizationProtocolAppliedComponent.hasDoseNumber()) {
            convertMenge(verabreichteDosenCluster);
        }
        if (immunizationProtocolAppliedComponent.hasSeriesDoses()) {
            convertDosierungsReihenfolge(verabreichteDosenCluster);
        }
        return verabreichteDosenCluster;
    }

    private void convertDosierungsReihenfolge(VerabreichteDosenCluster verabreichteDosenCluster) {
        if (immunizationProtocolAppliedComponent.hasSeriesDosesPositiveIntType()) {
            verabreichteDosenCluster.setDosierungsreihenfolgeMagnitude(Long.parseLong(immunizationProtocolAppliedComponent.getSeriesDosesPositiveIntType().toString()));
        } else if (immunizationProtocolAppliedComponent.hasSeriesDosesStringType()) {
            throw new UnprocessableEntityException("Currently the fhir bridge does not support inprecise values like strings for the dosage series, please enter an integer.");
        }
    }

    private void convertMenge(VerabreichteDosenCluster verabreichteDosenCluster) {
        if (immunizationProtocolAppliedComponent.hasDoseNumberPositiveIntType()) {
            verabreichteDosenCluster.setDosismengeMagnitude(Double.parseDouble(immunizationProtocolAppliedComponent.getDoseNumberPositiveIntType().toString()));
        } else if (immunizationProtocolAppliedComponent.hasDoseNumberStringType() &&
                immunizationProtocolAppliedComponent.getDoseNumberStringType().hasExtension("http://hl7.org/fhir/StructureDefinition/data-absent-reason")) {
            verabreichteDosenCluster.setDosismengeNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        } else if (immunizationProtocolAppliedComponent.hasDoseNumberStringType()) {
            throw new UnprocessableEntityException("Currently the fhir bridge does not support inprecise values like strings for the dosage amount, please enter an integer.");
        }
    }


}

