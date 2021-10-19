package org.ehrbase.fhirbridge.ehr.converter.specific.medication;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToPointEventConverter;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.springframework.lang.NonNull;

import java.util.Optional;

public abstract class GeccoMedikationPointEventConverter<PEE extends PointEventEntity> extends MedicationStatementToPointEventConverter<PEE> {

    @Override
    public PEE convert(@NonNull MedicationStatement resource) {
        return super.convert(resource);
    }

    protected Optional<DvCodedText> getGrundDefiningCode(MedicationStatement resource) {
        if (resource.hasReasonCode() && resource.getReasonCode().size() > 0 && resource.getReasonCode().get(0).hasCoding()) {
            return Optional.of(CodingToDvCodedTextConverter.getInstance().convert(resource.getReasonCode().get(0).getCoding().get(0)));
        }
        return Optional.empty();
    }

}
