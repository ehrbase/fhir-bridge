package org.ehrbase.fhirbridge.ehr.converter.observationlab;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
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
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.UntersuchterAnalytDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Specimen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ObservationLabCompositionConverter extends AbstractCompositionConverter<Observation, GECCOLaborbefundComposition> {

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
    public GECCOLaborbefundComposition convert(@NonNull Observation observation) {
        GECCOLaborbefundComposition result = new GECCOLaborbefundComposition();
        mapCommonAttributes(observation, result);

        LaborergebnisObservation laborergebnis = new LaborergebnisObservation();
        ProLaboranalytCluster laboranalyt = mapToLaboranalyt(observation);


        // Map Status to composition and laboranalyt
        StatusDefiningCode registereintragStatus = StatusDefiningCode.REGISTRIERT;
        ErgebnisStatusDefiningCode laboranalytStatusDefiningcode = ErgebnisStatusDefiningCode.UNVOLLSTAENDIG;

        switch (observation.getStatus()) {
            case FINAL:
                registereintragStatus = StatusDefiningCode.FINAL;
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningCode.ENDBEFUND;
                break;
            case REGISTERED:
                registereintragStatus = StatusDefiningCode.REGISTRIERT;
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningCode.ERFASST;
                break;
            case AMENDED:
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningCode.ENDBEFUND_GEAENDERT;
                break;
            case CORRECTED:
                registereintragStatus = StatusDefiningCode.GEAENDERT;
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningCode.ENDBEFUND_KORRIGIERT;
                break;
            case CANCELLED:
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningCode.ENDBEFUND_WIDERRUFEN;
                break;
            case ENTEREDINERROR:
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningCode.UNVOLLSTAENDIG;
                break;
            case NULL:
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningCode.STORNIERT;
                break;
            case PRELIMINARY:
                registereintragStatus = StatusDefiningCode.VORLAEUFIG;
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningCode.VORLAEUFIG;
                break;
            default:
                break;
        }

        result.setStatusDefiningCode(registereintragStatus);

        ProLaboranalytErgebnisStatusDvCodedText ergebnisStatus = new ProLaboranalytErgebnisStatusDvCodedText();
        ergebnisStatus.setErgebnisStatusDefiningCode(laboranalytStatusDefiningcode);
        laboranalyt.setErgebnisStatus(ergebnisStatus);


        // Map category, only LOINC part see https://github.com/ehrbase/num_platform/issues/33
        if (observation.getCategory().get(0).getCoding().get(0).getSystem().equals("http://loinc.org")) {
            String loincCode = observation.getCategory().get(0).getCoding().get(0).getCode();
            LabortestKategorieDefiningCode categoryDefiningcode = labortestBezeichnungLOINCDefiningcodeMap.get(loincCode);

            if (categoryDefiningcode == null) {
                throw new UnprocessableEntityException("Unknown LOINC code in observation");
            }

            result.setKategorieValue(categoryDefiningcode.getValue());
            laborergebnis.setLabortestKategorieDefiningCode(categoryDefiningcode);
        } else {
            throw new UnprocessableEntityException("No LOINC code in observation");
        }

        // Map performer to health care facility
        if (!observation.getPerformer().isEmpty()) {
            PartyIdentified healthCareFacility = new PartyIdentified();
            DvIdentifier identifier = mapIdentifier(observation.getPerformer().get(0).getIdentifier());
            healthCareFacility.addIdentifier(identifier);
            healthCareFacility.setName(observation.getPerformer().get(0).getDisplay());
            result.setHealthCareFacility(healthCareFacility);
        }

        // Map speciment to Probe
        if (!observation.getSpecimen().isEmpty()) {
            laborergebnis.getProbe().add(mapSpecimen(observation.getSpecimenTarget()));
        }


        // Map method to Testmethode

        if (!observation.getMethod().isEmpty() && !observation.getMethod().getCoding().isEmpty()) {
            DvText testmethode = new DvText();
            testmethode.setValue(observation.getMethod().getCoding().get(0).getDisplay());
//            laborergebnis.setValue(testmethode);
        }

        laborergebnis.setProLaboranalyt(laboranalyt);


        laborergebnis.setOriginValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime()); // mandatory
        laborergebnis.setTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        laborergebnis.setLanguage(Language.EN);
        laborergebnis.setSubject(new PartySelf());


        result.setLaborergebnis(laborergebnis);

        // ======================================================================================
        // Required fields by API
        result.setStartTimeValue(observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());

        return result;
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
                throw new UnprocessableEntityException("Probenart not defined in specimen");
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
            probe.setZeitpunktDerProbenentnahmeValue((new DateTimeType(start)).getValueAsCalendar().toZonedDateTime());
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
            throw new UnprocessableEntityException(e.getMessage());
        }

        if (fhirValueNumeric == null) {
            throw new UnprocessableEntityException("Value is required in FHIR Observation and should be Quantity");
        }
        if (fhirEffectiveDateTime == null) {
            throw new UnprocessableEntityException("effectiveDateTime is required in FHIR Observation");
        }
        if (untersuchterAnalyt == null) {
            throw new UnprocessableEntityException("untersuchterAnalyt is required in FHIR Observation");
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
