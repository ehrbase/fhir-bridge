package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.LoggerMessages;
import org.ehrbase.fhirbridge.fhir.support.Resources;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @param <C> openEHR Composition type
 * @since 1.0.0
 */
public abstract class ObservationToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Observation, C> {

    private static final Logger LOG = LoggerFactory.getLogger(ObservationToCompositionConverter.class);

    private final ReferenceToPartyIdentifiedConverter converter = new ReferenceToPartyIdentifiedConverter();

    @Override
    public C convert(@NonNull Observation resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertObservationTime(resource)); // StartTimeValue
        invokeStatus(composition, resource);
        TimeConverter.convertObservationEndTime(resource).ifPresent(composition::setEndTimeValue); // EndTimeValue
        return composition;
    }

    @Override
    protected PartyProxy convertComposer(Observation observation) {
        if (!observation.hasPerformer()) {
            return new PartySelf();
        }
        return new ReferenceToPartyIdentifiedConverter().convert(observation.getPerformerFirstRep());
    }

    @Override
    protected Optional<PartyIdentified> convertHealthCareFacility(Observation observation) {
        return observation.getPerformer()
                .stream()
                .filter(reference -> Resources.isReferenceType(reference, ResourceType.Organization))
                .findFirst()
                .map(converter::convert);
    }

    @SuppressWarnings("unchecked")
    protected void invokeStatus(C composition, Observation resource) {
        if (resource.hasStatusElement()) {
            try {
                Method getStatus = composition.getClass().getMethod("getStatusDefiningCode");
                Class<? extends Enum> clazz = (Class<? extends Enum>) getStatus.getReturnType();
                Method setStatus = composition.getClass().getMethod("setStatusDefiningCode", clazz);
                setStatus.invoke(composition, mapStatus(resource, clazz));
            } catch (IllegalAccessException | InvocationTargetException exception) {
                LOG.error(LoggerMessages.printInvokeError(exception));
            } catch (NoSuchMethodException ignored) {
                //ignored
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Enum> T mapStatus(Observation resource, Class<? extends Enum> clazz) {
        switch (resource.getStatusElement().getCode()) {
            case "final":
                return (T) Enum.valueOf(clazz, "FINAL");
            case "amended":
                return (T) Enum.valueOf(clazz, "GEAENDERT");
            case "registered":
                return (T) Enum.valueOf(clazz, "REGISTRIERT");
            case "preliminary":
                return (T) Enum.valueOf(clazz, "VORLAEUFIG");
            default:
                throw new ConversionException("The status " + resource.getStatus().toString() + " is not supported by the fhir bridge, since it does not accept unfinished entered-in-error or corrected instances. If an fix is necessary, please contact the administrator of the Bridge. Supported is either final, amended, registered or preliminary");
        }
    }
}