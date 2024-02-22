package org.ehrbase.fhirbridge.ehr.converter.specific.mibikultur;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.*;
import org.hl7.fhir.r4.model.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MibiBefundConverter extends ObservationToObservationConverter<BefundObservation> {

    @Override
    protected BefundObservation convertInternal(Observation resource) {
        BefundObservation befundObservation = new BefundObservation();
        befundObservation.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.MICROBIOLOGY_STUDIES);
        befundObservation.setKultur(List.of(mapKultur(resource)));
        mapProbe(resource, befundObservation);
        return befundObservation;
    }

    private void mapProbe(Observation resource, BefundObservation befundObservation) {
        if (resource.hasSpecimen()) {
            if (!resource.getSpecimen().hasExtension()) {
                ProbenConverter probenConverter = new ProbenConverter();
                befundObservation.setProbe(List.of(probenConverter.convert((Specimen) resource.getSpecimen().getResource())));
            }else if(resource.getSpecimen().getExtension().get(0).getUrl().equals("http://hl7.org/fhir/StructureDefinition/data-absent-reason")){
                ProbeCluster probeCluster = new ProbeCluster();
                ProbeZeitpunktDerProbenentnahmeDvDateTime probeZeitpunktDerProbenentnahmeDvDateTime = new ProbeZeitpunktDerProbenentnahmeDvDateTime();
                probeZeitpunktDerProbenentnahmeDvDateTime.setZeitpunktDerProbenentnahmeValue(LocalDateTime.of(1000,1,1, 0, 0, 0));
                probeCluster.setZeitpunktDerProbenentnahme(probeZeitpunktDerProbenentnahmeDvDateTime);
                probeCluster.setLaborprobenidentifikatorNullFlavourDefiningCode(NullFlavour.NOT_APPLICABLE);
                probeCluster.setProbenartNullFlavourDefiningCode(NullFlavour.NOT_APPLICABLE);
                //TODO add this lines in as soon as ehrbase bug is fixed
                // probeCluster.setZeitpunktDerProbenentnahmeNullFlavourDefiningCode(NullFlavour.NO_INFORMATION);
                befundObservation.setProbe(List.of(probeCluster));
            }
        }
    }

    private KulturCluster mapKultur(Observation resource) {
        KulturCluster kulturCluster = new KulturCluster();
        ProErregerCluster proErregerCluster = new ProErregerCluster();
        proErregerCluster.setNachweisValue("Nachweis");
        DvCodedTextParser.getInstance().parseFHIRCoding(resource.getComponent().get(0).getValueCodeableConcept().getCoding().get(0)).ifPresent(proErregerCluster::setErregername);
        proErregerCluster.setErregerdetails(mapErregerdetails(resource));
        //TODO: reimplement as soon as ehrbase bug ist fixed
       //  proErregerCluster.setZugehoerigeLaborprobeNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        ProErregerZugehoerigeLaborprobeDvUri proErregerZugehoerigeLaborprobeDvUri = new ProErregerZugehoerigeLaborprobeDvUri();
        proErregerZugehoerigeLaborprobeDvUri.setZugehoerigeLaborprobeValue(URI.create("null"));
        proErregerCluster.setZugehoerigeLaborprobe(proErregerZugehoerigeLaborprobeDvUri);
        kulturCluster.setProErreger(List.of(proErregerCluster));
        return kulturCluster;
    }

    private ErregerdetailsCluster mapErregerdetails(Observation resource) {
        ErregerdetailsCluster erregerdetailsCluster = new ErregerdetailsCluster();
        erregerdetailsCluster.setAntibiogramm(mapAntibiogramm(resource));
        erregerdetailsCluster.setMreKlasse(mapMREorMRGN(resource));
        return erregerdetailsCluster;
    }

    private AntibiogrammCluster mapAntibiogramm(Observation resource) {
        AntibiogrammCluster antibiogrammCluster = new AntibiogrammCluster();
        ProAntibiotikumCluster proAntibiotikumCluster = new ProAntibiotikumCluster();
        Observation empfindlichkeit = getEmpfindlichkeit(resource);
        DvCodedTextParser.getInstance().parseFHIRCoding(empfindlichkeit.getCode().getCoding().get(0)).ifPresent(proAntibiotikumCluster::setAntibiotikum);
        mapMinimalHemmkonzentration(proAntibiotikumCluster, empfindlichkeit);
        proAntibiotikumCluster.setResistenz(mapInterpretationsCodeToHIGHMEDCode(empfindlichkeit));
        antibiogrammCluster.setProAntibiotikum(List.of(proAntibiotikumCluster));
        return antibiogrammCluster;
    }

    private DvCodedText mapInterpretationsCodeToHIGHMEDCode(Observation empfindlichkeit) {
        Coding coding = empfindlichkeit.getInterpretation().get(0).getCoding().get(0);
        switch (coding.getCode()) {
            case "1306577009":
                return getEUCASTCodes("Susceptible, standard dosing regimen");
            case "1306583007":
                return getEUCASTCodes("Susceptible, increased exposure");
            case "1306581009":
                return getEUCASTCodes("Resistant");
            default:
                throw new IllegalArgumentException("Unsupported code for Interpretation of Resistance, has to be SNOMED EUCAST codes !");
        }
    }

    private DvCodedText getEUCASTCodes(String code){
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://highmed.org/fhir/CodeSystem/ic/resistenzklassen-antibiogramm-eucast"), code);
        DvCodedText dvCodedText = new DvCodedText(code, codePhrase);
        return dvCodedText;
    }


    private ProAntibiotikumCluster mapMinimalHemmkonzentration(ProAntibiotikumCluster proAntibiotikumCluster, Observation empfindlichkeit) {
        if(empfindlichkeit.hasValueQuantity()){
            proAntibiotikumCluster.setMinimaleHemmkonzentrationMagnitude(empfindlichkeit.getValueQuantity().getValue().doubleValue());
            proAntibiotikumCluster.setMinimaleHemmkonzentrationUnits(empfindlichkeit.getValueQuantity().getUnit());
        }
        return proAntibiotikumCluster;
    }

    private Observation getEmpfindlichkeit(Observation resource) {
        return resource.getContained().stream()
                .filter(containedR -> containedR.getMeta().getProfile().get(0).equals("https://www.medizininformatik-initiative.de/fhir/modul-mikrobio/StructureDefinition/mii-pr-mikrobio-empfindlichkeit"))
                .map(containerR -> (Observation) containerR)
                .findFirst().get();
    }

    private DvCodedText mapMREorMRGN(Observation resource) {
        Optional<Coding> coding = resource.getContained().stream()
                .filter(containedR -> containedR.getMeta().getProfile().get(0).equals("https://www.medizininformatik-initiative.de/fhir/modul-mikrobio/StructureDefinition/mii-pr-mikrobio-mre-klasse") || containedR.getMeta().getProfile().get(0).equals("https://www.medizininformatik-initiative.de/fhir/modul-mikrobio/StructureDefinition/mii-pr-mikrobio-mrgn-klasse"))
                .map(containedR -> ((Observation) containedR).getValueCodeableConcept().getCoding().get(0))
                .findFirst();
        return mapMREMRGNCoding(coding);
    }

    private DvCodedText mapMREMRGNCoding(Optional<Coding> coding) {
        switch (coding.get().getCode()) {
            case "115329001":
                return getHighmedMREDVCoded("MRSA");
            case "113727004":
                return getHighmedMREDVCoded("VRE");
            case "LA33214-0":
                return getHighmedMREDVCoded("2MRGN");
            case "LA33215-7":
                return getHighmedMREDVCoded("3MRGN");
            case "LA33216-5":
                return getHighmedMREDVCoded("4MRGN");
            default:
                throw new IllegalArgumentException("Unsupported code for MRE or MRGN !");
        }
    }

    private DvCodedText getHighmedMREDVCoded(String code) {
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://highmed.org/fhir/CodeSystem/ic/mre-klassen-lokal"), code);
        DvCodedText dvCodedText = new DvCodedText(code, codePhrase);
        return dvCodedText;
    }


}
