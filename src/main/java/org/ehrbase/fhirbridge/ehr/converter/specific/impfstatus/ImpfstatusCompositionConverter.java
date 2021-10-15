package org.ehrbase.fhirbridge.ehr.converter.specific.impfstatus;

import org.ehrbase.fhirbridge.ehr.converter.generic.ImmunizationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.ImpfstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.ImpfungAction;
import org.hl7.fhir.r4.model.Immunization;

import java.util.ArrayList;
import java.util.List;

public class ImpfstatusCompositionConverter extends ImmunizationToCompositionConverter<ImpfstatusComposition> {

    @Override
    protected ImpfstatusComposition convertInternal(Immunization immunization) {
        ImpfstatusComposition impfstatusComposition = new ImpfstatusComposition();
        if (!immunization.getOccurrenceDateTimeType().hasExtension() || !immunization.getVaccineCode().getCoding().get(0).getSystem().equals("http://hl7.org/fhir/uv/ips/CodeSystem/absent-unknown-uv-ips")) {
            impfstatusComposition.setImpfung(convertImpfungen(immunization));
        } else {
            impfstatusComposition.setUnbekannterImpfstatus(new UnbekannterImpfstatusEvaluationConverter().convert(immunization));
        }
        return impfstatusComposition;
    }

    private List<ImpfungAction> convertImpfungen(Immunization immunization) {
        List<ImpfungAction> impfungActionsList = new ArrayList<>();
        if(immunization.hasProtocolApplied()) {
            for (Immunization.ImmunizationProtocolAppliedComponent immunizationProtocolAppliedComponent : immunization.getProtocolApplied()) {
                impfungActionsList.add(new ImpfungActionConverter(immunizationProtocolAppliedComponent).convert(immunization));
            }
        }else{
           impfungActionsList.add(new ImpfungActionConverter().convert(immunization));
        }
        return impfungActionsList;
    }
}