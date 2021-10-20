package org.ehrbase.fhirbridge.ehr.converter.specific.impfstatus;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
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

    private final DvCodedTextParser dvCodedTextParser = DvCodedTextParser.getInstance();

    private Immunization.ImmunizationProtocolAppliedComponent immunizationProtocolAppliedComponent;
    private Boolean hasImmunizationProtocolApplied = false;

    ImpfungActionConverter(Immunization.ImmunizationProtocolAppliedComponent immunizationProtocolAppliedComponent) {
        hasImmunizationProtocolApplied = true;
        this.immunizationProtocolAppliedComponent = immunizationProtocolAppliedComponent;
    }

    ImpfungActionConverter() {
    }

    @Override
    protected ImpfungAction convertInternal(Immunization immunization) {
        ImpfungAction impfungAction = new ImpfungAction();
        dvCodedTextParser.parseFHIRCoding(immunization.getVaccineCode().getCoding().get(0))
                .ifPresent(impfungAction::setImpfstoff);
        impfungAction.setCurrentStateDefiningCode(mapCurentState(immunization));
        if (hasImmunizationProtocolApplied) {
            mapImpfungGegenAndDosis(impfungAction);
        }
        return impfungAction;
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
        List<ImpfungImpfungGegenElement> impfungImpfungGegenElements = new ArrayList<>();
        for (CodeableConcept targetDisease : immunizationProtocolAppliedComponent.getTargetDisease()) {
            ImpfungImpfungGegenElement impfungImpfungGegenElement = new ImpfungImpfungGegenElement();
            dvCodedTextParser.parseFHIRCoding(targetDisease.getCoding().get(0))
                    .ifPresent(impfungImpfungGegenElement::setValue);
            impfungImpfungGegenElements.add(impfungImpfungGegenElement);
        }
        return impfungImpfungGegenElements;
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

