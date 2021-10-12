package org.ehrbase.fhirbridge.ehr.converter.specific.sofascore;

import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.definition.SofaScoreObservation;
import org.hl7.fhir.r4.model.Observation;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"java:S1192", "java:S6212"})
public class SofaScoreObservationConverter extends ObservationToObservationConverter<SofaScoreObservation> {

    private static final String RESP_0 = "resp0";
    private static final String RESP_1 = "resp1";
    private static final String RESP_2 = "resp2";
    private static final String RESP_3 = "resp3";
    private static final String RESP_4 = "resp4";

    private static final String CVS_0 = "cvs0";
    private static final String CVS_1 = "cvs1";
    private static final String CVS_2 = "cvs2";
    private static final String CVS_3 = "cvs3";
    private static final String CVS_4 = "cvs4";

    private static final String NS_0 = "ns0";
    private static final String NS_1 = "ns1";
    private static final String NS_2 = "ns2";
    private static final String NS_3 = "ns3";
    private static final String NS_4 = "ns4";

    private static final String KID_0 = "kid0";
    private static final String KID_1 = "kid1";
    private static final String KID_2 = "kid2";
    private static final String KID_3 = "kid3";
    private static final String KID_4 = "kid4";

    private static final String LIV_0 = "liv0";
    private static final String LIV_1 = "liv1";
    private static final String LIV_2 = "liv2";
    private static final String LIV_3 = "liv3";
    private static final String LIV_4 = "liv4";

    private static final String COA_0 = "coa0";
    private static final String COA_1 = "coa1";
    private static final String COA_2 = "coa2";
    private static final String COA_3 = "coa3";
    private static final String COA_4 = "coa4";

    @Override
    protected SofaScoreObservation convertInternal(Observation observation) {
        SofaScoreObservation result = new SofaScoreObservation();
        convertRespirationSystem(observation, result);
        convertCardiovascularSystem(observation, result);
        convertNervousSystem(observation, result);
        convertKidneys(observation, result);
        convertLiver(observation, result);
        convertCoagulation(observation, result);
        convertOverallOutcome(observation, result);
        return result;
    }



    private void convertRespirationSystem(Observation observation, SofaScoreObservation result) {
        if(getComponent(observation, "resp").isPresent() && getComponent(observation, "resp").get().hasDataAbsentReason()){
            result.setRespirationNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }else{
            convertRepirationSystemValue(observation).ifPresent(result::setRespiration);
        }
    }

    private Optional<DvOrdinal> convertRepirationSystemValue(Observation observation) {
        return getComponent(observation, "resp")
                .filter(Observation.ObservationComponentComponent::hasValueCodeableConcept)
                .map(component -> {
                    String code = component.getValueCodeableConcept().getCoding().get(0).getCode();
                    switch (code) {
                        case RESP_0:
                            return SofaScoreCode.ATEMFREQUENZ_SCORE_0.getValue();
                        case RESP_1:
                            return SofaScoreCode.ATEMFREQUENZ_SCORE_1.getValue();
                        case RESP_2:
                            return SofaScoreCode.ATEMFREQUENZ_SCORE_2.getValue();
                        case RESP_3:
                            return SofaScoreCode.ATEMFREQUENZ_SCORE_3.getValue();
                        case RESP_4:
                            return SofaScoreCode.ATEMFREQUENZ_SCORE_4.getValue();
                        default:
                            throw new ConversionException("The code " + code + " is not valid for the respiration score");
                    }
                });
    }

    private void convertCardiovascularSystem(Observation observation, SofaScoreObservation result) {
        if(getComponent(observation, "cvs").isPresent() && getComponent(observation, "cvs").get().hasDataAbsentReason()){
            result.setKardiovaskulaeresSystemNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }else{
            convertCardiovascularSystemValue(observation).ifPresent(result::setKardiovaskulaeresSystem);
        }
    }

    private Optional<DvOrdinal> convertCardiovascularSystemValue(Observation observation) {
        return getComponent(observation, "cvs")
                .filter(Observation.ObservationComponentComponent::hasValueCodeableConcept)
                .map(component -> {
                    String code = component.getValueCodeableConcept().getCoding().get(0).getCode();
                    switch (code) {
                        case CVS_0:
                            return SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_0.getValue();
                        case CVS_1:
                            return SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_1.getValue();
                        case CVS_2:
                            return SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_2.getValue();
                        case CVS_3:
                            return SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_3.getValue();
                        case CVS_4:
                            return SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_4.getValue();
                        default:
                            throw new ConversionException("The code " + code + " is not valid for the cardiovaskular score");
                    }
                });
    }

    private void convertNervousSystem(Observation observation, SofaScoreObservation result) {
        if(getComponent(observation, "ns").isPresent() && getComponent(observation, "ns").get().hasDataAbsentReason()){
            result.setZentralesNervensystemNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }else{
            convertNervousSystemValue(observation).ifPresent(result::setZentralesNervensystem);
        }
    }


