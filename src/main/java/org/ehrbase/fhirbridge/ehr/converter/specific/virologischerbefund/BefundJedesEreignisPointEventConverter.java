package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvIdentifierParser;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.BefundJedesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.LabortestPanelCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProbeCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.AnatomischeLokalisationCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.LabortestBezeichnungDefiningCode;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Specimen;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BefundJedesEreignisPointEventConverter extends ObservationToPointEventConverter<BefundJedesEreignisPointEvent> {

    @Override
    protected BefundJedesEreignisPointEvent convertInternal(Observation observation) throws FHIRException {

        Specimen specimen = observation.getSpecimenTarget();
        BefundJedesEreignisPointEvent befundevent = new BefundJedesEreignisPointEvent();

        if(checkLabortestbezeichnung(observation)){
            befundevent.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.DETECTION_OF_VIRUS_PROCEDURE);
        }else {
            throw new ConversionException("createLabortestBezeichnungDefiningCode failed as snomedct-subcategory Code was not 122442008.");
        }
        mapProbe(specimen).ifPresent(befundevent::setProbe);

        List<ProAnalytCluster> proAnalytClusterlist = new ArrayList<>();
        LabortestPanelCluster labortestPanelCluster = new LabortestPanelCluster();

        proAnalytClusterlist.add(new ProAnalytClusterConverter().convert(observation));
        labortestPanelCluster.setProAnalyt(proAnalytClusterlist);
        befundevent.setLabortestPanel(labortestPanelCluster);

        return befundevent;

    }

    private boolean checkLabortestbezeichnung(Observation observation) {
        for (CodeableConcept loop1 : observation.getCategory()){
            boolean result = checkLabortestbezeichnungcode(loop1);
            if(result){
                return true;
            }
        }
        return false;
    }

    private boolean checkLabortestbezeichnungcode(CodeableConcept loop1){
        for (Coding loop2 : loop1.getCoding()) {
            if(loop2.getCode().equals("122442008")) {
                return true;
            }
        }
        return false;
    }

    private Optional<ProbeCluster> mapProbe(Specimen specimen) throws FHIRException {
        ProbeCluster probecluster = new ProbeCluster();
        if (specimen.hasCollection()){
            mapZeitpunktDerProbenentnahme(specimen).ifPresent(probecluster::setZeitpunktDerProbenentnahmeValue);
            mapAnatomischeLokalisation(specimen).ifPresent(probecluster::setAnatomischeLokalisation);
            mapAccessionIdentifier(specimen).ifPresent(probecluster::setLaborprobenidentifikator);
            return Optional.of(probecluster);
        } else {
            return Optional.empty();
        }
    }

    private  Optional<AnatomischeLokalisationCluster> mapAnatomischeLokalisation (Specimen specimen){

        AnatomischeLokalisationCluster anatomischeLokalisationCluster = new AnatomischeLokalisationCluster();
        if (specimen.getCollection().hasBodySite()){
            if (specimen.getCollection().getBodySite().hasCoding()){
                return Optional.of(mapBodySiteCoding(specimen,anatomischeLokalisationCluster));
            } else{
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private AnatomischeLokalisationCluster mapBodySiteCoding (Specimen specimen, AnatomischeLokalisationCluster anatomischeLokalisationCluster){
        for (Coding loop : specimen.getCollection().getBodySite().getCoding()){
            Optional<DvCodedText> NameDerKoerperstelle = Optional.of(new DvCodedText(loop.getDisplay(), new CodePhrase(new TerminologyId("SNOMED CT", ""), loop.getCode())));
            NameDerKoerperstelle.ifPresent(anatomischeLokalisationCluster::setNameDerKoerperstelle);
        }
        return anatomischeLokalisationCluster;
    }

    private Optional<TemporalAccessor> mapZeitpunktDerProbenentnahme(Specimen specimen) {
        if (specimen.hasCollection() && specimen.getCollection().hasCollected()) {
            return TimeConverter.convertSpecimanCollection(specimen.getCollection());
        } else {
            return Optional.empty();
        }
    }

    private Optional<DvIdentifier> mapAccessionIdentifier(Specimen specimen) {
        if (specimen.hasAccessionIdentifier()) {
            return Optional.of(DvIdentifierParser.parseIdentifierIntoDvIdentifier(specimen.getAccessionIdentifier()));
        } else {
            return Optional.empty();
        }
    }
}


