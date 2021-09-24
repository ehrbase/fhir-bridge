package org.ehrbase.fhirbridge.ehr.converter.specific.patient.personendaten;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.AdresseCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.AngabenZumTodCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.DatenZurGeburtCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EinzelheitenDerKommunikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EthnischerHintergrundCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EthnischerHintergrundDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.PersonendatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.PersonennameCluster;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Patient;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonenDatenAdminEntryConverter extends EntryEntityConverter<Patient, PersonendatenAdminEntry> {

    private final String ethnischerHintergrundExtensionUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/ethnic-group";

    @Override
    protected PersonendatenAdminEntry convertInternal(Patient resource) {
        PersonendatenAdminEntry personData = new PersonendatenAdminEntry();
        mapDataOnBirth(resource).ifPresent(personData::setDatenZurGeburt);
        mapEthnischerHintergrund(resource).ifPresent(personData::setEthnischerHintergrund);
        mapAngabenZumTod(resource, personData);
        setPersonenName(resource, personData);
        setAdresse(resource, personData);
        setEinzelheitenZurKommunikation(resource, personData);
        return personData;
    }

    private void setEinzelheitenZurKommunikation(Patient resource, PersonendatenAdminEntry personData) {
        List<EinzelheitenDerKommunikationCluster> list = new EinzelheitenDerKommunikationConverter().convert(resource);
        if (!list.isEmpty()) {
            personData.setEinzelheitenDerKommunikation(list);
        }
    }

    private void setAdresse(Patient resource, PersonendatenAdminEntry personData) {
        List<AdresseCluster> list = new AdresseConverter().convert(resource);
        if (!list.isEmpty()) {
            personData.setAdresse(list);
        }
    }

    private void setPersonenName(Patient resource, PersonendatenAdminEntry personData) {
        List<PersonennameCluster> list = new PersonenNameConverter().convert(resource);
        if (!list.isEmpty()) {
            personData.setPersonenname(list);
        }
    }

    private void mapAngabenZumTod(Patient resource, PersonendatenAdminEntry personData) {
        AngabenZumTodCluster angabenZumTodCluster = new AngabenZumTodCluster();
        if(resource.hasDeceased()){
            if(resource.hasDeceasedBooleanType()){
                personData.setVerstorbenValue(true);
            }
            if (resource.hasDeceasedDateTimeType()) {
                personData.setVerstorbenValue(true);
                angabenZumTodCluster.setSterbedatumValue(resource.getDeceasedDateTimeType().getValueAsCalendar().toZonedDateTime());
            }
        }
    }

    private Optional<List<EthnischerHintergrundCluster>> mapEthnischerHintergrund(Patient patient) {
        if (patient.hasExtension(ethnischerHintergrundExtensionUrl) && patient.getExtensionByUrl(ethnischerHintergrundExtensionUrl).hasValue()) {
            if (((Coding) patient.getExtensionByUrl(ethnischerHintergrundExtensionUrl).getValue()).hasCode()) {
                return convertEthnischerHintergrund(patient);
            }
        }
        return Optional.empty();
    }

    private Optional<List<EthnischerHintergrundCluster>> convertEthnischerHintergrund(Patient patient) {
        List<EthnischerHintergrundCluster> ethnischerHintergrundClusterList = new ArrayList<>();
        Coding codig = (Coding) patient.getExtensionByUrl(ethnischerHintergrundExtensionUrl).getValue();
        EthnischerHintergrundCluster ethnischerHintergrundCluster = new EthnischerHintergrundCluster();
        if (EthnischerHintergrundDefiningCode.getBySNOMEDCode(codig.getCode()).isPresent()) {
            EthnischerHintergrundDefiningCode.getBySNOMEDCode(codig.getCode()).ifPresent(ethnischerHintergrundCluster::setEthnischerHintergrundDefiningCode);
            ethnischerHintergrundClusterList.add(ethnischerHintergrundCluster);
            return Optional.of(ethnischerHintergrundClusterList);
        } else {
            throw new ConversionException("The SNOMED code is not supported for this entry");
        }
    }

    private Optional<DatenZurGeburtCluster> mapDataOnBirth(Patient fhirPatient) {
        if (fhirPatient.hasBirthDate()) {
            DatenZurGeburtCluster datenZurGeburtCluster = new DatenZurGeburtCluster();
            datenZurGeburtCluster.setGeburtsdatumValue(fhirPatient.getBirthDate().toInstant().atZone(ZoneId.of("Europe/Berlin")).toLocalDate());
            mapMehrereGeburten(fhirPatient).ifPresent(datenZurGeburtCluster::setKodierungFuerMehrlingsgeburten);
            return Optional.of(datenZurGeburtCluster);
        } else {
            return Optional.empty();
        }
    }

    private Optional<DvCodedText> mapMehrereGeburten(Patient fhirPatient) {
        if(fhirPatient.hasMultipleBirth()){
            if(fhirPatient.hasMultipleBirthBooleanType()){
                return Optional.of(DvCodedTextParser.parseBirthBoolean(fhirPatient.getMultipleBirthBooleanType()));
            }else{
                return Optional.of(DvCodedTextParser.parseBirthInteger(fhirPatient.getMultipleBirthIntegerType()));
            }
        }else{
            return Optional.empty();
        }
    }

}
