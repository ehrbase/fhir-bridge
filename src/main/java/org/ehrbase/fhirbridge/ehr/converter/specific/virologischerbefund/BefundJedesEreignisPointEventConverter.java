package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.BefundJedesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.LabortestPanelCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProbeCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.AnatomischeLokalisationCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.NameDerKoerperstelleDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.LabortestBezeichnungDefiningCode;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Specimen;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class BefundJedesEreignisPointEventConverter extends ObservationToPointEventConverter<BefundJedesEreignisPointEvent> {

    @Override
    protected BefundJedesEreignisPointEvent convertInternal(Observation observation) throws FHIRException {

        Specimen specimen = observation.getSpecimenTarget();

        BefundJedesEreignisPointEvent befundevent = new BefundJedesEreignisPointEvent();

        mapLabortestbezeichnung(observation, befundevent);
        mapProbe(befundevent, specimen);

        List<ProAnalytCluster> proAnalytClusterlist = new ArrayList<>();
        LabortestPanelCluster labortestPanelCluster = new LabortestPanelCluster();

        proAnalytClusterlist.add(new ProAnalytClusterConverter().convert(observation));
        labortestPanelCluster.setProAnalyt(proAnalytClusterlist);
        befundevent.setLabortestPanel(labortestPanelCluster);

        return befundevent;

    }

    private void mapLabortestbezeichnung(Observation observation, BefundJedesEreignisPointEvent befundevent) {

        if(observation.getCategory().get(0).getCoding().get(2).getCode().equals("122442008")) {
            befundevent.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.DETECTION_OF_VIRUS_PROCEDURE);
        }else{
            throw new ConversionException("createLabortestBezeichnungDefiningCode failed as snomedct-subcategory Code (3rd entry in Observation-Category-Coding) was not 122442008.");
        }
    }

    private void mapProbe(BefundJedesEreignisPointEvent befundevent, Specimen specimen) throws FHIRException {

        ProbeCluster probecluster = new ProbeCluster();

        if (!specimen.hasCollection()){
            throw new ConversionException("Specimen resource needs to have a Collection.");
        }
        mapZeitpunktDerProbenentnahme(probecluster, specimen);
        mapNameDerKoerperstelle(probecluster, specimen);

        befundevent.setProbe(probecluster);
    }

    private void mapZeitpunktDerProbenentnahme(ProbeCluster probecluster, Specimen specimen) throws FHIRException {

        if (specimen.getCollection().hasCollectedPeriod()){
            if (specimen.getCollection().getCollectedPeriod().hasStart() && specimen.getCollection().getCollectedPeriod().hasEnd()) {
                Date start = specimen.getCollection().getCollectedPeriod().getStart();
                Date end = specimen.getCollection().getCollectedPeriod().getEnd();
                probecluster.setZeitpunktDesProbeneingangsValue((new DateTimeType(start)).getValueAsCalendar().toZonedDateTime());
                probecluster.setZeitpunktDesProbeneingangsValue((new DateTimeType(end)).getValueAsCalendar().toZonedDateTime());
            }
        } else if (specimen.getCollection().hasCollectedDateTimeType()) {
            DateTimeType date = specimen.getCollection().getCollectedDateTimeType();
            probecluster.setZeitpunktDerProbenentnahmeValue(date.getValueAsCalendar().toZonedDateTime());
        } else{
            throw new ConversionException("Collection of Specimen resource needs to either have CollectedDateTimeType or CollectedPeriod.");
        }
    }

    private void mapNameDerKoerperstelle(ProbeCluster probecluster, Specimen specimen) throws FHIRException {

        AnatomischeLokalisationCluster anatomischeLokalisationCluster = new AnatomischeLokalisationCluster();
        if (!specimen.getCollection().getBodySite().hasCoding() || specimen.getCollection().getBodySite().getCoding().size() != 1){
            throw new ConversionException("Body Site of Specimen resource needs to have exactly one instance of Coding.");
        }
        switch (specimen.getCollection().getBodySite().getCoding().get(0).getCode()) {
            case "367592002":
                anatomischeLokalisationCluster.setNameDerKoerperstelleDefiningCode(NameDerKoerperstelleDefiningCode.STRUCTURE_OF_POSTERIOR_NASOPHARYNX_BODY_STRUCTURE);
                break;
            case "12999009":
                anatomischeLokalisationCluster.setNameDerKoerperstelleDefiningCode(NameDerKoerperstelleDefiningCode.STRUCTURE_OF_POSTERIOR_WALL_OF_OROPHARYNX_BODY_STRUCTURE);
                break;
            case "700016008":
                anatomischeLokalisationCluster.setNameDerKoerperstelleDefiningCode(NameDerKoerperstelleDefiningCode.STRUCTURE_OF_INTERNAL_PART_OF_MOUTH_BODY_STRUCTURE);
                break;
            case "44567001":
                anatomischeLokalisationCluster.setNameDerKoerperstelleDefiningCode(NameDerKoerperstelleDefiningCode.TRACHEAL_STRUCTURE_BODY_STRUCTURE);
                break;
            case "82094008":
                anatomischeLokalisationCluster.setNameDerKoerperstelleDefiningCode(NameDerKoerperstelleDefiningCode.LOWER_RESPIRATORY_TRACT_STRUCTURE_BODY_STRUCTURE);
                break;
            case "91724006":
                anatomischeLokalisationCluster.setNameDerKoerperstelleDefiningCode(NameDerKoerperstelleDefiningCode.TRACHEOBRONCHIAL_STRUCTURE_BODY_STRUCTURE);
                break;
            case "955009":
                anatomischeLokalisationCluster.setNameDerKoerperstelleDefiningCode(NameDerKoerperstelleDefiningCode.BRONCHIAL_STRUCTURE_BODY_STRUCTURE);
                break;
            case "113253006":
                anatomischeLokalisationCluster.setNameDerKoerperstelleDefiningCode(NameDerKoerperstelleDefiningCode.PULMONARY_ALVEOLAR_STRUCTURE_BODY_STRUCTURE);
                break;
            default:
                throw new ConversionException("createNameDerKoerperstelleDefiningCode failed. Code not found for: " + specimen.getCollection().getBodySite().getCoding().get(0).getCode());

        }
        probecluster.setAnatomischeLokalisation(anatomischeLokalisationCluster);
    }

}


