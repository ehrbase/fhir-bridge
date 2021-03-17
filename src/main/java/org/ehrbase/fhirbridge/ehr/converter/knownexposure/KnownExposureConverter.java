package org.ehrbase.fhirbridge.ehr.converter.knownexposure;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.CommonData;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.SARSCoV2ExpositionComposition;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;

public class KnownExposureConverter  implements CompositionConverter<SARSCoV2ExpositionComposition, Observation> {

    @Override
    public Observation fromComposition(SARSCoV2ExpositionComposition composition) throws CompositionConversionException {
        return null;
    }

    //FHIR-Profile: https://simplifier.net/forschungsnetzcovid-19/knownexposure
    //Template: https://ckm.highmed.org/ckm/templates/1246.169.1186

    @Override
    public SARSCoV2ExpositionComposition toComposition(Observation fhirObserv) throws CompositionConversionException {
        SARSCoV2ExpositionComposition composition = new SARSCoV2ExpositionComposition();

        setCompositionDefaults(fhirObserv, composition);

        mapStatus(composition, fhirObserv);
        composition.setSarsCov2Exposition(new SarsCov2ExpositionConverter().map(fhirObserv));
        return composition;
    }

    private void setCompositionDefaults(Observation object, SARSCoV2ExpositionComposition composition) {
        composition.setLanguage(Language.DE);
        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setComposer(new PartySelf());
        composition.setStartTimeValue(object.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        composition.setEndTimeValue(object.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        composition.setLocation("test");
        composition.setTerritory(Territory.DE);
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        composition.setFeederAudit(CommonData.constructFeederAudit(object));
    }

    private void mapStatus(SARSCoV2ExpositionComposition composition, Observation obs) {
        String status = obs.getStatusElement().getCode();
        if (status.equals(StatusDefiningCode.FINAL.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else if (status.equals(StatusDefiningCode.GEAENDERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
        } else if (status.equals(StatusDefiningCode.REGISTRIERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
        } else if (status.equals(StatusDefiningCode.VORLAEUFIG.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
        } else {
            throw new UnprocessableEntityException("The status " + obs.getStatus().toString() + " is not valid for known exposure.");
        }
    }
}
