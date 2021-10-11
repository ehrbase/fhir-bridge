package org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.*;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.openjdk.jmh.util.Optional;

import java.util.List;

public class BefundJedesEreignisPointEventConverter extends ObservationToPointEventConverter<BefundJedesEreignisPointEvent> {
    private final Immunoassay immunoassay;

    public BefundJedesEreignisPointEventConverter(Immunoassay immunoassay) {
        this.immunoassay = immunoassay;
    }

    @Override
    protected BefundJedesEreignisPointEvent convertInternal(Observation resource) {
        BefundJedesEreignisPointEvent befundJedesEreignisPointEvent = new BefundJedesEreignisPointEvent();
        befundJedesEreignisPointEvent.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.SEROLOGIC_TEST_PROCEDURE);
        befundJedesEreignisPointEvent.setLabortestPanel(mapLabortestPanel());
        return befundJedesEreignisPointEvent;
    }

    private LabortestPanelCluster mapLabortestPanel() {
        LabortestPanelCluster labortestPanelCluster = new LabortestPanelCluster();
        ProAnalytCluster proAnalytCluster = new ProAnalytCluster();
        DvCodedTextParser.parseFHIRCoding(immunoassay.getObservation().getCode().getCoding().get(0)).ifPresent(proAnalytCluster::setVirusnachweistest);
        if(immunoassay.getObservation().hasValueQuantity()){
            ProAnalytQuantitativesErgebnisDvQuantity proAnalytQuantitativesErgebnisDvQuantity = new ProAnalytQuantitativesErgebnisDvQuantity();
            proAnalytQuantitativesErgebnisDvQuantity.setQuantitativesErgebnisMagnitude(immunoassay.getObservation().getValueQuantity().getValue().doubleValue());
            proAnalytQuantitativesErgebnisDvQuantity.setQuantitativesErgebnisUnits(immunoassay.getObservation().getValueQuantity().getCode());
            proAnalytCluster.setQuantitativesErgebnis(proAnalytQuantitativesErgebnisDvQuantity);
        }else{
            convertNachweisDefiningCode().ifPresent
            proAnalytCluster.setNachweis();
        }
        proAnalytCluster.setErgebnisStatusValue(immunoassay.getObservation().getStatusElement().getCode());
        labortestPanelCluster.setProAnalyt(proAnalytCluster);
        return labortestPanelCluster;
    }

    private Optional<DvCodedText> convertNachweisDefiningCode() {
        if(immunoassay.getObservation().hasValueCodeableConcept() && immunoassay.getObservation().getValueCodeableConcept().hasCoding() && immunoassay.getObservation().getValueCodeableConcept().getCoding().get(0).hasCode()){
            List<Coding> codingList = immunoassay.getObservation().getValueCodeableConcept().getCoding();
            Coding coding = codingList.get(0);
            return 
        }else{
            throw new ConversionException("ValueCodeableConcept.coding or code is missing");
        }
    }

}
