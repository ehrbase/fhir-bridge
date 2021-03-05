package org.ehrbase.fhirbridge.ehr.converter.antibodypanel;

import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.*;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;
import java.util.Arrays;
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
        for (Optional<Observation> optionalObservation : antiBodyPanel.getAllNonPanel()) {
            optionalObservation.ifPresent(observation -> befundJedesEreignisChoices.add(mapLabortestCluster(observation)));
        }
        return befundJedesEreignisChoices;
    }

    private BefundJedesEreignisPointEvent mapLabortestCluster(Observation observation){
        BefundJedesEreignisPointEvent befundJedesEreignisPointEvent = new BefundJedesEreignisPointEvent();
        befundJedesEreignisPointEvent.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.SEROLOGIC_TEST_PROCEDURE);
        befundJedesEreignisPointEvent.setLabortestPanel(mapLabortestPanel(observation));

        return befundJedesEreignisPointEvent;
    }

    private LabortestPanelCluster mapLabortestPanel(Observation observation) {
        LabortestPanelCluster labortestPanelCluster = new LabortestPanelCluster();


        return labortestPanelCluster;
    }

}
