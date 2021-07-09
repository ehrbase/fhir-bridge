package org.ehrbase.fhirbridge.ehr.converter.specific.sofascore;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.definition.SofaScoreObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class SofaScoreObservationConverter extends ObservationToObservationConverter<SofaScoreObservation> {



    @Override
    protected SofaScoreObservation convertInternal(Observation resource) {
        SofaScoreObservation sofaScore = new SofaScoreObservation();
        mapCodes(sofaScore, resource);
        return sofaScore;
    }

    private void mapCodes(SofaScoreObservation sofaScore, Observation observation) {
        Observation.ObservationComponentComponent ns = getComponent(observation, "ns");
        Observation.ObservationComponentComponent cvs = getComponent(observation, "cvs");
        String nervensystemCode = ns.getValueCodeableConcept().getCoding().get(0).getCode();
        String herzKreislaufSystemCode = cvs.getValueCodeableConcept().getCoding().get(0).getCode();
        mapAtemtaetigkeitCode(sofaScore, observation);
        mapNervenSystemCode(sofaScore, nervensystemCode);
        mapHerzKreislaufSystemCode(sofaScore, herzKreislaufSystemCode, nervensystemCode);
        mapLeberfunktionsCode(sofaScore, observation);
        mapBlutgerinnungscode(sofaScore, observation);
        mapNierenFunktions(sofaScore, observation);
        mapSofaScoreMagnitude(sofaScore, observation);
    }

    /**
     * Get component by code
     *
     * @param code which can be: cvs, liv, ns, resp, coa, kid
     * @return the correspondent component to the code
     */
    private Observation.ObservationComponentComponent getComponent(Observation observation, String code) {
        for (Observation.ObservationComponentComponent component : observation.getComponent()) {
            if (codeOfComponentMatchesCode(component, code)) {
                return component;
            }
        }
        throw new ConversionException("The component with code '" + code + "' is not present");
    }

    private boolean codeOfComponentMatchesCode(Observation.ObservationComponentComponent component, String code) {
        for (Coding coding : component.getCode().getCoding()) {
            if (coding.getCode().equals(code)) {
                checkIfEmpty(component, code);
                return true;
            }
        }
        return false;
    }


    private void mapSofaScoreMagnitude(SofaScoreObservation sofaScore, Observation observation) {
        String sofaScoreCode = observation.getCode().getCoding().get(0).getCode();
        Long sofaScoreCodeLong = Long.parseLong(sofaScoreCode);
        sofaScore.setGesamtergebnisMagnitude(sofaScoreCodeLong);
    }

    private void mapNierenFunktions(SofaScoreObservation sofaScore, Observation observation) {
        String nierenfunktionsCode = getComponent(observation, "kid").getValueCodeableConcept().getCoding().get(0).getCode();
        switch (nierenfunktionsCode) {
            case "kid0":
                sofaScore.setNierenfunktion(SofaScoreCode.NIERENFUNKTIONS_SCORE_0.getValue());
                break;
            case "kid1":
                sofaScore.setNierenfunktion(SofaScoreCode.NIERENFUNKTIONS_SCORE_1.getValue());
                break;
            case "kid2":
                sofaScore.setNierenfunktion(SofaScoreCode.NIERENFUNKTIONS_SCORE_2.getValue());
                break;
            case "kid3":
                sofaScore.setNierenfunktion(SofaScoreCode.NIERENFUNKTIONS_SCORE_3.getValue());
                break;
            case "kid4":
                sofaScore.setNierenfunktion(SofaScoreCode.NIERENFUNKTIONS_SCORE_4.getValue());
                break;
            default:
                throw new ConversionException("The code " + nierenfunktionsCode + " is not valid for the Kidney Score");
        }
    }

    private void mapBlutgerinnungscode(SofaScoreObservation sofaScore, Observation observation) {
        String blutgerinnungsCode = getComponent(observation, "coa").getValueCodeableConcept().getCoding().get(0).getCode();
        switch (blutgerinnungsCode) {
            case "coa0":
                sofaScore.setBlutgerinnung(SofaScoreCode.BLUTGERINNUNGS_SCORE_0.getValue());
                break;
            case "coa1":
                sofaScore.setBlutgerinnung(SofaScoreCode.BLUTGERINNUNGS_SCORE_1.getValue());
                break;
            case "coa2":
                sofaScore.setBlutgerinnung(SofaScoreCode.BLUTGERINNUNGS_SCORE_2.getValue());
                break;
            case "coa3":
                sofaScore.setBlutgerinnung(SofaScoreCode.BLUTGERINNUNGS_SCORE_3.getValue());
                break;
            case "coa4":
                sofaScore.setBlutgerinnung(SofaScoreCode.BLUTGERINNUNGS_SCORE_4.getValue());
                break;
            default:
                throw new ConversionException("The code " + blutgerinnungsCode + " is not valid for the Blood clotting score");
        }
    }

    private void mapLeberfunktionsCode(SofaScoreObservation sofaScore, Observation observation) {
        String leberfunktionsCode = getComponent(observation, "liv").getValueCodeableConcept().getCoding().get(0).getCode();
        switch (leberfunktionsCode) {
            case "liv0":
                sofaScore.setLeberfunktion(SofaScoreCode.LEBERFUNKTIONS_SCORE_0.getValue());
                break;
            case "liv1":
                sofaScore.setLeberfunktion(SofaScoreCode.LEBERFUNKTIONS_SCORE_1.getValue());
                break;
            case "liv2":
                sofaScore.setLeberfunktion(SofaScoreCode.LEBERFUNKTIONS_SCORE_2.getValue());
                break;
            case "liv3":
                sofaScore.setLeberfunktion(SofaScoreCode.LEBERFUNKTIONS_SCORE_3.getValue());
                break;
            case "liv4":
                sofaScore.setLeberfunktion(SofaScoreCode.LEBERFUNKTIONS_SCORE_4.getValue());
                break;
            default:
                throw new ConversionException("The code " + leberfunktionsCode + " is not valid for the Liver score");
        }
    }

    private void mapHerzKreislaufSystemCode(SofaScoreObservation sofaScore, String herzKreislaufSystemCode, String nervensystemCode) {
        if (herzKreislaufSystemCode.equals("cvs0")) {
            sofaScore.setKardiovaskulaeresSystem(SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_0.getValue());
        } else if (herzKreislaufSystemCode.equals("cvs1")) {
            sofaScore.setKardiovaskulaeresSystem(SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_1.getValue());
        } else if (nervensystemCode.equals("cvs2")) {
            sofaScore.setKardiovaskulaeresSystem(SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_2.getValue());
        } else if (nervensystemCode.equals("cvs3")) {
            sofaScore.setKardiovaskulaeresSystem(SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_3.getValue());
        } else if (nervensystemCode.equals("cvs4")) {
            sofaScore.setKardiovaskulaeresSystem(SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_4.getValue());
        } else {
            throw new ConversionException("Either the code " + herzKreislaufSystemCode + " or " + nervensystemCode + " is not valid for the cardiovaskular score");
        }

    }

    private void mapAtemtaetigkeitCode(SofaScoreObservation sofaScore, Observation observation) {
        String atemtaetigkeitCode = getComponent(observation, "resp").getValueCodeableConcept().getCoding().get(0).getCode();

        switch (atemtaetigkeitCode) {
            case "resp0":
                sofaScore.setRespiration(SofaScoreCode.ATEMFREQUENZ_SCORE_0.getValue());
                break;
            case "resp1":
                sofaScore.setRespiration(SofaScoreCode.ATEMFREQUENZ_SCORE_1.getValue());
                break;
            case "resp2":
                sofaScore.setRespiration(SofaScoreCode.ATEMFREQUENZ_SCORE_2.getValue());
                break;
            case "resp3":
                sofaScore.setRespiration(SofaScoreCode.ATEMFREQUENZ_SCORE_3.getValue());
                break;
            case "resp4":
                sofaScore.setRespiration(SofaScoreCode.ATEMFREQUENZ_SCORE_4.getValue());
                break;
            default:
                throw new ConversionException("The code " + atemtaetigkeitCode + " is not valid for the Breath Score");
        }
    }

    private void mapNervenSystemCode(SofaScoreObservation sofaScore, String nervensystemCode) {
        switch (nervensystemCode) {
            case "ns0":
                sofaScore.setZentralesNervensystem(SofaScoreCode.NERVENSYSTEM_SCORE_0.getValue());
                break;
            case "ns1":
                sofaScore.setZentralesNervensystem(SofaScoreCode.NERVENSYSTEM_SCORE_1.getValue());
                break;
            case "ns2":
                sofaScore.setZentralesNervensystem(SofaScoreCode.NERVENSYSTEM_SCORE_2.getValue());
                break;
            case "ns3":
                sofaScore.setZentralesNervensystem(SofaScoreCode.NERVENSYSTEM_SCORE_3.getValue());
                break;
            case "ns4":
                sofaScore.setZentralesNervensystem(SofaScoreCode.NERVENSYSTEM_SCORE_4.getValue());
                break;
            default:
                throw new ConversionException("The code " + nervensystemCode + " is not valid for the Nerves Score");
        }
    }



    private void checkIfEmpty(Observation.ObservationComponentComponent component, String name) {
        if (component.getValueCodeableConcept().getCoding().isEmpty()) {
            throw new ConversionException("The component " + name + " doesn't have a code");
        }

    }
}
