package org.ehrbase.fhirbridge.ehr.converter.specific.mibimolekdiagnostik;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.ProbeZeitpunktDerProbenentnahmeDvDateTime;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProbeCluster;
import org.hl7.fhir.r4.model.Specimen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class ProbenConverter {

    public ProbeCluster convert(Specimen specimenTarget) {
        ProbeCluster probe = new ProbeCluster();
        probe.setProbenartNullFlavourDefiningCode(NullFlavour.NOT_APPLICABLE);
        probe.setLaborprobenidentifikatorNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        mapZeitpunktDerEntnahme(specimenTarget).ifPresent(probe::setZeitpunktDerProbenentnahmeValue);
        return probe;
    }

    private Optional<TemporalAccessor> mapZeitpunktDerEntnahme(Specimen specimenTarget) {
        if (specimenTarget.hasCollection() && specimenTarget.getCollection().hasCollected()) {
            return TimeConverter.convertSpecimanCollection(specimenTarget.getCollection());
        } else {
            return Optional.empty();
        }
    }
}


