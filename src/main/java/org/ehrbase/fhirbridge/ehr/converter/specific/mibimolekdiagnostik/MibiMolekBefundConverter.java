package org.ehrbase.fhirbridge.ehr.converter.specific.mibimolekdiagnostik;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.BefundJedesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ErgebnisStatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.LabortestBezeichnungDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.LabortestPanelCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytErgebnisStatusDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytErgebnisStatusElement;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytQuantitativesErgebnisDvQuantity;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytQuantitativesErgebnisElement;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Specimen;

import java.util.ArrayList;
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
        befundObservation.setJedesEreignis(List.of(befundJedesEreignisPointEvent));
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
        proAnalytErgebnisStatusElement.setValue2(proAnalytErgebnisStatusDvCodedText);
        proAnalytCluster.setErgebnisStatus(List.of(proAnalytErgebnisStatusElement));
        List<ProAnalytQuantitativesErgebnisElement> proAnalytQuantitativesErgebnisElementList = new ArrayList<>();
        mapQuantitativesErgebnis(resource).ifPresent(proAnalytQuantitativesErgebnisElementList::add);
        proAnalytCluster.setQuantitativesErgebnis(proAnalytQuantitativesErgebnisElementList);
        labortestPanelCluster.setProAnalyt(List.of(proAnalytCluster));
        return labortestPanelCluster;
    }

    private Optional<ProAnalytQuantitativesErgebnisElement> mapQuantitativesErgebnis(Observation mibiDiag) {
        for (Observation.ObservationComponentComponent observation : mibiDiag.getComponent()) {
            if (observation.getCode().getCoding().get(0).getCode().equals("398545005")) {
                ProAnalytQuantitativesErgebnisElement proAnalytQuantitativesErgebnisElement = new ProAnalytQuantitativesErgebnisElement();
                ProAnalytQuantitativesErgebnisDvQuantity proAnalytQuantitativesErgebnisDvQuantity = new ProAnalytQuantitativesErgebnisDvQuantity();
                proAnalytQuantitativesErgebnisDvQuantity.setQuantitativesErgebnisMagnitude(observation.getValueQuantity().getValue().doubleValue());
                proAnalytQuantitativesErgebnisDvQuantity.setQuantitativesErgebnisUnits(observation.getValueQuantity().getCode());
                proAnalytQuantitativesErgebnisElement.setValue2(proAnalytQuantitativesErgebnisDvQuantity);
                return Optional.of(proAnalytQuantitativesErgebnisElement);
            }
        }
        return Optional.empty();
    }

    private DvCodedText mapNachweis(Observation resource) {
        switch (resource.getValueCodeableConcept().getCoding().get(0).getCode()) {
            case "10828004":
            case "260408008":
                return getAsDvCodedTest(CodeSystem.SNOMED, "260373001", "Detected");
            case "260385009":
                return getAsDvCodedTest(CodeSystem.SNOMED, "260415000", "Not detected");
            case "419984006":
                return getAsDvCodedTest(CodeSystem.SNOMED, "419984006", "Inconclusive");
            default:
                throw new ConversionException("Code is not supported");
        }
    }

    private void mapProbe(Observation resource, BefundJedesEreignisPointEvent befundJedesEreignisPointEvent) {
        ProbenConverter probenConverter = new ProbenConverter();
        befundJedesEreignisPointEvent.setProbe(probenConverter.convert((Specimen) resource.getSpecimen().getResource()));
    }

    private DvCodedText getAsDvCodedTest(CodeSystem system, String code, String display) {
        CodePhrase codePhrase = new CodePhrase(new TerminologyId(system.getUrl()), code);
        return new DvCodedText(display, codePhrase);
    }

}
