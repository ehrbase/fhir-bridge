package org.ehrbase.fhirbridge.ehr.converter.bloodgas;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.CommonData;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.hl7.fhir.r4.model.Observation;

public class BloodGasPanelCompositionConverter implements CompositionConverter<BefundDerBlutgasanalyseComposition, Observation> {

    @Override
    public BefundDerBlutgasanalyseComposition toComposition(Observation observation) throws CompositionConversionException {
        BloodGasPanel bloodGasPanel = new BloodGasPanel(observation);
        BlutgasAnalyseConverter blutgasAnalyseMapper = new BlutgasAnalyseConverter();
        FeederAudit feederAudit = CommonData.constructFeederAudit(observation);
        return blutgasAnalyseMapper.convert(bloodGasPanel, feederAudit);
    }

}
