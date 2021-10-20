package org.ehrbase.fhirbridge.ehr.converter.specific.geccovirologischerbefund;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.LabortestBezeichnungDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.LabortestPanelCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.VirusnachweistestDefiningCode;
import org.hl7.fhir.r4.model.Observation;

@SuppressWarnings("java:S6212")
public class PCRObservationConverter extends ObservationToObservationConverter<BefundObservation> {

    @Override
    protected BefundObservation convertInternal(Observation observation) {
        BefundObservation befundObservation = new BefundObservation();
        befundObservation.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.DETECTION_OF_VIRUS_PROCEDURE);
        befundObservation.setLabortestPanel(createLabortestPanel(observation));
        return befundObservation;
    }

    private LabortestPanelCluster createLabortestPanel(Observation observation) {
        LabortestPanelCluster labortestPanelCluster = new LabortestPanelCluster();
        ProAnalytCluster proAnalytCluster = new ProAnalytCluster();
        proAnalytCluster.setVirusnachweistestDefiningCode(VirusnachweistestDefiningCode.SARS_COV2_COVID19_RNA_PRESENCE_IN_RESPIRATORY_SPECIMEN_BY_NAA_WITH_PROBE_DETECTION);
        if (observation.hasValueCodeableConcept()) {
            DvCodedTextParser.getInstance()
                    .parseFHIRCoding(observation.getValueCodeableConcept().getCoding().get(0))
                    .ifPresent(proAnalytCluster::setNachweis);
        } else {
            proAnalytCluster.setNachweisNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        proAnalytCluster.setErgebnisStatusValue(observation.getStatus().toCode());
        labortestPanelCluster.setProAnalyt(proAnalytCluster);
        return labortestPanelCluster;
    }
}
