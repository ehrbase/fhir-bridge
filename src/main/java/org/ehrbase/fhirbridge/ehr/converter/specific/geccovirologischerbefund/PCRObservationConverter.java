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
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S6212")
public class PCRObservationConverter extends ObservationToObservationConverter<BefundObservation> {

    @Override
    protected BefundObservation convertInternal(Observation resource) {
        BefundObservation befundObservation = new BefundObservation();
        befundObservation.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.DETECTION_OF_VIRUS_PROCEDURE);
        befundObservation.setLabortestPanel(createLabortestPanel(resource));

        if (resource.hasIdentifier()) {
            befundObservation.setFeederAudit(createIdentifierSection(resource));
        }
        return befundObservation;
    }

    private FeederAudit createIdentifierSection(Observation observation) {
        FeederAudit feederAudit = new FeederAudit();
        List<DvIdentifier> identifierList = new ArrayList<>();
        DvIdentifier e = new DvIdentifier();
        Identifier identifier = observation.getIdentifierFirstRep();
        Reference assigner = identifier.getAssigner();
        if (assigner.hasReference()) {
            e.setAssigner(assigner.getReference());
        } else {
            e.setAssigner(assigner.getIdentifier().getValue());
        }
        e.setType(identifier.getType().toString().split("@")[0]);
        e.setId(identifier.getValue());
        identifierList.add(e);
        feederAudit.setOriginatingSystemItemIds(identifierList);
        com.nedap.archie.rm.archetyped.FeederAuditDetails originatingSystemAudit = new com.nedap.archie.rm.archetyped.FeederAuditDetails();
        originatingSystemAudit.setSystemId(identifier.getSystem());
        feederAudit.setOriginatingSystemAudit(originatingSystemAudit);
        return feederAudit;
    }

    private LabortestPanelCluster createLabortestPanel(Observation observation) {
        LabortestPanelCluster labortestPanel = new LabortestPanelCluster();
        ProAnalytCluster analyt = new ProAnalytCluster();
        analyt.setVirusnachweistestDefiningCode(VirusnachweistestDefiningCode.SARS_COV2_COVID19_RNA_PRESENCE_IN_RESPIRATORY_SPECIMEN_BY_NAA_WITH_PROBE_DETECTION);
        switch (observation.getValueCodeableConcept().getCoding().get(0).getCode()) {
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
