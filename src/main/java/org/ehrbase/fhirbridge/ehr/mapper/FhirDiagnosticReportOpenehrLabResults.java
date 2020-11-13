package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import org.ehrbase.fhirbridge.ehr.mapper.CommonData;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.*;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.*;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.*;
import com.nedap.archie.rm.generic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.Date.from;

/**
 * FHIR to openEHR - Laboratory report
 */
public class FhirDiagnosticReportOpenehrLabResults {

    private static final Logger logger = LoggerFactory.getLogger(FhirDiagnosticReportOpenehrLabResults.class);

    private static final Map<String, UntersuchterAnalytDefiningcode> untersuchterAnalytLOINCDefiningcodeMap
            = new HashMap<>();

    static {
        for (UntersuchterAnalytDefiningcode code : UntersuchterAnalytDefiningcode.values()) {
            if (code.getTerminologyId().equals("LOINC")) {
                untersuchterAnalytLOINCDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    private static final Map<String, LabortestBezeichnungDefiningcode> labortestBezeichnungLOINCDefiningcodeMap
            = new HashMap<>();

    static {
        for (LabortestBezeichnungDefiningcode code : LabortestBezeichnungDefiningcode.values()) {
            if (code.getTerminologyId().equals("LOINC")) {
                labortestBezeichnungLOINCDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    private static final Map<String, ReferenzbereichsHinweiseDefiningcode> referenzBereichsHTTPDefiningcodeMap
            = new HashMap<>();

    static {
        for (ReferenzbereichsHinweiseDefiningcode code : ReferenzbereichsHinweiseDefiningcode.values()) {
            if (code.getTerminologyId().equals("http")) {
                referenzBereichsHTTPDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    private static final Map<String, ProbenartDefiningcode> probenartHTTPDefiningcodeMap
            = new HashMap<>();

    static {
        for (ProbenartDefiningcode code : ProbenartDefiningcode.values()) {
            if (code.getTerminologyId().equals("http")) {
                probenartHTTPDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }


    private FhirDiagnosticReportOpenehrLabResults() {
    }

    /**
     * this maps a single lab observation to a composition, the map(DiagnosticReport) method maps a
     * DiagnosticReport with a direct contained Observation to a composition.
     *
     * @param fhirObservation the FHIR Observation resource received in the API.
     * @return the Composition defined by the laborbefund template.
     */
    public static GECCOLaborbefundComposition map(Observation fhirObservation) {

        GECCOLaborbefundComposition composition = new GECCOLaborbefundComposition();

        // set feeder auhttps://www.medizininformatik-initiative.de/fhir/core/StructureDefinition/ObservationLabdit
        FeederAudit fa = CommonData.constructFeederAudit(fhirObservation);
        composition.setFeederAudit(fa);

        LaborergebnisObservation laborergebnis = new LaborergebnisObservation();
        ProLaboranalytCluster laboranalyt = mapToLaboranalyt(fhirObservation);


        // Map Status to composition and laboranalyt
        StatusDefiningcode registereintragStatus = StatusDefiningcode.REGISTRIERT;
        ErgebnisStatusDefiningcode laboranalytStatusDefiningcode = ErgebnisStatusDefiningcode.UNVOLLSTANDIG;

        // TODO: Check if corrected=changed and default=registered is correct.
        switch (fhirObservation.getStatus()) {
            case FINAL:
                registereintragStatus = StatusDefiningcode.FINAL;
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningcode.ENDBEFUND;
                break;
            case REGISTERED:
                registereintragStatus = StatusDefiningcode.REGISTRIERT;
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningcode.ERFASST;
                break;
            case AMENDED:
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningcode.ENDBEFUND_GEANDERT;
                break;
            case CORRECTED:
                registereintragStatus = StatusDefiningcode.GEANDERT;
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningcode.ENDBEFUND_KORRIGIERT;
                break;
            case CANCELLED:
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningcode.ENDBEFUND_WIDERRUFEN;
                break;
            case ENTEREDINERROR:
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningcode.UNVOLLSTANDIG;
                break;
            case NULL:
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningcode.STORNIERT;
                break;
            case PRELIMINARY:
                registereintragStatus = StatusDefiningcode.VORLAUFIG;
                laboranalytStatusDefiningcode = ErgebnisStatusDefiningcode.VORLAUFIG;
                break;
        }

        composition.setStatusDefiningcode(registereintragStatus);

        ProLaboranalytErgebnisStatusDvcodedtext ergebnisStatus = new ProLaboranalytErgebnisStatusDvcodedtext();
        ergebnisStatus.setErgebnisStatusDefiningcode(laboranalytStatusDefiningcode);
        laboranalyt.setErgebnisStatus(ergebnisStatus);


        // Map category, only LOINC part see https://github.com/ehrbase/num_platform/issues/33
        if (fhirObservation.getCategory().get(0).getCoding().get(0).getSystem().equals("http://loinc.org")) {
            String loincCode = fhirObservation.getCategory().get(0).getCoding().get(0).getCode();
            LabortestBezeichnungDefiningcode categoryDefiningcode = labortestBezeichnungLOINCDefiningcodeMap.get(loincCode);

            if (categoryDefiningcode == null) {
                throw new UnprocessableEntityException("Unknown LOINC code in observation");
            }

            composition.setKategorieValue(categoryDefiningcode.getValue());
            laborergebnis.setLabortestBezeichnungDefiningcode(categoryDefiningcode);
        } else {
            throw new UnprocessableEntityException("No LOINC code in observation");
        }

        // Map performer to health care facility
        if (!fhirObservation.getPerformer().isEmpty()) {
            PartyIdentified healthCareFacility = new PartyIdentified();
            DvIdentifier identifier = mapIdentifier(fhirObservation.getPerformer().get(0).getIdentifier());
            healthCareFacility.addIdentifier(identifier);
            healthCareFacility.setName(fhirObservation.getPerformer().get(0).getDisplay());
            composition.setHealthCareFacility(healthCareFacility);
        }

        // Map speciment to Probe
        if (!fhirObservation.getSpecimen().isEmpty()) {
            laborergebnis.getProbe().add(mapSpecimen(fhirObservation.getSpecimenTarget()));
        }


        // Map method to Testmethode

        if(!fhirObservation.getMethod().isEmpty() && !fhirObservation.getMethod().getCoding().isEmpty())
        {
            DvText testmethode = new DvText();
            testmethode.setValue(fhirObservation.getMethod().getCoding().get(0).getDisplay());
            laborergebnis.setValue(testmethode);
        }

        laborergebnis.setProLaboranalyt(laboranalyt);


        laborergebnis.setOriginValue(fhirObservation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime()); // mandatory
        laborergebnis.setTimeValue(fhirObservation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        laborergebnis.setLanguage(Language.EN);
        laborergebnis.setSubject(new PartySelf());


        composition.setLaborergebnis(laborergebnis);

        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.EN);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(fhirObservation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        // FIXME: https://github.com/ehrbase/ehrbase_client_library/issues/31
        //        PartyProxy composer = new PartyIdentified();
        //        composition.setComposer(composer);

        composition.setComposer(new PartySelf());

        return composition;
    }

    public static DvIdentifier mapIdentifier(Identifier identifier) {
        if (identifier == null) {
            throw new UnprocessableEntityException("Unknown identifier");
        }

        DvIdentifier dvIdentifier = new DvIdentifier();

        dvIdentifier.setAssigner(identifier.getAssigner().getDisplay());
        dvIdentifier.setId(identifier.getId());
        dvIdentifier.setType(identifier.getType().getText());

        return dvIdentifier;
    }


    public static ProbeCluster mapSpecimen(Specimen specimen) {
        ProbeCluster probe = new ProbeCluster();

        // Map to Probenart
        if(!specimen.getType().getCoding().isEmpty()) {
            ProbenartDefiningcode probenart = null;

            if (specimen.getType().getCoding().get(0).getSystem().equals("http://terminology.hl7.org/CodeSystem/v2-0487")) {
                String code = specimen.getType().getCoding().get(0).getCode();
                probenart = probenartHTTPDefiningcodeMap.get(code);
            }

            if (probenart == null) {
                throw new UnprocessableEntityException("Probenart not defined in specimen");
            }

            probe.setProbenartDefiningcode(probenart);
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

            ProbeZeitpunktDerProbenentnahmeDvinterval interval = new ProbeZeitpunktDerProbenentnahmeDvinterval();

            DvInterval<DvDate> dateDvInterval = new DvInterval<>();

            dateDvInterval.setLower(new DvDate((new DateTimeType(start)).getValueAsCalendar().toZonedDateTime()));
            dateDvInterval.setUpper(new DvDate((new DateTimeType(end)).getValueAsCalendar().toZonedDateTime()));

            interval.setZeitpunktDerProbenentnahme(dateDvInterval);

            probe.setZeitpunktDerProbenentnahme(interval);
        } else {
            DateTimeType date = specimen.getCollection().getCollectedDateTimeType();

            ProbeZeitpunktDerProbenentnahmeDvdatetime zeitpunkt = new ProbeZeitpunktDerProbenentnahmeDvdatetime();
            zeitpunkt.setZeitpunktDerProbenentnahmeValue(date.getValueAsCalendar().toZonedDateTime());

            probe.setZeitpunktDerProbenentnahme(zeitpunkt);
        }

        // Map collection->collector to Identifikator des Probenentnehmers
        probe.setIdentifikatorDesProbennehmers(mapIdentifier(specimen.getCollection().getCollector().getIdentifier()));

        // Map parents -> Identifikator der übergeordneten Probe
        for (Reference reference : specimen.getParent()) {
            ProbeIdentifikatorDerUbergeordnetenProbeElement identifikator = new ProbeIdentifikatorDerUbergeordnetenProbeElement();
            identifikator.setValue(mapIdentifier(reference.getIdentifier()));

            probe.getIdentifikatorDerUbergeordnetenProbe().add(identifikator);
        }

        // Map Condition -> Probenentnahmebedingung
        for (CodeableConcept codeableConcept : specimen.getCondition()) {
            if(!codeableConcept.getCoding().isEmpty())
            {
                ProbeProbenentahmebedingungElement bedingung = new ProbeProbenentahmebedingungElement();
                bedingung.setValue(codeableConcept.getCoding().get(0).getDisplay());
                probe.getProbenentahmebedingung().add(bedingung);
            }
        }

        // Map Collection -> Method to Probenentnahmemethode
        probe.setProbenentnahmemethodeValue(specimen.getCollection().getMethod().getText());

        // TODO: What about setProbenentnahmestelleName?
        probe.setProbenentnahmestelleValue(specimen.getCollection().getBodySite().getText());

        // Map Status -> Eignung zum Testen
        EignungZumTestenDefiningcode eignungZumTestenDefiningcode = EignungZumTestenDefiningcode.ZUFRIEDENSTELLEND;
        // TODO: Check if these mappings are correct.
        switch (specimen.getStatus()) {
            case UNSATISFACTORY:
                eignungZumTestenDefiningcode = EignungZumTestenDefiningcode.MANGELHAFT_VERARBEITET;
                break;
            case ENTEREDINERROR:
            case UNAVAILABLE:
            case NULL:
                eignungZumTestenDefiningcode = EignungZumTestenDefiningcode.MANGELHAFT_NICHT_VERARBEITET;
                break;
        }

        ProbeEignungZumTestenDvcodedtext eignungZumTesten = new ProbeEignungZumTestenDvcodedtext();
        eignungZumTesten.setEignungZumTestenDefiningcode(eignungZumTestenDefiningcode);
        probe.setEignungZumTesten(eignungZumTesten);

        if(!specimen.getNote().isEmpty())
        {
            probe.setKommentarValue(specimen.getNote().get(0).getText());
        }

        return probe;
    }


    /**
     * this maps a DiagnosticReport with a direct contained Observation to an
     * openEHR composition generated for the Laborbefund template.
     *
     * @param fhirDiagnosticReport the DiagnosticReport FHIR resource received in the API
     * @return the Composition defined by the laborbefund template
     */
    public static GECCOLaborbefundComposition map(DiagnosticReport fhirDiagnosticReport) {


        logger.debug("Contained size: {}", fhirDiagnosticReport.getContained().size());

        // one contained Observation is expected
        if (fhirDiagnosticReport.getContained().size() != 1) {
            throw new UnprocessableEntityException("One contained Observation was expected " + fhirDiagnosticReport.getContained().size() + " were received in DiagnosticReport " + fhirDiagnosticReport.getId());
        }
        if (fhirDiagnosticReport.getContained().get(0).getResourceType() != ResourceType.Observation) {
            throw new UnprocessableEntityException("One contained Observation was expected, contained is there but is not Observation, it is " + fhirDiagnosticReport.getContained().get(0).getResourceType().toString());
        }

        Observation fhirObservation = (Observation) fhirDiagnosticReport.getContained().get(0);

        GECCOLaborbefundComposition composition = map(fhirObservation);


        LaborergebnisObservation laborbefund = composition.getLaborergebnis();

        ProLaboranalytCluster laboranalytCluster = laborbefund.getProLaboranalyt();

        laborbefund.setTimeValue(fhirObservation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        laborbefund.setLabortestBezeichnungValue(fhirDiagnosticReport.getCode().getText());
        laborbefund.setSchlussfolgerungValue(fhirDiagnosticReport.getConclusion());


        laborbefund.setProLaboranalyt(laboranalytCluster);


        //DvIdentifier receiverOrderIdentifier = new DvIdentifier();
        //receiverOrderIdentifier.setId(fhirDiagnosticReport.getIdentifier().get(0).getValue());
        //receiverOrderIdentifier.setType(fhirDiagnosticReport.getIdentifier().get(0).getSystem());
        //laborbefund.setLaborWelchesDenUntersuchungsauftragAnnimmt(receiverOrderIdentifier);


        return composition;
    }


    /**
     * Maps a FHIR Observation to an openEHR LaboranalytResultatCluster generated from the Laborbefund template.
     *
     * @param fhirObservation the FHIR Observation resource received in the API.
     * @return the cluster defined in the OPT that maps to the FHIR observation
     */
    private static ProLaboranalytCluster mapToLaboranalyt(Observation fhirObservation) {

        // ========================================================================================
        // value quantity is expected
        Quantity fhirValue = null;
        BigDecimal fhirValueNumeric = null;
        DateTimeType fhirEffectiveDateTime = null;
        DateTimeType issuedDateTime = null;

        UntersuchterAnalytDefiningcode untersuchterAnalyt = null;
        ReferenzbereichsHinweiseDefiningcode interpretationDefiningcode = null;

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

            if(!fhirObservation.getNote().isEmpty())
            {
                kommentarElement.setValue(fhirObservation.getNote().get(0).getText());
            }


            fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
            issuedDateTime = new DateTimeType(fhirObservation.getIssued());
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
        ProLaboranalytAnalytResultatDvquantity laboranalytResultat = new ProLaboranalytAnalytResultatDvquantity();

        // map value to magnitude and unit
        laboranalytResultat.setAnalytResultatMagnitude(fhirValueNumeric.doubleValue());
        laboranalytResultat.setAnalytResultatUnits(fhirValue.getUnit());

        // =======================================================================================
        // rest of the structure to build the composition with the value taken from FHIR
        ProLaboranalytCluster laboranalyt = new ProLaboranalytCluster();

        laboranalyt.setAnalytResultat(laboranalytResultat);
        laboranalyt.setAnalytResultatValue("result"); // this is the ELEMENT.name
        laboranalyt.setUntersuchterAnalytDefiningcode(untersuchterAnalyt);

        laboranalyt.setReferenzbereichsHinweiseDefiningcode(interpretationDefiningcode);

        if(kommentarElement.getValue() != null)
        {
            laboranalyt.getKommentar().add(kommentarElement);
        }

        laboranalyt.setZeitpunktValidationValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        laboranalyt.setZeitpunktErgebnisStatusValue(issuedDateTime.getValueAsCalendar().toZonedDateTime());

        return laboranalyt;
    }

    // TODO: Is it necessary to implement a map function from Composition -> DiagnosticReport?

    public static Observation map(GECCOLaborbefundComposition composition) {

        // TODO: Do we have to map all possible values back to the observation?
        Observation observation = new Observation();

        TemporalAccessor temporal;
        Coding coding;

        observation.getIdentifier().add(new Identifier()); // analyseBefundCode
        observation.getIdentifier().get(0).getType().getCoding().add(new Coding());
        observation.getIdentifier().get(0).getType().getCoding().get(0).setSystem("http://terminology.hl7.org/CodeSystem/v2-0203");
        observation.getIdentifier().get(0).getType().getCoding().get(0).setCode("OBI");


        ProLaboranalytCluster cluster = composition.getLaborergebnis().getProLaboranalyt();

        // cluster . time -> observation . effective_date
        temporal = cluster.getZeitpunktErgebnisStatusValue();
        observation.getEffectiveDateTimeType().setValue(from(((OffsetDateTime) temporal).toInstant()));

        // cluster . value -> observation . value
        ProLaboranalytAnalytResultatDvquantity value = ((ProLaboranalytAnalytResultatDvquantity) cluster.getAnalytResultat());
        observation.getValueQuantity().setValue(value.getAnalytResultatMagnitude());
        observation.getValueQuantity().setUnit(value.getAnalytResultatUnits());
        observation.getValueQuantity().setSystem("http://unitsofmeasure.org");
        observation.getValueQuantity().setCode(value.getAnalytResultatUnits());

        // set codes that come hardcoded in the inbound resources

        // observation . category
        observation.getCategory().add(new CodeableConcept());

        coding = observation.getCategory().get(0).addCoding();
        coding.setSystem("http://loing.org");
        coding.setCode("26436-6");

        coding = observation.getCategory().get(0).addCoding();
        coding.setSystem("http://terminology.hl7.org/CodeSystem/observation-category");
        coding.setCode("laboratory");

        // observation . code
        coding = observation.getCode().addCoding();
        coding.setSystem("http://loing.org");
        coding.setCode("59826-8");
        coding.setDisplay("Creatinine [Moles/volume] in Blood");
        observation.getCode().setText("Kreatinin");

        // set patient
        //observation.getSubject().setReference("Patient/"+ subjectId.getValue());

        observation.setStatus(Observation.ObservationStatus.FINAL);

        observation.getMeta().addProfile(Profile.OBSERVATION_LAB.getUri());

        observation.setId(composition.getVersionUid().toString());

        return observation;
    }
}