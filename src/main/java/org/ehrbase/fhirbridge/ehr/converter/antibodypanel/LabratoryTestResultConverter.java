package org.ehrbase.fhirbridge.ehr.converter.antibodypanel;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.convertercodes.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.*;
import org.hl7.fhir.r4.model.Coding;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LabratoryTestResultConverter {
    public BefundObservation convert(AntiBodyPanel antiBodyPanel) {
        BefundObservation befundObservation = new BefundObservation();
        befundObservation.setJedesEreignis(mapBefunde(antiBodyPanel));
        return befundObservation;
    }

    private List<BefundJedesEreignisChoice> mapBefunde(AntiBodyPanel antiBodyPanel) {
        List<BefundJedesEreignisChoice> befundJedesEreignisChoices = new ArrayList<>();
        for (Optional<Immunoassay> optionalObservation : antiBodyPanel.getAllNonPanel()) {
            optionalObservation.ifPresent(immunoassay -> befundJedesEreignisChoices.add(mapLabortestCluster(immunoassay)));
        }
        return befundJedesEreignisChoices;
    }

    private BefundJedesEreignisPointEvent mapLabortestCluster(Immunoassay immunoassay) {
        BefundJedesEreignisPointEvent befundJedesEreignisPointEvent = new BefundJedesEreignisPointEvent();
        befundJedesEreignisPointEvent.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.SEROLOGIC_TEST_PROCEDURE);
        befundJedesEreignisPointEvent.setLabortestPanel(mapLabortestPanel(immunoassay));
        return befundJedesEreignisPointEvent;
    }

    private LabortestPanelCluster mapLabortestPanel(Immunoassay immunoassay) {
        LabortestPanelCluster labortestPanelCluster = new LabortestPanelCluster();
        ProAnalytCluster proAnalytCluster = new ProAnalytCluster();
        proAnalytCluster.setVirusnachweistestDefiningCode(convertVirusNachweisTest(immunoassay));
        proAnalytCluster.setNachweisDefiningCode(convertNachweisDefiningCode(immunoassay.getObservation().getValueCodeableConcept().getCoding()));

     //   proAnalytCluster.setQuantitativesErgebnisNullFlavourDefiningCode();


        proAnalytCluster.setErgebnisStatusValue(immunoassay.getObservation().getStatusElement().getCode()); //TODO check if status is validated by hapi
        labortestPanelCluster.setProAnalyt(proAnalytCluster);
        return labortestPanelCluster;
    }

    private NachweisDefiningCode convertNachweisDefiningCode(List<Coding> codingList) {
        Coding coding = codingList.get(0);
        if (coding.getCode().equals(NachweisDefiningCode.DETECTED_QUALIFIER_VALUE.getCode()) && coding.getSystem().equals(CodeSystem.SNOMED.getUrl())){
            return NachweisDefiningCode.DETECTED_QUALIFIER_VALUE;
        } else if (coding.getCode().equals(NachweisDefiningCode.INCONCLUSIVE_QUALIFIER_VALUE.getCode()) && coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
            return NachweisDefiningCode.INCONCLUSIVE_QUALIFIER_VALUE;
        } else if (coding.getCode().equals(NachweisDefiningCode.NOT_DETECTED_QUALIFIER_VALUE.getCode()) && coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
            return NachweisDefiningCode.NOT_DETECTED_QUALIFIER_VALUE;
        } else {
            throw new UnprocessableEntityException("The code in valueCodeableConcept.coding.code is not supported ");
        }
    }

    private VirusnachweistestDefiningCode convertVirusNachweisTest(Immunoassay immunoassay) {
        if (immunoassay.getObservation().getCode().getCoding().get(0).getCode().equals(immunoassay.getVirusnachweistestDefiningCode().getCode()) &&
                immunoassay.getObservation().getCode().getCoding().get(0).getSystem().equals(CodeSystem.LOINC.getUrl())) {
            return immunoassay.getVirusnachweistestDefiningCode();
        } else {
            throw new UnprocessableEntityException("The Loinc code in code.coding is not supported in this profile");
        }
    }

}
