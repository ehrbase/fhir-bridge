package org.ehrbase.fhirbridge.ehr.converter.specific.mibimolekdiagnostik;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.converter.InvalidStatusCodeException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.*;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;

import java.util.List;
import java.util.Optional;

public class MibiMolekBefundConverter extends ObservationToObservationConverter<BefundObservation> {

    @Override
    protected BefundObservation convertInternal(Observation resource) {
        BefundObservation befundObservation = new BefundObservation();
        BefundJedesEreignisPointEvent befundJedesEreignisPointEvent = new BefundJedesEreignisPointEvent();
        befundJedesEreignisPointEvent.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.DETECTION_OF_VIRUS_PROCEDURE);
        befundJedesEreignisPointEvent.setLabortestPanel(mapLabortestPanel(resource));
        mapProbe(resource, befundJedesEreignisPointEvent);
        return befundObservation;
    }

    private LabortestPanelCluster mapLabortestPanel(Observation resource) {
        LabortestPanelCluster labortestPanelCluster = new LabortestPanelCluster();
        ProAnalytCluster proAnalytCluster = new ProAnalytCluster();
        proAnalytCluster.setVirusnachweistest(getAsDvCodedTest(CodeSystem.LOINC, "945006-6", "SARS-CoV-2 (COVID-19) RNA [Presence] in Respiratory system specimen by NAA with probe detection"));
        proAnalytCluster.setNachweis(mapNachweis(resource));
        ProAnalytErgebnisStatusElement proAnalytErgebnisStatusElement = new ProAnalytErgebnisStatusElement();
        ProAnalytErgebnisStatusDvCodedText proAnalytErgebnisStatusDvCodedText = new ProAnalytErgebnisStatusDvCodedText();
        proAnalytErgebnisStatusDvCodedText.setErgebnisStatusDefiningCode(ErgebnisStatusDefiningCode.ENDBEFUND);
        proAnalytErgebnisStatusElement.setValue2(List.of(proAnalytErgebnisStatusDvCodedText));
        proAnalytCluster.setErgebnisStatus(List.of(proAnalytErgebnisStatusElement));
        mapQuantitativesErgebnis(resource).ifPresent(proAnalytCluster::setQuantitativesErgebnis);
        labortestPanelCluster.setProAnalyt(List.of(proAnalytCluster));
        return labortestPanelCluster;
    }

    private Optional<List<ProAnalytQuantitativesErgebnisElement>> mapQuantitativesErgebnis(Observation mibiDiag) {
        for (Observation.ObservationComponentComponent observation : mibiDiag.getComponent()) {
            if (observation.getCode().getCoding().get(0).getCode().equals("398545005")) {
                ProAnalytQuantitativesErgebnisElement proAnalytQuantitativesErgebnisElement = new ProAnalytQuantitativesErgebnisElement();
                ProAnalytQuantitativesErgebnisDvQuantity proAnalytQuantitativesErgebnisDvQuantity = new ProAnalytQuantitativesErgebnisDvQuantity();
                proAnalytQuantitativesErgebnisDvQuantity.setQuantitativesErgebnisMagnitude(observation.getValueQuantity().getValue().doubleValue());
                proAnalytQuantitativesErgebnisDvQuantity.setQuantitativesErgebnisUnits(observation.getValueQuantity().getUnit());
                proAnalytQuantitativesErgebnisElement.setValue2(List.of(proAnalytQuantitativesErgebnisDvQuantity));
                return Optional.of(List.of(proAnalytQuantitativesErgebnisElement));
            }
        }
        return Optional.empty();
    }

    private DvCodedText mapNachweis(Observation resource) {
        switch (resource.getStatusElement().getCode()) {
            case "Positive":
            case "Weakly positive":
                return getAsDvCodedTest(CodeSystem.SNOMED, "260373001", "Detected");
            case "Negative":
                return getAsDvCodedTest(CodeSystem.SNOMED, "260415000", "Not detected");
            case "Inconclusive":
                return getAsDvCodedTest(CodeSystem.SNOMED, "419984006", "Inconclusive");
            default:
                throw new InvalidStatusCodeException(resource.getStatusElement().getCode());
        }
    }

    private void mapProbe(Observation resource, BefundJedesEreignisPointEvent befundJedesEreignisPointEvent) {
        if (resource.hasSpecimen()) {
            if (resource.getSpecimen().hasExtension() && !resource.getSpecimen().getExtension().get(0).getUrl().equals("http://hl7.org/fhir/StructureDefinition/data-absent-reason")) {
                ProbenConverter probenConverter = new ProbenConverter();
                befundJedesEreignisPointEvent.setProbe(probenConverter.convert(resource.getSpecimenTarget()));
            }
        }
    }

    private DvCodedText getAsDvCodedTest(CodeSystem system, String code, String display) {
        CodePhrase codePhrase = new CodePhrase(new TerminologyId(system.getUrl()), code);
        return new DvCodedText(display, codePhrase);
    }

}
