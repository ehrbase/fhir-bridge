package org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.BefundJedesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.LabortestBezeichnungDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.LabortestPanelCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.ProAnalytQuantitativesErgebnisDvQuantity;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class BefundJedesEreignisPointEventConverter extends ObservationToPointEventConverter<BefundJedesEreignisPointEvent> {

    private final DvCodedTextParser dvCodedTextParser = DvCodedTextParser.getInstance();

    private final Immunoassay immunoassay;

    public BefundJedesEreignisPointEventConverter(Immunoassay immunoassay) {
        this.immunoassay = immunoassay;
    }

    @Override
    protected BefundJedesEreignisPointEvent convertInternal(Observation observation) {
        BefundJedesEreignisPointEvent befundJedesEreignisPointEvent = new BefundJedesEreignisPointEvent();
        befundJedesEreignisPointEvent.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.SEROLOGIC_TEST_PROCEDURE);
        befundJedesEreignisPointEvent.setLabortestPanel(mapLabortestPanel());
        return befundJedesEreignisPointEvent;
    }

    private LabortestPanelCluster mapLabortestPanel() {
        LabortestPanelCluster labortestPanelCluster = new LabortestPanelCluster();
        ProAnalytCluster proAnalytCluster = new ProAnalytCluster();
        dvCodedTextParser.parseFHIRCoding(immunoassay.getObservation().getCode().getCoding().get(0)).ifPresent(proAnalytCluster::setVirusnachweistest);
        if (immunoassay.getObservation().hasValue()) {
            mapValue(proAnalytCluster);
        } else {
            mapNullFlavour(proAnalytCluster);
        }
        proAnalytCluster.setErgebnisStatusValue(immunoassay.getObservation().getStatusElement().getCode());
        labortestPanelCluster.setProAnalyt(proAnalytCluster);
        return labortestPanelCluster;
    }

    private void mapNullFlavour(ProAnalytCluster proAnalytCluster) {
        if (immunoassay.getHasValueQuantity()) {
            proAnalytCluster.setQuantitativesErgebnisNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        } else {
            proAnalytCluster.setNachweisNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
    }

    private void mapValue(ProAnalytCluster proAnalytCluster) {
        if (immunoassay.getObservation().hasValueQuantity()) {
            convertValueQuantity().ifPresent(proAnalytCluster::setQuantitativesErgebnis);
        } else {
            convertNachweisDefiningCode().ifPresent(proAnalytCluster::setNachweis);
        }
    }

    private Optional<ProAnalytQuantitativesErgebnisDvQuantity> convertValueQuantity() {
        if (immunoassay.getObservation().hasValueQuantity()) {
            ProAnalytQuantitativesErgebnisDvQuantity proAnalytQuantitativesErgebnisDvQuantity = new ProAnalytQuantitativesErgebnisDvQuantity();
            proAnalytQuantitativesErgebnisDvQuantity.setQuantitativesErgebnisMagnitude(immunoassay.getObservation().getValueQuantity().getValue().doubleValue());
            proAnalytQuantitativesErgebnisDvQuantity.setQuantitativesErgebnisUnits(immunoassay.getObservation().getValueQuantity().getCode());
            return Optional.of(proAnalytQuantitativesErgebnisDvQuantity);
        } else {
            return Optional.empty();
        }
    }

    private Optional<DvCodedText> convertNachweisDefiningCode() {
        if (immunoassay.getObservation().hasValueCodeableConcept() && immunoassay.getObservation().getValueCodeableConcept().hasCoding() && immunoassay.getObservation().getValueCodeableConcept().getCoding().get(0).hasCode()) {
            Coding coding = immunoassay.getObservation().getValueCodeableConcept().getCoding().get(0);
            return dvCodedTextParser.parseFHIRCoding(coding);
        } else {
            return Optional.empty();
        }
    }

}
