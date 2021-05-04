package org.ehrbase.fhirbridge.ehr.converter.specific.impfstatus;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ImmunizationToActionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.ImpfstoffDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.ImpfungAction;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.ImpfungGegenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.VerabreichteDosenCluster;
import org.hl7.fhir.r4.model.Immunization;

import java.util.List;
import java.util.Optional;

public class ImpfungActionConverter extends ImmunizationToActionConverter<ImpfungAction> {

    Optional<VerabreichteDosenCluster> verabreichteDosenClusterOptional = Optional.empty();

    @Override
    protected ImpfungAction convertInternal(Immunization resource) {
        ImpfungAction impfungAction = new ImpfungAction();
        impfungAction.setImpfstoffDefiningCode(mapImpstoffDefiningCode(resource));
        mapImpfungGegenAndDosis(resource, impfungAction);
        return impfungAction;
    }

    private void mapImpfungGegenAndDosis(Immunization resource, ImpfungAction impfungAction) {
        if (resource.hasProtocolApplied()) {
            determineAndSet(resource, impfungAction);
        } else {
            throw new UnprocessableEntityException("Target Disease code and dose missing");
        }
    }

    private void determineAndSet(Immunization resource, ImpfungAction impfungAction) {
        for (Immunization.ImmunizationProtocolAppliedComponent immunizationProtocolAppliedComponent : resource.getProtocolApplied()) {
            if (immunizationProtocolAppliedComponent.hasTargetDisease()) {
                setImfungGegen(impfungAction, resource);
            } else if (immunizationProtocolAppliedComponent.hasDoseNumber()) {
                setVerabreichteDosis(impfungAction, immunizationProtocolAppliedComponent);
            }
        }
    }

    private void setImfungGegen(ImpfungAction impfungAction, Immunization resource) {
        if (resource.getProtocolApplied().get(0).getTargetDisease().get(0).hasCoding()
                && resource.getProtocolApplied().get(0).getTargetDisease().get(0).getCoding().get(0).hasSystem()
                && resource.getProtocolApplied().get(0).getTargetDisease().get(0).getCoding().get(0).getSystem().equals(CodeSystem.SNOMED.getUrl())) {
            impfungAction.setImpfungGegen(List.of(mapImpfungGegen(resource)));
        } else {
            throw new UnprocessableEntityException("Target Disease code");
        }
    }

    private ImpfungGegenDefiningCode mapImpfungGegen(Immunization resource) {
        String snomedCode = resource.getProtocolApplied().get(0).getTargetDisease().get(0).getCoding().get(0).getCode();
        if (ImpfungGegenDefiningCode.getCodesAsMap().containsKey(snomedCode)) {
            return ImpfungGegenDefiningCode.getCodesAsMap().get(snomedCode);
        } else {
            throw new UnprocessableEntityException("Invalid Snomed Code " + snomedCode + " entered");
        }
    }

    private void setVerabreichteDosis(ImpfungAction impfungAction, Immunization.ImmunizationProtocolAppliedComponent resource) {
        verabreichteDosenClusterOptional = Optional.empty();
        if (resource.hasDoseNumberPositiveIntType() && !resource.getDoseNumberPositiveIntType().hasExtension()) { // if it contains an unknown no mapping shall be done
            setDosisMenge(resource);
        } else if (resource.hasSeriesDosesPositiveIntType() && !resource.getSeriesDosesPositiveIntType().hasExtension()) { // if it contains an unknown no mapping shall be done
            setDosierungsReihenfolge(resource);
        }
        verabreichteDosenClusterOptional.ifPresent(impfungAction::setVerabreichteDosen);
    }

    private void setDosierungsReihenfolge(Immunization.ImmunizationProtocolAppliedComponent resource) {
        VerabreichteDosenCluster verabreichteDosenCluster = getVerabreichteDosenCluster();
        verabreichteDosenCluster.setDosierungsreihenfolgeMagnitude(Long.parseLong(resource.getSeriesDosesPositiveIntType().toString()));
        verabreichteDosenClusterOptional = Optional.of(verabreichteDosenCluster);
    }

    private void setDosisMenge(Immunization.ImmunizationProtocolAppliedComponent resource) {
        VerabreichteDosenCluster verabreichteDosenCluster = getVerabreichteDosenCluster();
        verabreichteDosenCluster.setDosismengeMagnitude(Double.parseDouble(resource.getDoseNumberPositiveIntType().toString()));
        verabreichteDosenClusterOptional = Optional.of(verabreichteDosenCluster);
    }

    private VerabreichteDosenCluster getVerabreichteDosenCluster() {
        if (verabreichteDosenClusterOptional.isEmpty()) {
            return new VerabreichteDosenCluster();
        } else {
            return verabreichteDosenClusterOptional.get();
        }
    }

    private ImpfstoffDefiningCode mapImpstoffDefiningCode(Immunization resource) {
        String code = resource.getVaccineCode().getCoding().get(0).getCode();
        if (ImpfstoffDefiningCode.getCodesAsMap().containsKey(code)) {
            return ImpfstoffDefiningCode.getCodesAsMap().get(code);
        } else {
            throw new UnprocessableEntityException("Invalid Code or vaccineCode" + code + " entered");
        }
    }
}

