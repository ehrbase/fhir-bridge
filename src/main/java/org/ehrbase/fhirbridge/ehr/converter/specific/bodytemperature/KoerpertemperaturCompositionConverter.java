package org.ehrbase.fhirbridge.ehr.converter.specific.bodytemperature;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpertemperaturcomposition.KoerpertemperaturComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpertemperaturcomposition.definition.GeccoKoerpertemperaturKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.koerpertemperaturcomposition.definition.KategorieDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class KoerpertemperaturCompositionConverter extends ObservationToCompositionConverter<KoerpertemperaturComposition> {

    @Override
    public KoerpertemperaturComposition convertInternal(@NonNull Observation resource) {
        KoerpertemperaturComposition composition = new KoerpertemperaturComposition();
        mapKategorie(composition, resource);
        composition.setKoerpertemperatur(new KoerpertemperaturObservationConverter().convert(resource));
        return composition;
    }

    private void mapKategorie(KoerpertemperaturComposition composition, Observation resource) {
        List<GeccoKoerpertemperaturKategorieElement> list = new ArrayList<>();
        GeccoKoerpertemperaturKategorieElement geccoKoerpertemperaturKategorieElement = new GeccoKoerpertemperaturKategorieElement();
        geccoKoerpertemperaturKategorieElement.setValue(KategorieDefiningCode.VITAL_SIGNS);
        list.add(geccoKoerpertemperaturKategorieElement);
        composition.setKategorie(list);
    }
}
