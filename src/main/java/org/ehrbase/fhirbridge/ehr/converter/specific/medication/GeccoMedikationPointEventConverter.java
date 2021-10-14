package org.ehrbase.fhirbridge.ehr.converter.specific.medication;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.LoggerMessages;
import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusDefiningCode2;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public abstract class GeccoMedikationPointEventConverter<PEE extends PointEventEntity> extends MedicationStatementToPointEventConverter<PEE> {

    @Override
    public PEE convert(@NonNull MedicationStatement resource) {
        return super.convert(resource);
    }

    protected Optional<DvCodedText> getGrundDefiningCode(MedicationStatement resource) {
        if (resource.hasReasonCode() && resource.getReasonCode().size() > 0 && resource.getReasonCode().get(0).hasCoding()) {
            return DvCodedTextParser.parseFHIRCoding(resource.getReasonCode().get(0).getCoding().get(0));
        }
        return Optional.empty();
    }

}
