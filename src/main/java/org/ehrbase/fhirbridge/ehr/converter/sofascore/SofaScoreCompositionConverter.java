package org.ehrbase.fhirbridge.ehr.converter.sofascore;


import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.CommonData;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.SOFAComposition;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.definition.SOFAScoreObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SofaScoreCompositionConverter implements CompositionConverter<SOFAComposition, Observation> {
    private static final Logger LOG = LoggerFactory.getLogger(SofaScoreCompositionConverter.class);


    @Override
    public Observation fromComposition(SOFAComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public SOFAComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        SOFAComposition result = new SOFAComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(fa);
        
        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();

        // this throws UnprocessableEntityException if there is a problem with the mapping
        result.setSofaScore(new SofaScoreObservationConverter().convert(observation));

        // ======================================================================================
        // Required fields by API
        result.setLanguage(Language.DE);
        result.setLocation("test");
        result.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningcode(CategoryDefiningcode.EVENT);

        result.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        result.setComposer(new PartySelf());

        return result;
    }



}
