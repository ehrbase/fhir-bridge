package org.ehrbase.fhirbridge.ehr.converter.specific.kdsdiagnose;

import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.KlinischerStatusCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.PrimaercodeKoerperstelleElement;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.SekundaercodeEvaluation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SekundaercodeEvaluationConverter extends DiagnoseCodeEvaluationConverter<SekundaercodeEvaluation> {

    public SekundaercodeEvaluationConverter(Coding extension) {
        super(extension);
    }

    @Override
    protected SekundaercodeEvaluation convertInternal(Condition resource) {
        SekundaercodeEvaluation evaluation = new SekundaercodeEvaluation();

        if (resource.hasCode() && resource.getCode().hasCoding()) {
            DvCodedTextParser.getInstance().parseFHIRCoding(coding).ifPresent(evaluation::setKodierteDiagnose);
        }
        if (resource.hasOnset()) {
            evaluation.setKlinischRelevanterZeitraumZeitpunktDesAuftretensValue(TimeConverter.convertConditionOnset(resource));
            TimeConverter.convertConditionEndTime(resource).ifPresent(evaluation::setKlinischRelevanterZeitraumZeitpunktDerGenesungValue);
        }

        if (resource.hasRecordedDate()) {
            evaluation.setFeststellungsdatumValue(resource.getRecordedDate().toInstant().atZone(ZoneId.systemDefault()));
        }

        transformBodySite(resource).ifPresent(evaluation::setKoerperstelle);

        if (resource.hasClinicalStatus() && resource.getClinicalStatus().hasCoding()) {
            KlinischerStatusCluster klinischerStatusCluster = new KlinischerStatusCluster();
            DvCodedTextParser.getInstance().parseFHIRCoding(resource.getClinicalStatus().getCoding().get(0)).ifPresent(klinischerStatusCluster::setKlinischerStatus);
            evaluation.setKlinischerStatus(klinischerStatusCluster);
        }
        return evaluation;
    }

    Optional<List<PrimaercodeKoerperstelleElement>> transformBodySite(Condition resource) {
        List<PrimaercodeKoerperstelleElement> koerperstelle = new ArrayList<>();
        if (resource.getBodySite().size() > 1) {
            for (CodeableConcept bodySite : resource.getBodySite()) {
                for (Coding coding : bodySite.getCoding()) {
                    PrimaercodeKoerperstelleElement primaercodeKoerperstelleElement = new PrimaercodeKoerperstelleElement();
                    DvCodedTextParser.getInstance().parseFHIRCoding(coding).ifPresent(primaercodeKoerperstelleElement::setValue);
                    koerperstelle.add(primaercodeKoerperstelleElement);
                }
            }
        }
        if (koerperstelle.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(koerperstelle);
    }
}

