package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.fhirbridge.fhir.support.Resources;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.ResourceType;
import org.springframework.lang.NonNull;

import java.util.Optional;

public abstract class DiagnosticReportToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<DiagnosticReport, C> {

    private final ReferenceToPartyIdentifiedConverter converter = new ReferenceToPartyIdentifiedConverter();

    @Override
    public C convert(@NonNull DiagnosticReport resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertDiagnosticReportTime(resource)); // StartTimeValue
        TimeConverter.convertDiagnosticReportEndTime(resource).ifPresent(composition::setEndTimeValue); // EndTimeValue
        return composition;
    }

    @Override
    protected PartyProxy convertComposer(DiagnosticReport report) {
        if (!report.hasPerformer()) {
            return new PartySelf();
        }
        return converter.convert(report.getPerformerFirstRep());
    }

    @Override
    protected Optional<PartyIdentified> convertHealthCareFacility(DiagnosticReport report) {
        return report.getPerformer()
                .stream()
                .filter(reference -> Resources.isReferenceType(reference, ResourceType.Organization))
                .findFirst()
                .map(converter::convert);
    }
}