    private Optional<DvOrdinal> convertNervousSystemValue(Observation observation) {
        return getComponent(observation, "ns")
                .filter(Observation.ObservationComponentComponent::hasValueCodeableConcept)
                .map(component -> {
                    String code = component.getValueCodeableConcept().getCoding().get(0).getCode();
                    switch (code) {
                        case NS_0:
                            return SofaScoreCode.NERVENSYSTEM_SCORE_0.getValue();
                        case NS_1:
                            return SofaScoreCode.NERVENSYSTEM_SCORE_1.getValue();
                        case NS_2:
                            return SofaScoreCode.NERVENSYSTEM_SCORE_2.getValue();
                        case NS_3:
                            return SofaScoreCode.NERVENSYSTEM_SCORE_3.getValue();
                        case NS_4:
                            return SofaScoreCode.NERVENSYSTEM_SCORE_4.getValue();
                        default:
                            throw new ConversionException("The code " + code + " is not valid for the nervous system score");
                    }
                });
    }

    private void convertKidneys(Observation observation, SofaScoreObservation result) {
        if(getComponent(observation, "kid").isPresent() && getComponent(observation, "kid").get().hasDataAbsentReason()){
            result.setNierenfunktionNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }else{
            convertKidneysValue(observation).ifPresent(result::setNierenfunktion);
        }
    }

    private Optional<DvOrdinal> convertKidneysValue(Observation observation) {
        return getComponent(observation, "kid")
                .filter(Observation.ObservationComponentComponent::hasValueCodeableConcept)
                .map(component -> {
                    String code = component.getValueCodeableConcept().getCoding().get(0).getCode();
                    switch (code) {
                        case KID_0:
                            return SofaScoreCode.NIERENFUNKTIONS_SCORE_0.getValue();
                        case KID_1:
                            return SofaScoreCode.NIERENFUNKTIONS_SCORE_1.getValue();
                        case KID_2:
                            return SofaScoreCode.NIERENFUNKTIONS_SCORE_2.getValue();
                        case KID_3:
                            return SofaScoreCode.NIERENFUNKTIONS_SCORE_3.getValue();
                        case KID_4:
                            return SofaScoreCode.NIERENFUNKTIONS_SCORE_4.getValue();
                        default:
                            throw new ConversionException("The code " + code + " is not valid for the kidney score");
                    }
                });
    }

    private void convertLiver(Observation observation, SofaScoreObservation result) {
        if(getComponent(observation, "liv").isPresent() && getComponent(observation, "liv").get().hasDataAbsentReason()){
            result.setLeberfunktionNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }else{
            convertLiverValue(observation).ifPresent(result::setLeberfunktion);
        }
    }

    private Optional<DvOrdinal> convertLiverValue(Observation observation) {
        return getComponent(observation, "liv")
                .filter(Observation.ObservationComponentComponent::hasValueCodeableConcept)
                .map(component -> {
                    String code = component.getValueCodeableConcept().getCoding().get(0).getCode();
                    switch (code) {
                        case LIV_0:
                            return SofaScoreCode.LEBERFUNKTIONS_SCORE_0.getValue();
                        case LIV_1:
                            return SofaScoreCode.LEBERFUNKTIONS_SCORE_1.getValue();
                        case LIV_2:
                            return SofaScoreCode.LEBERFUNKTIONS_SCORE_2.getValue();
                        case LIV_3:
                            return SofaScoreCode.LEBERFUNKTIONS_SCORE_3.getValue();
                        case LIV_4:
                            return SofaScoreCode.LEBERFUNKTIONS_SCORE_4.getValue();
                        default:
                            throw new ConversionException("The code " + code + " is not valid for the liver score");
                    }
                });
    }

    private void convertCoagulation(Observation observation, SofaScoreObservation result) {
        if(getComponent(observation, "coa").isPresent() && getComponent(observation, "coa").get().hasDataAbsentReason()){
            result.setBlutgerinnungNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }else{
            convertCoagulationValue(observation).ifPresent(result::setBlutgerinnung);
        }
    }

    private Optional<DvOrdinal> convertCoagulationValue(Observation observation) {
        return getComponent(observation, "coa")
                .filter(Observation.ObservationComponentComponent::hasValueCodeableConcept)
                .map(component -> {
                    String code = component.getValueCodeableConcept().getCoding().get(0).getCode();
                    switch (code) {
                        case COA_0:
                            return SofaScoreCode.BLUTGERINNUNGS_SCORE_0.getValue();
                        case COA_1:
                            return SofaScoreCode.BLUTGERINNUNGS_SCORE_1.getValue();
                        case COA_2:
                            return SofaScoreCode.BLUTGERINNUNGS_SCORE_2.getValue();
                        case COA_3:
                            return SofaScoreCode.BLUTGERINNUNGS_SCORE_3.getValue();
                        case COA_4:
                            return SofaScoreCode.BLUTGERINNUNGS_SCORE_4.getValue();
                        default:
                            throw new ConversionException("The code " + code + " is not valid for the blood clotting score");
                    }
                });
    }

    private void convertOverallOutcome(Observation observation, SofaScoreObservation result) {
        if(observation.hasDataAbsentReason()){
            result.setGesamtergebnisNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }else{
            convertOverallOutcomeValue(observation).ifPresent(value -> result.setGesamtergebnisMagnitude(value.longValue()));
        }
    }

    private Optional<Integer> convertOverallOutcomeValue(Observation observation) {
        if (!observation.hasValueIntegerType()) {
            return Optional.empty();
        }
        return Optional.of(observation.getValueIntegerType().getValue());
    }


    private Optional<Observation.ObservationComponentComponent> getComponent(Observation observation, String code) {
        return observation.getComponent()
                .stream()
                .filter(component -> Objects.equals(component.getCode().getCoding().get(0).getCode(), code))
                .findFirst();
    }
}
