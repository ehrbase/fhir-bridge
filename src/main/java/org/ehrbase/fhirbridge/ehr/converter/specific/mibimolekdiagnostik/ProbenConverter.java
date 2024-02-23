package org.ehrbase.fhirbridge.ehr.converter.specific.mibimolekdiagnostik;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvIdentifierParser;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProbeCluster;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Specimen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class ProbenConverter {
    private static final Logger LOG = LoggerFactory.getLogger(org.ehrbase.fhirbridge.ehr.converter.specific.kdsobservationlab.SpecimenConverter.class);

    public ProbeCluster convert(Specimen specimenTarget) {
        ProbeCluster probe = new ProbeCluster();
        mapProbenart(specimenTarget).ifPresent(probe::setProbenart);
        if(probe.getProbenart() == null){
            probe.setLaborprobenidentifikatorNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        mapKommentar(specimenTarget).ifPresent(probe::setKommentarValue);
        return probe;
    }

    private Optional<DvCodedText> mapProbenart(Specimen specimenTarget) {
        if (specimenTarget.hasType() && specimenTarget.getType().hasCoding() && specimenTarget.getType().getCoding().size() == 1) {
            return DvCodedTextParser.getInstance().parseFHIRCoding(specimenTarget.getType().getCoding().get(0));
        } else {
            throw new UnprocessableEntityException("The fhir bridge does not support a specimen with multiple type.codings, since a specimen cannot have several types. Please alter the data accordingly.");
        }
    }

    private Optional<DvIdentifier> mapAccessionIdentifier(Specimen specimenTarget) {
        if (specimenTarget.hasAccessionIdentifier()) {
            return Optional.of(DvIdentifierParser.parseIdentifierIntoDvIdentifier(specimenTarget.getAccessionIdentifier()));
        } else {
            return Optional.empty();
        }
    }

    private Optional<TemporalAccessor> mapReceivedTime(Specimen specimenTarget) {
        if (specimenTarget.hasReceivedTime()) {
            return Optional.of((new DateTimeType(specimenTarget.getReceivedTime())).getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }


    private Optional<String> mapKommentar(Specimen specimenTarget) {
        if (specimenTarget.hasNote()) {
            StringBuilder kommentar = new StringBuilder();
            for (Annotation annotation : specimenTarget.getNote()) {
                kommentar.append(annotation.getText());
            }
            return Optional.of(kommentar.toString());
        }
        return Optional.empty();
    }
}


