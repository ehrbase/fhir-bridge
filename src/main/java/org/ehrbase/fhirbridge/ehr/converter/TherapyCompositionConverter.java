package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.GECCOProzedurComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.NameDerProzedurDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.GeraetenameDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.KoerperstelleDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.GeccoProzedurKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.ProzedurAction;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.UnbekannteProzedurEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.MedizingeraetCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.NichtDurchgefuehrteProzedurEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.CurrentStateDefiningCode;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Coding;


import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TherapyCompositionConverter implements CompositionConverter<GECCOProzedurComposition, Procedure> {

    private static final Map<String, KategorieDefiningCode> kategorieMap = new HashMap<>();
    private static final Map<String, NameDerProzedurDefiningCode> nameDerProzedurMap = new HashMap<>();
    private static final Map<String, KoerperstelleDefiningCode> koerperstelleMap = new HashMap<>();
    private static final Map<String, GeraetenameDefiningCode> geraetenameMap = new HashMap<>();

    private static final String SNOMED_SYSTEM = "http://snomed.info/sct";


    static {
        for (KategorieDefiningCode kategorie : KategorieDefiningCode.values()) {
            kategorieMap.put(kategorie.getCode(), kategorie);
        }

        for (NameDerProzedurDefiningCode nameDerProzedurDefiningCode : NameDerProzedurDefiningCode.values()) {
            nameDerProzedurMap.put(nameDerProzedurDefiningCode.getCode(), nameDerProzedurDefiningCode);
        }

        for (KoerperstelleDefiningCode koerperstelleDefiningCode : KoerperstelleDefiningCode.values()) {
            koerperstelleMap.put(koerperstelleDefiningCode.getCode(), koerperstelleDefiningCode);
        }

        for (GeraetenameDefiningCode geraetenameDefiningCode : GeraetenameDefiningCode.values()) {
            geraetenameMap.put(geraetenameDefiningCode.getCode(), geraetenameDefiningCode);
        }
    }


    @Override
    public Procedure fromComposition(GECCOProzedurComposition composition) throws CompositionConversionException {
        return null;
    }


    @Override
    public GECCOProzedurComposition toComposition(Procedure procedure) throws CompositionConversionException {
        if (procedure == null) {
            return null;
        }

        GECCOProzedurComposition result = new GECCOProzedurComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(procedure);
        result.setFeederAudit(fa);

        mapCategory(result, procedure);
        mapStartTime(result, procedure);

        switch (procedure.getStatus()) {
            case UNKNOWN:
                mapUnknown(procedure, result);
                break;
            case NOTDONE:
                mapNotDone(procedure, result);
                break;
            case ENTEREDINERROR:
                throw new CompositionConversionException("Invalid status");
            default:
                mapDone(procedure, result);
        }

        setMandatoryFields(result);

        return result;
    }

    private void mapDone(Procedure procedure, GECCOProzedurComposition composition) {

        ProzedurAction durchgefuehrteProzedur = new ProzedurAction();

        try {

            durchgefuehrteProzedur.setNameDerProzedurDefiningCode(mapNameDerProzedur(procedure));

            if (durchgefuehrteProzedur.getNameDerProzedurDefiningCode().equals(NameDerProzedurDefiningCode.PLAIN_RADIOGRAPHY) ||
                    durchgefuehrteProzedur.getNameDerProzedurDefiningCode().equals(NameDerProzedurDefiningCode.DIAGNOSTIC_ULTRASONOGRAPHY_PROCEDURE) ||
                    durchgefuehrteProzedur.getNameDerProzedurDefiningCode().equals(NameDerProzedurDefiningCode.COMPUTERIZED_AXIAL_TOMOGRAPHY_PROCEDURE)) {
                mapBodySite(durchgefuehrteProzedur, procedure);
            } else if (durchgefuehrteProzedur.getNameDerProzedurDefiningCode().equals(NameDerProzedurDefiningCode.ARTIFICIAL_RESPIRATION_PROCEDURE) ||
                    durchgefuehrteProzedur.getNameDerProzedurDefiningCode().equals(NameDerProzedurDefiningCode.OXYGEN_ADMINISTRATION_BY_NASAL_CANNULA_PROCEDURE) ||
                    durchgefuehrteProzedur.getNameDerProzedurDefiningCode().equals(NameDerProzedurDefiningCode.RESPIRATORY_THERAPY_PROCEDURE) ||
                    durchgefuehrteProzedur.getNameDerProzedurDefiningCode().equals(NameDerProzedurDefiningCode.NONINVASIVE_VENTILATION_PROCEDURE)) {
                mapMedizingerat(durchgefuehrteProzedur, procedure);
            }

            durchgefuehrteProzedur.setArtDerProzedurDefiningCode(composition.getKategorie().get(0).getValue());
            durchgefuehrteProzedur.setDurchfuehrungsabsichtValue(mapDurchfuhrungsabsicht(procedure));

            durchgefuehrteProzedur.setKommentarValue(procedure.getNote().toString());
            durchgefuehrteProzedur.setTimeValue(mapTimeValue(composition, procedure));

        } catch (UnprocessableEntityException e) {
            throw new CompositionConversionException("Some parts of the present procedure did not contain the required elements. "
                    + e.getMessage(), e);
        }

        durchgefuehrteProzedur.setLanguage(Language.DE);
        durchgefuehrteProzedur.setSubject(new PartySelf());
        durchgefuehrteProzedur.setCurrentStateDefiningCode(CurrentStateDefiningCode.PLANNED);
        composition.setProzedur(durchgefuehrteProzedur);
    }

    private void mapNotDone(Procedure procedure, GECCOProzedurComposition composition) {

        NichtDurchgefuehrteProzedurEvaluation nichtDurchgefuehrteProzedur = new NichtDurchgefuehrteProzedurEvaluation();

        nichtDurchgefuehrteProzedur.setAussageUeberDenAusschlussValue(procedure.getStatus().getDisplay());
        try {
            nichtDurchgefuehrteProzedur.setEingriffDefiningCode(mapNameDerProzedur(procedure));
        } catch (UnprocessableEntityException e) {
            throw new CompositionConversionException("Some parts of the not present procedure did not contain the required elements. "
                    + e.getMessage(), e);
        }

        nichtDurchgefuehrteProzedur.setSubject(new PartySelf());
        nichtDurchgefuehrteProzedur.setLanguage(Language.DE);

        composition.setNichtDurchgefuehrteProzedur(nichtDurchgefuehrteProzedur);

    }

    private void mapUnknown(Procedure procedure, GECCOProzedurComposition composition) {

        UnbekannteProzedurEvaluation unbekannteProzedur = new UnbekannteProzedurEvaluation();

        unbekannteProzedur.setAussageUeberDieFehlendeInformationValue(procedure.getStatus().getDisplay());

        try {
            unbekannteProzedur.setUnbekannteProzedurDefiningCode(mapNameDerProzedur(procedure));
        } catch (UnprocessableEntityException e) {
            throw new CompositionConversionException("Some parts of the unknown procedure did not contain the required elements. "
                    + e.getMessage(), e);
        }

        unbekannteProzedur.setSubject(new PartySelf());
        unbekannteProzedur.setLanguage(Language.DE);


        composition.setUnbekannteProzedur(unbekannteProzedur);
    }

    private void mapCategory(GECCOProzedurComposition composition, Procedure procedure) {
        // Map Kategorie
        composition.setKategorie(new ArrayList<>());

        for (Coding coding : procedure.getCategory().getCoding()) {
            if (coding.getSystem().equals(SNOMED_SYSTEM) && kategorieMap.containsKey(coding.getCode())) {
                GeccoProzedurKategorieElement element = new GeccoProzedurKategorieElement();
                element.setValue(kategorieMap.get(coding.getCode()));

                composition.getKategorie().add(element);
            }
        }
    }

    private void mapStartTime(GECCOProzedurComposition composition, Procedure procedure) {
        // Map Start Time
        try {

            if (!procedure.getExtension().isEmpty() && procedure.getExtension().get(0).getValue() instanceof DateTimeType) {
                composition.setStartTimeValue(((DateTimeType) procedure.getExtension().get(0).getValue()).getValueAsCalendar().toZonedDateTime());
            } else {
                composition.setStartTimeValue(procedure.getPerformedDateTimeType().getValueAsCalendar().toZonedDateTime());
            }

        } catch (NullPointerException | FHIRException ex) {
            composition.setStartTimeValue(ZonedDateTime.now());
        }
    }


    private void setMandatoryFields(GECCOProzedurComposition composition) {
        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setComposer(new PartySelf());
    }

    private NameDerProzedurDefiningCode mapNameDerProzedur(Procedure procedure) throws UnprocessableEntityException {
        Coding coding = procedure.getCode().getCoding().get(0);

        if (coding.getSystem().equals(SNOMED_SYSTEM) && nameDerProzedurMap.containsKey(coding.getCode())) {
            return nameDerProzedurMap.get(coding.getCode());
        } else {
            throw new UnprocessableEntityException("Invalid name of procedure");
        }
    }

    private void mapBodySite(ProzedurAction durchgefuehrteProzedur, Procedure procedure) {
        // Map body site for PLAIN_RADIOGRAPHY

        Coding bodySiteCoding = procedure.getBodySite().get(0).getCoding().get(0);

        if (bodySiteCoding.getSystem().equals(SNOMED_SYSTEM) &&
                koerperstelleMap.containsKey(bodySiteCoding.getCode())) {
            durchgefuehrteProzedur.setKoerperstelleDefiningCode(koerperstelleMap.get(bodySiteCoding.getCode()));
        } else {
            throw new UnprocessableEntityException("Invalid body site for PLAIN_RADIOGRAPHY");
        }
    }

    private void mapMedizingerat(ProzedurAction durchgefuehrteProzedur, Procedure procedure) {
        // Map Medizingeraet for RESP

        if (procedure.getUsedCode() == null || procedure.getUsedCode().isEmpty()) {
            return;
        }

        Coding usedCodeCoding = procedure.getUsedCode().get(0).getCoding().get(0);

        if (usedCodeCoding.getSystem().equals(SNOMED_SYSTEM) && geraetenameMap.containsKey(usedCodeCoding.getCode())) {

            MedizingeraetCluster medizingeraetCluster = new MedizingeraetCluster();

            medizingeraetCluster.setGeraetenameDefiningCode(geraetenameMap.get(usedCodeCoding.getCode()));

            durchgefuehrteProzedur.setMedizingeraet(new ArrayList<>());
            durchgefuehrteProzedur.getMedizingeraet().add(medizingeraetCluster);
        } else {
            throw new UnprocessableEntityException("Invalid medical device code");
        }
    }

    private String mapDurchfuhrungsabsicht(Procedure procedure) {
        if (procedure.getExtension().isEmpty()) {
            return null;
        }

        for (Extension extension : procedure.getExtension()) {
            if (extension.getValue() instanceof Coding) {
                return ((Coding) extension.getValue()).getDisplay();
            }
        }

        return null;
    }

    private TemporalAccessor mapTimeValue(GECCOProzedurComposition composition, Procedure procedure) {
        if (procedure.getPerformedDateTimeType() != null && procedure.getPerformedDateTimeType().isDateTime()
                && procedure.getPerformedDateTimeType().getValueAsCalendar() != null) {
            return procedure.getPerformedDateTimeType().getValueAsCalendar().toZonedDateTime();
        } else {
            return composition.getStartTimeValue();
        }
    }
}
