package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.LoggerMessages;
import org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;

/**
 * @param <C> openEHR Composition type
 * @since 1.0.0
 */
public abstract class ObservationToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<Observation, C> {

    private static final Logger LOG = LoggerFactory.getLogger(ObservationToCompositionConverter.class);

    @Override
    public C convert(@NonNull Observation resource) {
        C composition = super.convert(resource);
        // Mandatory
        composition.setStartTimeValue(TimeConverter.convertObservationTime(resource)); // StartTimeValue
        composition.setComposer(getComposerOrDefault(resource)); // Composer
        invokeStatus(composition, resource);
        // Optional
        TimeConverter.convertObservationEndTime(resource).ifPresent(composition::setEndTimeValue); // EndTimeValue
        return composition;
    }

    protected void invokeStatus(C composition, Observation resource){
            try {
                Method setStatus = composition.getClass().getMethod("setStatus", TemporalAccessor.class);
                setStatus.invoke(mapStatus(resource));
            } catch (IllegalAccessException | InvocationTargetException exception) {
                LOG.error(LoggerMessages.printInvokeError(exception));
            } catch (NoSuchMethodException ignored){
                //ignored
            }
    }

    protected PartyProxy getComposerOrDefault(Observation resource) {
        return resource.getPerformer()
                .stream()
                .map(reference -> {
                    PartyIdentified composer = new PartyIdentified();
                    DvIdentifier identifier = new DvIdentifier();
                    if (reference.hasReference()) {
                        identifier.setId(reference.getReference());
                    } else if (reference.hasIdentifier()) {
                        identifier.setAssigner(reference.getIdentifier().getSystem());
                        identifier.setId(reference.getIdentifier().getValue());
                    }
                    composer.addIdentifier(identifier);
                    return (PartyProxy) composer;
                })
                .findFirst()
                .orElse(new PartySelf());
    }

    private StatusDefiningCode mapStatus(Observation resource) {
        switch (resource.getStatusElement().getCode()) {
            case "final":
                return StatusDefiningCode.FINAL;
            case "amended":
                return StatusDefiningCode.GEAENDERT;
            case "registered":
                return StatusDefiningCode.REGISTRIERT;
            case "preliminary":
                return StatusDefiningCode.VORLAEUFIG;
            default:
                throw new ConversionException("The status " + resource.getStatus().toString() + " is not valid for radiology report. Please enter either final, amended, registered or preliminary");
        }
    }
}