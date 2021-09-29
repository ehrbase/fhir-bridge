package org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.BefundJedesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.LabortestBezeichnungDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.LabortestPanelCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.NachweisDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.VirusnachweistestDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

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
        proAnalytCluster.setVirusnachweistestDefiningCode(convertVirusNachweisTest());
        if(immunoassay.getObservation().hasValueQuantity()){
            proAnalytCluster.setQuantitativesErgebnisMagnitude(immunoassay.getObservation().getValueQuantity().getValue().doubleValue());
            proAnalytCluster.setQuantitativesErgebnisUnits(immunoassay.getObservation().getValueQuantity().getCode());
        }else{
            proAnalytCluster.setNachweisDefiningCode(convertNachweisDefiningCode());
        }
        proAnalytCluster.setErgebnisStatusValue(immunoassay.getObservation().getStatusElement().getCode());
        labortestPanelCluster.setProAnalyt(proAnalytCluster);
        return labortestPanelCluster;
    }

    private NachweisDefiningCode convertNachweisDefiningCode() {
        if(immunoassay.getObservation().hasValueCodeableConcept() && immunoassay.getObservation().getValueCodeableConcept().hasCoding() && immunoassay.getObservation().getValueCodeableConcept().getCoding().get(0).hasCode()){
            List<Coding> codingList = immunoassay.getObservation().getValueCodeableConcept().getCoding();
            Coding coding = codingList.get(0);
            return resolveNachweisDefiningCode(coding);
        }else{
            throw new ConversionException("ValueCodeableConcept.coding or code is missing");
        }
    }

    private NachweisDefiningCode resolveNachweisDefiningCode(Coding coding) {
        if (coding.getCode().equals(NachweisDefiningCode.DETECTED_QUALIFIER_VALUE.getCode()) && coding.getSystem().equals(CodeSystem.SNOMED.getUrl())){
            return NachweisDefiningCode.DETECTED_QUALIFIER_VALUE;
        } else if (coding.getCode().equals(NachweisDefiningCode.INCONCLUSIVE_QUALIFIER_VALUE.getCode()) && coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
            return NachweisDefiningCode.INCONCLUSIVE_QUALIFIER_VALUE;
        } else if (coding.getCode().equals(NachweisDefiningCode.NOT_DETECTED_QUALIFIER_VALUE.getCode()) && coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
            return NachweisDefiningCode.NOT_DETECTED_QUALIFIER_VALUE;
        } else {
            throw new ConversionException("The code in valueCodeableConcept.coding.code is not supported");
        }
    }

    private VirusnachweistestDefiningCode convertVirusNachweisTest() {
        if (immunoassay.getObservation().getCode().getCoding().get(0).getCode().equals(immunoassay.getVirusnachweistestDefiningCode().getCode()) &&
                immunoassay.getObservation().getCode().getCoding().get(0).getSystem().equals(CodeSystem.LOINC.getUrl())) {
            return immunoassay.getVirusnachweistestDefiningCode();
        } else {
            throw new ConversionException("The Loinc code in code.coding is not supported in this profile");
        }
    }
}
