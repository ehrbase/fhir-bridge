package org.ehrbase.fhirbridge.ehr.converter.specific.mibikultur;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.*;
import org.hl7.fhir.r4.model.*;

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
            if (resource.getSpecimen().hasExtension() && !resource.getSpecimen().getExtension().get(0).getUrl().equals("http://hl7.org/fhir/StructureDefinition/data-absent-reason")) {
                ProbenConverter probenConverter = new ProbenConverter();
                befundObservation.setProbe(List.of(probenConverter.convert(resource.getSpecimenTarget())));
            }
        }
    }

    private KulturCluster mapKultur(Observation resource) {
        KulturCluster kulturCluster = new KulturCluster();
        ProErregerCluster proErregerCluster = new ProErregerCluster();
        proErregerCluster.setNachweisValue("Nachweis");
        DvCodedTextParser.getInstance().parseFHIRCoding(resource.getComponent().get(0).getValueCodeableConcept().getCoding().get(0)).ifPresent(proErregerCluster::setErregername);
        proErregerCluster.setErregerdetails(mapErregerdetails(resource));
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
        DvCodedTextParser.getInstance().parseFHIRCoding(empfindlichkeit.getValueCodeableConcept().getCoding().get(0)).ifPresent(proAntibiotikumCluster::setAntibiotikum);
        
        // valueQuantity
        // Interpretation
        antibiogrammCluster.setProAntibiotikum(List.of(proAntibiotikumCluster));
        return antibiogrammCluster;
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
                return getHighmedDVCoded("MRSA");
            case "113727004":
                return getHighmedDVCoded("VRE");
            case "LA33214-0":
                return getHighmedDVCoded("2MRGN");
            case "LA33215-7":
                return getHighmedDVCoded("3MRGN");
            case "LA33216-5":
                return getHighmedDVCoded("4MRGN");
            default:
                throw new IllegalArgumentException("Unsupported code for MRE or MRGN !");
        }
    }

    private DvCodedText getHighmedDVCoded(String code) {
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("http://highmed.org/fhir/CodeSystem/ic/mre-klassen-lokal"), code);
        DvCodedText dvCodedText = new DvCodedText(code, codePhrase);
        return dvCodedText;
    }


}
