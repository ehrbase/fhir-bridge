package org.ehrbase.fhirbridge.ehr.converter.specific.impfstatus;

import org.ehrbase.fhirbridge.ehr.converter.generic.ImmunizationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.ImpfstatusComposition;
import org.hl7.fhir.r4.model.Immunization;

import java.util.List;

public class ImpfstatusCompositionConverter extends ImmunizationToCompositionConverter<ImpfstatusComposition> {

    @Override
    protected ImpfstatusComposition convertInternal(Immunization immunization) {
        ImpfstatusComposition impfstatusComposition = new ImpfstatusComposition();
        if (!immunization.getOccurrenceDateTimeType().hasExtension() || !immunization.getVaccineCode().getCoding().get(0).getSystem().equals("http://hl7.org/fhir/uv/ips/CodeSystem/absent-unknown-uv-ips")) {
            impfstatusComposition.setImpfung(List.of(new ImpfungActionConverter().convert(immunization)));
        } else {
            impfstatusComposition.setUnbekannterImpfstatus(new UnbekannterImpfstatusEvaluationConverter().convert(immunization));
        }
        return impfstatusComposition;
    }
}