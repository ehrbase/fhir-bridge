package org.ehrbase.fhirbridge.ehr.converter.knownexposure;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.radiologischerBefund.BildgebendesUntersuchungsergebnisConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.GECCORadiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.SARSCoV2ExpositionComposition;
import org.hl7.fhir.r4.model.Observation;

public class KnownExposureConverter  implements CompositionConverter<SARSCoV2ExpositionComposition, Observation> {

    @Override
    public Observation fromComposition(SARSCoV2ExpositionComposition composition) throws CompositionConversionException {
        return null;
    }

    @Override
    public SARSCoV2ExpositionComposition toComposition(Observation object) throws CompositionConversionException {
        SARSCoV2ExpositionComposition composition = new SARSCoV2ExpositionComposition();

        composition = setCompositionDefaults(object, composition);

        //mapStatus(composition, object);
        //mapKategorie(composition, object);
        //composition.setBildgebendesUntersuchungsergebnis(new BildgebendesUntersuchungsergebnisConverter().map(object));
        return composition;
    }

    private SARSCoV2ExpositionComposition setCompositionDefaults(Observation object, SARSCoV2ExpositionComposition composition) {
        composition.setLanguage(Language.DE);
        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setComposer(new PartySelf());
        composition.setStartTimeValue(object.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        composition.setEndTimeValue(object.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        composition.setLocation("test");
        composition.setTerritory(Territory.DE);
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        return (composition);
    }
}
