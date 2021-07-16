package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeChoice;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeDvUri;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeDvIdentifier;

import org.hl7.fhir.r4.model.Observation;

public class ProAnalytZugehoerigeLaborprobeChoiceConverter {

    public ProAnalytZugehoerigeLaborprobeChoice convertDvIdentifier(Observation observation){

        ProAnalytZugehoerigeLaborprobeDvIdentifier proAnalytZugehoerigeLaborprobeDvIdentifier = new ProAnalytZugehoerigeLaborprobeDvIdentifier();

        DvIdentifier dvIdentifier = new DvIdentifier();

        if(observation.getSpecimen().getIdentifier() == null){
            throw new ConversionException("Unknown specimen identifier");
        }
        if (!observation.getSpecimen().getIdentifier().hasAssigner() || !observation.getSpecimen().getIdentifier().hasId() || !observation.getSpecimen().getIdentifier().hasType()){
            throw new ConversionException("If Identifier of Specimen of Observation is present, it needs Assigner, Id and Type.");
        }
        if (!observation.getSpecimen().getIdentifier().getAssigner().hasDisplay()){
            throw new ConversionException("Assigner of Identifier of Specimen of Observation needs a Display.");
        }
        if (!observation.getSpecimen().getIdentifier().getType().hasText()){
            throw new ConversionException("Type of Identifier of Specimen of Observation needs Text.");
        }

        dvIdentifier.setAssigner(observation.getSpecimen().getIdentifier().getAssigner().getDisplay());

        dvIdentifier.setId(observation.getSpecimen().getIdentifier().getId());

        dvIdentifier.setType(observation.getSpecimen().getIdentifier().getType().getText());

        proAnalytZugehoerigeLaborprobeDvIdentifier.setZugehoerigeLaborprobe(dvIdentifier);

        return proAnalytZugehoerigeLaborprobeDvIdentifier;

    }

    public ProAnalytZugehoerigeLaborprobeChoice convertDvUri(Observation observation){

        ProAnalytZugehoerigeLaborprobeDvUri proAnalytZugehoerigeLaborprobeDvUri = new ProAnalytZugehoerigeLaborprobeDvUri();

        proAnalytZugehoerigeLaborprobeDvUri.setZugehoerigeLaborprobeValue(observation.getSpecimen().getTypeElement());

        return proAnalytZugehoerigeLaborprobeDvUri;

    }



}
