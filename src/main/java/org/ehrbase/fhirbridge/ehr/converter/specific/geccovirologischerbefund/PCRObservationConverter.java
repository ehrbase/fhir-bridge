package org.ehrbase.fhirbridge.ehr.converter.specific.geccovirologischerbefund;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.LabortestBezeichnungDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.LabortestPanelCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.NachweisDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.VirusnachweistestDefiningCode;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;
import java.util.List;

public class PCRObservationConverter extends ObservationToObservationConverter<BefundObservation> {

    @Override
    protected BefundObservation convertInternal(Observation resource) {
        BefundObservation befundObservation = new BefundObservation();
        befundObservation.setFeederAudit(createIdentifierSection(resource));
        befundObservation.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.DETECTION_OF_VIRUS_PROCEDURE);
        befundObservation.setLabortestPanel(createLabortestPanel(resource));
        return befundObservation;
    }

    private FeederAudit createIdentifierSection(Observation observation) {
        FeederAudit feederAudit = new FeederAudit();
        List<DvIdentifier> identifierList = new ArrayList<>();
        com.nedap.archie.rm.datavalues.DvIdentifier e =  new com.nedap.archie.rm.datavalues.DvIdentifier();
        e.setAssigner(observation.getIdentifier().get(0).getAssigner().getReference());
        e.setType(observation.getIdentifier().get(0).getType().toString().split("@")[0]);
        e.setId(observation.getIdentifier().get(0).getValue());
        identifierList.add(e);
        feederAudit.setOriginatingSystemItemIds(identifierList);
        com.nedap.archie.rm.archetyped.FeederAuditDetails originatingSystemAudit = new com.nedap.archie.rm.archetyped.FeederAuditDetails();
        originatingSystemAudit.setSystemId(observation.getIdentifier().get(0).getSystem());
        feederAudit.setOriginatingSystemAudit(originatingSystemAudit);
        return feederAudit;
    }

    private LabortestPanelCluster createLabortestPanel(Observation observation) {
        LabortestPanelCluster labortestPanel = new LabortestPanelCluster();
        ProAnalytCluster analyt = new ProAnalytCluster();
        analyt.setVirusnachweistestDefiningCode(VirusnachweistestDefiningCode.SARS_COV2_COVID19_RNA_PRESENCE_IN_RESPIRATORY_SPECIMEN_BY_NAA_WITH_PROBE_DETECTION);
        switch(observation.getValueCodeableConcept().getCoding().get(0).getCode()) {
            case "260373001":
                analyt.setNachweisDefiningCode(NachweisDefiningCode.DETECTED_QUALIFIER_VALUE);
                break;
            case "419984006":
                analyt.setNachweisDefiningCode(NachweisDefiningCode.INCONCLUSIVE_QUALIFIER_VALUE);
                break;
            case "260415000":
                analyt.setNachweisDefiningCode(NachweisDefiningCode.NOT_DETECTED_QUALIFIER_VALUE);
                break;
            default:
                throw new UnprocessableEntityException("Value code " + observation.getValueCodeableConcept().getCoding().get(0).getCode() + " is not supported");
        }
        analyt.setErgebnisStatusValue(observation.getStatus().toCode());
        labortestPanel.setProAnalyt(analyt);
        return labortestPanel;
    }
}
