package org.ehrbase.fhirbridge.ehr.converter.specific.geccovirologischerbefund;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.LabortestBezeichnungDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.LabortestPanelCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.NachweisDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.VirusnachweistestDefiningCode;
import org.hl7.fhir.r4.model.Observation;

@SuppressWarnings("java:S6212")
public class PCRObservationConverter extends ObservationToObservationConverter<BefundObservation> {

    @Override
    protected BefundObservation convertInternal(Observation resource) {
        BefundObservation befundObservation = new BefundObservation();
        befundObservation.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.DETECTION_OF_VIRUS_PROCEDURE);
        befundObservation.setLabortestPanel(createLabortestPanel(resource));
        return befundObservation;
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
                throw new ConversionException("Value code " + observation.getValueCodeableConcept().getCoding().get(0).getCode() + " is not supported");
        }
        analyt.setErgebnisStatusValue(observation.getStatus().toCode());
        labortestPanel.setProAnalyt(analyt);
        return labortestPanel;
    }
}
