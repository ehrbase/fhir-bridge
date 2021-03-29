package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.EignungZumTestenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ErgebnisStatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.InterpretationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LabortestKategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytErgebnisStatusDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytKommentarElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytMesswertDvQuantity;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeEignungZumTestenDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeIdentifikatorDerUebergeordnetenProbeElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeProbenentahmebedingungElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbenartDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.UntersuchterAnalytDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Specimen;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LaborergebnisObservationConverter extends ObservationToObservationConverter<LaborergebnisObservation> {


    private static final Map<String, UntersuchterAnalytDefiningCode> untersuchterAnalytLOINCDefiningcodeMap
            = new HashMap<>();
    private static final Map<String, LabortestKategorieDefiningCode> labortestBezeichnungLOINCDefiningcodeMap
            = new HashMap<>();
    private static final Map<String, InterpretationDefiningCode> referenzBereichsHTTPDefiningcodeMap
            = new HashMap<>();
    private static final Map<String, ProbenartDefiningCode> probenartHTTPDefiningcodeMap
            = new HashMap<>();

    static {
        for (UntersuchterAnalytDefiningCode code : UntersuchterAnalytDefiningCode.values()) {
            if (code.getTerminologyId().equals("LOINC")) {
                untersuchterAnalytLOINCDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    static {
        for (LabortestKategorieDefiningCode code : LabortestKategorieDefiningCode.values()) {
            if (code.getTerminologyId().equals("LOINC")) {
                labortestBezeichnungLOINCDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    static {
        for (InterpretationDefiningCode code : InterpretationDefiningCode.values()) {
            if (code.getTerminologyId().equals("http")) {
                referenzBereichsHTTPDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    static {
        for (ProbenartDefiningCode code : ProbenartDefiningCode.values()) {
            if (code.getTerminologyId().equals("http")) {
                probenartHTTPDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    @Override
    protected LaborergebnisObservation convertInternal(Observation resource) {
        LaborergebnisObservation laborergebnisObservation = new LaborergebnisObservation();
        ProLaboranalytCluster laboranalyt = mapToLaboranalyt(resource);
        setLaborergebnisKategorieDefiningCode(laborergebnisObservation, resource);
        setProbe(resource, laborergebnisObservation);
        ProLaboranalytErgebnisStatusDvCodedText ergebnisStatus = new ProLaboranalytErgebnisStatusDvCodedText();
        ergebnisStatus.setErgebnisStatusDefiningCode(getLaboranalyteStatusDefiningCode(resource));
        laboranalyt.setErgebnisStatus(ergebnisStatus);
        laborergebnisObservation.setProLaboranalyt(laboranalyt);
        return laborergebnisObservation;
    }

    private void setProbe(Observation resource, LaborergebnisObservation laborergebnisObservation) {
        if (!resource.getSpecimen().isEmpty()) {
            laborergebnisObservation.getProbe().add(mapSpecimen(resource.getSpecimenTarget()));
        }
    }

    private void setLaborergebnisKategorieDefiningCode(LaborergebnisObservation laborergebnisObservation, Observation resource) {
        if (resource.getCategory().get(0).getCoding().get(0).getSystem().equals(CodeSystem.LOINC.getUrl())) {
            String loincCode = resource.getCategory().get(0).getCoding().get(0).getCode();
            LabortestKategorieDefiningCode categoryDefiningcode = labortestBezeichnungLOINCDefiningcodeMap.get(loincCode);
            if (categoryDefiningcode == null) {
                throw new ConversionException("Unknown LOINC code in observation");
            }
            laborergebnisObservation.setLabortestKategorieDefiningCode(categoryDefiningcode);
        } else {
            throw new ConversionException("No LOINC code in observation");
        }
    }

    private ErgebnisStatusDefiningCode getLaboranalyteStatusDefiningCode(Observation observation){
        switch (observation.getStatus()) {
            case FINAL:
               return ErgebnisStatusDefiningCode.ENDBEFUND;
            case REGISTERED:
                return ErgebnisStatusDefiningCode.ERFASST;
            case AMENDED:
                return ErgebnisStatusDefiningCode.ENDBEFUND_GEAENDERT;
            case CORRECTED:
                return ErgebnisStatusDefiningCode.ENDBEFUND_KORRIGIERT;
            case CANCELLED:
                return ErgebnisStatusDefiningCode.ENDBEFUND_WIDERRUFEN;
            case NULL:
                return ErgebnisStatusDefiningCode.STORNIERT;
            case PRELIMINARY:
                return ErgebnisStatusDefiningCode.VORLAEUFIG;
            default:
                return ErgebnisStatusDefiningCode.UNVOLLSTAENDIG;
        }
    }

    private ProbeCluster mapSpecimen(Specimen specimen) {
        ProbeCluster probe = new ProbeCluster();

        // Map to Probenart
        if (!specimen.getType().getCoding().isEmpty()) {
            ProbenartDefiningCode probenart = null;

            if (specimen.getType().getCoding().get(0).getSystem().equals("http://terminology.hl7.org/CodeSystem/v2-0487")) {
                String code = specimen.getType().getCoding().get(0).getCode();
                probenart = probenartHTTPDefiningcodeMap.get(code);
            }

            if (probenart == null) {
                throw new ConversionException("Probenart not defined in specimen");
            }

            probe.setProbenartDefiningCode(probenart);
        }


        // Map Labor/External Identifikator
        probe.setLaborprobenidentifikator(mapIdentifier(specimen.getAccessionIdentifier()));
        probe.setExternerIdentifikator(mapIdentifier(specimen.getIdentifier().get(0)));

        // Map Zeitpunkt des Probeneingangs
        probe.setZeitpunktDesProbeneingangsValue((new DateTimeType(specimen.getReceivedTime())).getValueAsCalendar().toZonedDateTime());


        // Map Zeitpunkt der Probenentnahme (either interval or time instant)
        if (specimen.getCollection().getCollectedPeriod().hasStart() && specimen.getCollection().getCollectedPeriod().hasEnd()) {
            Date start = specimen.getCollection().getCollectedPeriod().getStart();
            Date end = specimen.getCollection().getCollectedPeriod().getEnd();
            probe.setZeitpunktDesProbeneingangsValue((new DateTimeType(start)).getValueAsCalendar().toZonedDateTime());
            probe.setZeitpunktDesProbeneingangsValue((new DateTimeType(end)).getValueAsCalendar().toZonedDateTime());
        } else {
            DateTimeType date = specimen.getCollection().getCollectedDateTimeType();
            probe.setZeitpunktDerProbenentnahmeValue(date.getValueAsCalendar().toZonedDateTime());
        }

        // Map collection->collector to Identifikator des Probenentnehmers
        probe.setIdentifikatorDesProbennehmers(mapIdentifier(specimen.getCollection().getCollector().getIdentifier()));

        // Map parents -> Identifikator der übergeordneten Probe
        for (Reference reference : specimen.getParent()) {
            ProbeIdentifikatorDerUebergeordnetenProbeElement identifikator = new ProbeIdentifikatorDerUebergeordnetenProbeElement();
            identifikator.setValue(mapIdentifier(reference.getIdentifier()));
            probe.getIdentifikatorDerUebergeordnetenProbe().add(identifikator);
        }

        // Map Condition -> Probenentnahmebedingung
        for (CodeableConcept codeableConcept : specimen.getCondition()) {
            if (!codeableConcept.getCoding().isEmpty()) {
                ProbeProbenentahmebedingungElement bedingung = new ProbeProbenentahmebedingungElement();
                bedingung.setValue(codeableConcept.getCoding().get(0).getDisplay());
                probe.getProbenentahmebedingung().add(bedingung);
            }
        }
        // Map Collection -> Method to Probenentnahmemethode
        probe.setProbenentnahmemethodeValue(specimen.getCollection().getMethod().getText());
        probe.setProbenentnahmemethodeValue(specimen.getCollection().getBodySite().getText());

        // Map Status -> Eignung zum Testen
        EignungZumTestenDefiningCode eignungZumTestenDefiningcode = EignungZumTestenDefiningCode.ZUFRIEDENSTELLEND;
        // TODO: Check if these mappings are correct.
        switch (specimen.getStatus()) {
            case UNSATISFACTORY:
                eignungZumTestenDefiningcode = EignungZumTestenDefiningCode.MANGELHAFT_VERARBEITET;
                break;
            case ENTEREDINERROR:
            case UNAVAILABLE:
            case NULL:
                eignungZumTestenDefiningcode = EignungZumTestenDefiningCode.MANGELHAFT_NICHT_VERARBEITET;
                break;
            default:
                break;
        }

        ProbeEignungZumTestenDvCodedText eignungZumTesten = new ProbeEignungZumTestenDvCodedText();
        eignungZumTesten.setEignungZumTestenDefiningCode(eignungZumTestenDefiningcode);
        probe.setEignungZumTesten(eignungZumTesten);

        if (!specimen.getNote().isEmpty()) {
            probe.setKommentarValue(specimen.getNote().get(0).getText());
        }

        return probe;
    }


    private DvIdentifier mapIdentifier(Identifier identifier) {
        if (identifier == null) {
            throw new UnprocessableEntityException("Unknown identifier");
        }

        DvIdentifier dvIdentifier = new DvIdentifier();

        dvIdentifier.setAssigner(identifier.getAssigner().getDisplay());
        dvIdentifier.setId(identifier.getId());
        dvIdentifier.setType(identifier.getType().getText());

        return dvIdentifier;
    }




    /**
     * Maps a FHIR Observation to an openEHR LaboranalytResultatCluster generated from the Laborbefund template.
     *
     * @param fhirObservation the FHIR Observation resource received in the API.
     * @return the cluster defined in the OPT that maps to the FHIR observation
     */
    private ProLaboranalytCluster mapToLaboranalyt(Observation fhirObservation) {

        // ========================================================================================
        // value quantity is expected
        Quantity fhirValue = null;
        BigDecimal fhirValueNumeric = null;
        DateTimeType fhirEffectiveDateTime = null;
        DateTimeType issuedDateTime = null;

        UntersuchterAnalytDefiningCode untersuchterAnalyt = null;
        InterpretationDefiningCode interpretationDefiningcode = null;

        ProLaboranalytKommentarElement kommentarElement = new ProLaboranalytKommentarElement();


        try {
            fhirValue = fhirObservation.getValueQuantity();
            fhirValueNumeric = fhirValue.getValue();

            if (fhirObservation.getCode().getCoding().get(0).getSystem().equals("http://loinc.org")) {
                String code = fhirObservation.getCode().getCoding().get(0).getCode();
                untersuchterAnalyt = untersuchterAnalytLOINCDefiningcodeMap.get(code);
            }

            if (!fhirObservation.getInterpretation().isEmpty() && fhirObservation.getInterpretation().get(0).getCoding().get(0).getSystem().equals("http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation")) {
                String code = fhirObservation.getInterpretation().get(0).getCoding().get(0).getCode();
                interpretationDefiningcode = referenzBereichsHTTPDefiningcodeMap.get(code);
            }

            if (!fhirObservation.getNote().isEmpty()) {
                kommentarElement.setValue(fhirObservation.getNote().get(0).getText());
            }


            fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();

        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }

        if (fhirValueNumeric == null) {
            throw new ConversionException("Value is required in FHIR Observation and should be Quantity");
        }
        if (fhirEffectiveDateTime == null) {
            throw new ConversionException("effectiveDateTime is required in FHIR Observation");
        }
        if (untersuchterAnalyt == null) {
            throw new ConversionException("untersuchterAnalyt is required in FHIR Observation");
        }


        // mapping to openEHR
        // ProLaboranalytMesswertChoice
        ProLaboranalytMesswertDvQuantity laboranalytResultat = new ProLaboranalytMesswertDvQuantity();

        // map value to magnitude and unit
        laboranalytResultat.setMesswertMagnitude(fhirValueNumeric.doubleValue());
        laboranalytResultat.setMesswertUnits(fhirValue.getUnit());

        // =======================================================================================
        // rest of the structure to build the composition with the value taken from FHIR
        ProLaboranalytCluster laboranalyt = new ProLaboranalytCluster();

        laboranalyt.setMesswert(laboranalytResultat);
        laboranalyt.setUntersuchterAnalytDefiningCode(untersuchterAnalyt);

        laboranalyt.setInterpretationDefiningCode(interpretationDefiningcode);

        if (kommentarElement.getValue() != null) {
            laboranalyt.getKommentar().add(kommentarElement);
        }

        laboranalyt.setZeitpunktValidationValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        if (!fhirObservation.getIssuedElement().isEmpty()) {
            issuedDateTime = new DateTimeType(fhirObservation.getIssued());
            laboranalyt.setZeitpunktErgebnisStatusValue(issuedDateTime.getValueAsCalendar().toZonedDateTime());
        }

        return laboranalyt;
    }
}
