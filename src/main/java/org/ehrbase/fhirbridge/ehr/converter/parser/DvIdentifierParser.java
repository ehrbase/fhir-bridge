package org.ehrbase.fhirbridge.ehr.converter.parser;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class DvIdentifierParser {

    public static DvIdentifier parseIdentifierIntoDvIdentifier(Identifier identifier) {
        DvIdentifier dvIdentifier = new DvIdentifier();
        setDvIdentifierAssinger(dvIdentifier, identifier);
        setDvIdentifierId(dvIdentifier, identifier);
        setDvIdentifierType(dvIdentifier, identifier);
        return dvIdentifier;
    }


    private static void setDvIdentifierType(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasAssigner()) {
            dvIdentifier.setAssigner(identifier.getAssigner().getDisplay());
        } else {
            dvIdentifier.setAssigner("");
        }
    }

    private static void setDvIdentifierId(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasId()) {
            dvIdentifier.setId(identifier.getId());
        } else {
            dvIdentifier.setId("");
        }
    }

    private static void setDvIdentifierAssinger(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasType()) {
            dvIdentifier.setType(identifier.getType().getText());
        } else {
            dvIdentifier.setType("");
        }
    }

    public static Optional<DvIdentifier> parseObservationIntoDvIdentifier(Observation observation){

        DvIdentifier dvIdentifier = new DvIdentifier();

        mapAssigner(observation).ifPresent(dvIdentifier::setAssigner);
        mapId(observation).ifPresent(dvIdentifier::setId);
        mapType(observation).ifPresent(dvIdentifier::setType);

        return Optional.of(dvIdentifier);
    }

    private static Optional<String> mapAssigner (Observation observation){
        if(hasAssigner(observation) && hasDisplay(observation)){
            return Optional.of(observation.getSpecimen().getIdentifier().getAssigner().getDisplay());
        }
        return Optional.empty();
    }

    private static Optional<String> mapId (Observation observation){
        if(hasId(observation)){
            return Optional.of(observation.getSpecimen().getIdentifier().getId());
        }
        return Optional.empty();
    }

    private static Optional<String> mapType (Observation observation){
        if(hasType(observation) && hasText(observation)){
            return Optional.of(observation.getSpecimen().getIdentifier().getType().getText());
        }
        return Optional.empty();
    }

    private static boolean hasAssigner (Observation observation){
        return observation.getSpecimen().getIdentifier().hasAssigner();
    }

    private static boolean hasDisplay (Observation observation){
        return observation.getSpecimen().getIdentifier().getAssigner().hasDisplay();
    }

    private static boolean hasId (Observation observation){
        return observation.getSpecimen().getIdentifier().hasId();
    }

    private static boolean hasType (Observation observation){
        return observation.getSpecimen().getIdentifier().hasType();
    }

    private static boolean hasText (Observation observation){
        return observation.getSpecimen().getIdentifier().getType().hasText();
    }
}
