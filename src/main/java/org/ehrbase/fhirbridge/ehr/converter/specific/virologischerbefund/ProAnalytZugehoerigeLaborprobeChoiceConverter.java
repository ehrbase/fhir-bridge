package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

import org.ehrbase.fhirbridge.ehr.converter.parser.DvIdentifierParser;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeChoice;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeDvUri;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytZugehoerigeLaborprobeDvIdentifier;

import org.hl7.fhir.r4.model.Observation;

public class ProAnalytZugehoerigeLaborprobeChoiceConverter {

    public ProAnalytZugehoerigeLaborprobeChoice convertDvIdentifier(Observation observation){

        ProAnalytZugehoerigeLaborprobeDvIdentifier proAnalytZugehoerigeLaborprobeDvIdentifier = new ProAnalytZugehoerigeLaborprobeDvIdentifier();

        DvIdentifierParser.parseObservationIntoDvIdentifier(observation).ifPresent(proAnalytZugehoerigeLaborprobeDvIdentifier::setZugehoerigeLaborprobe);

        return proAnalytZugehoerigeLaborprobeDvIdentifier;

    }

    public ProAnalytZugehoerigeLaborprobeChoice convertDvUri(Observation observation){

        ProAnalytZugehoerigeLaborprobeDvUri proAnalytZugehoerigeLaborprobeDvUri = new ProAnalytZugehoerigeLaborprobeDvUri();

        // FIX proAnalytZugehoerigeLaborprobeDvUri.setZugehoerigeLaborprobeValue(observation.getSpecimen().getTypeElement());

        return proAnalytZugehoerigeLaborprobeDvUri;

    }
}
