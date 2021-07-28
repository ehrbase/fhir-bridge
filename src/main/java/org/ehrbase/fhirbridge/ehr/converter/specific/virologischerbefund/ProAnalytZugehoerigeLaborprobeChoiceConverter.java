package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeChoice;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeDvUri;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeDvIdentifier;

import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class ProAnalytZugehoerigeLaborprobeChoiceConverter {

    public ProAnalytZugehoerigeLaborprobeChoice convertDvIdentifier(Observation observation){

        ProAnalytZugehoerigeLaborprobeDvIdentifier proAnalytZugehoerigeLaborprobeDvIdentifier = new ProAnalytZugehoerigeLaborprobeDvIdentifier();

        mapDvIdentifier(observation).ifPresent(proAnalytZugehoerigeLaborprobeDvIdentifier::setZugehoerigeLaborprobe);

        return proAnalytZugehoerigeLaborprobeDvIdentifier;

    }

    public Optional<DvIdentifier> mapDvIdentifier(Observation observation){

        DvIdentifier dvIdentifier = new DvIdentifier();

        mapAssigner(observation).ifPresent(dvIdentifier::setAssigner);
        mapId(observation).ifPresent(dvIdentifier::setId);
        mapType(observation).ifPresent(dvIdentifier::setType);

        return Optional.of(dvIdentifier);
    }

    public Optional<String> mapAssigner (Observation observation){
        if(hasAssigner(observation)){
            if(hasDisplay(observation)){
                return Optional.of(observation.getSpecimen().getIdentifier().getAssigner().getDisplay());
            }
        }
        return Optional.empty();
    }

    public Optional<String> mapId (Observation observation){
        if(hasId(observation)){
            return Optional.of(observation.getSpecimen().getIdentifier().getId());
        }
        return Optional.empty();
    }

    public Optional<String> mapType (Observation observation){
        if(hasType(observation)){
            if(hasText(observation)){
                return Optional.of(observation.getSpecimen().getIdentifier().getType().getText());
            }
        }
        return Optional.empty();
    }

    public boolean hasAssigner (Observation observation){
        return observation.getSpecimen().getIdentifier().hasAssigner();
    }

    public boolean hasDisplay (Observation observation){
        return observation.getSpecimen().getIdentifier().getAssigner().hasDisplay();
    }

    public boolean hasId (Observation observation){
        return observation.getSpecimen().getIdentifier().hasId();
    }

    public boolean hasType (Observation observation){
        return observation.getSpecimen().getIdentifier().hasType();
    }

    public boolean hasText (Observation observation){
        return observation.getSpecimen().getIdentifier().getType().hasText();
    }


    public ProAnalytZugehoerigeLaborprobeChoice convertDvUri(Observation observation){

        ProAnalytZugehoerigeLaborprobeDvUri proAnalytZugehoerigeLaborprobeDvUri = new ProAnalytZugehoerigeLaborprobeDvUri();

        proAnalytZugehoerigeLaborprobeDvUri.setZugehoerigeLaborprobeValue(observation.getSpecimen().getTypeElement());

        return proAnalytZugehoerigeLaborprobeDvUri;

    }



}
