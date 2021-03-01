package org.ehrbase.fhirbridge.ehr.converter.sofascore;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.definition.SOFAScoreObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

public class SofaScoreObservationConverter {

    public SOFAScoreObservation convert(Observation observation) {
        SOFAScoreObservation sofaScore = new SOFAScoreObservation();
        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();
        mapCodes(sofaScore, observation);
        sofaScore.setSubject(new PartySelf());
        sofaScore.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        sofaScore.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        sofaScore.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        return sofaScore;
    }

    private void mapCodes(SOFAScoreObservation sofaScore, Observation observation) {
        String nervensystemCode = observation.getComponent().get(1)
                .getValueCodeableConcept().getCoding().get(0).getCode();
        String herzKreislaufSystemCode = observation.getComponent().get(2).getValueCodeableConcept().
                getCoding().get(0).getCode();
        mapAtemtaetigkeitCode(sofaScore, observation);
        mapNervenSystemCode(sofaScore, nervensystemCode);
        mapHerzKreislaufSystemCode(sofaScore, herzKreislaufSystemCode, nervensystemCode);
        mapLeberfunktionsCode(sofaScore, observation);
        mapBlutgerinnungscode(sofaScore, observation);
        mapNierenFunktions(sofaScore, observation);
        mapSofaScoreMagnitude(sofaScore, observation);
    }

    private void mapSofaScoreMagnitude(SOFAScoreObservation sofaScore, Observation observation) {
        String sofaScoreCode = observation.getCode().getCoding().get(0).getCode();
        Long sofaScoreCodeLong = Long.parseLong(sofaScoreCode);
        sofaScore.setSofaScoreMagnitude(sofaScoreCodeLong);
    }


    private void mapNierenFunktions(SOFAScoreObservation sofaScore, Observation observation) {
        String nierenfunktionsCode = observation.getComponent().get(5).getValueCodeableConcept().
                getCoding().get(0).getCode();
        switch (nierenfunktionsCode) {
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
                throw new UnprocessableEntityException("The code "+ nierenfunktionsCode + " is not valid for the Kidney Score");
        }
    }

    private void mapBlutgerinnungscode(SOFAScoreObservation sofaScore, Observation observation) {
        String blutgerinnungsCode = observation.getComponent().get(4).getValueCodeableConcept().
                getCoding().get(0).getCode();
        switch (blutgerinnungsCode) {
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
                throw new UnprocessableEntityException("The code "+ blutgerinnungsCode + " is not valid for the Blood clotting score");
        }
    }

    private void mapLeberfunktionsCode(SOFAScoreObservation sofaScore, Observation observation) {
        String leberfunktionsCode = observation.getComponent().get(3).getValueCodeableConcept().
                getCoding().get(0).getCode();
        switch (leberfunktionsCode) {
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
                throw new UnprocessableEntityException("The code "+ leberfunktionsCode + " is not valid for the Liver score");
        }
    }

    private void mapHerzKreislaufSystemCode(SOFAScoreObservation sofaScore, String herzKreislaufSystemCode, String nervensystemCode) {
        if (herzKreislaufSystemCode.equals("cvs1")) {
            sofaScore.setHerzKreislaufSystem(SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_1.getValue());
        } else if (nervensystemCode.equals("cvs2")) {
            sofaScore.setHerzKreislaufSystem(SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_2.getValue());
        } else if (nervensystemCode.equals("cvs3")) {
            sofaScore.setHerzKreislaufSystem(SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_3.getValue());
        } else if (nervensystemCode.equals("cvs4")) {
            sofaScore.setHerzKreislaufSystem(SofaScoreCode.HERZKREISLAUFSYSTEM_SCORE_4.getValue());
        }else{
            throw new UnprocessableEntityException("Either the code "+ herzKreislaufSystemCode + " or "+ nervensystemCode +" is not valid for the cardiovaskular score");
        }

    }

    private void mapAtemtaetigkeitCode(SOFAScoreObservation sofaScore, Observation observation) {
        String atemtaetigkeitCode = observation.getComponent().get(0).getValueCodeableConcept().
                getCoding().get(0).getCode();

        switch (atemtaetigkeitCode) {
            case "resp1":
                sofaScore.setAtemtatigkeit(SofaScoreCode.ATEMFREQUENZ_SCORE_1.getValue());
                break;
            case "resp2":
                sofaScore.setAtemtatigkeit(SofaScoreCode.ATEMFREQUENZ_SCORE_2.getValue());
                break;
            case "resp3":
                sofaScore.setAtemtatigkeit(SofaScoreCode.ATEMFREQUENZ_SCORE_3.getValue());
                break;
            case "resp4":
                sofaScore.setAtemtatigkeit(SofaScoreCode.ATEMFREQUENZ_SCORE_4.getValue());
                break;
            default:
                throw new UnprocessableEntityException("The code "+ atemtaetigkeitCode + " is not valid for the Breath Score");
        }
    }

    private void mapNervenSystemCode(SOFAScoreObservation sofaScore, String nervensystemCode) {
        switch (nervensystemCode) {
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
                throw new UnprocessableEntityException("The code "+ nervensystemCode + " is not valid for the Nerves Score");
        }
    }
}
