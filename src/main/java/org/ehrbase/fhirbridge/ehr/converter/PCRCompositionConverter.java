package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.GECCOVirologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.GeccoVirologischerBefundKategorieLoincElement;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.KategorieLoincDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.LabortestBezeichnungDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.LabortestPanelCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.NachweisDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.ProAnalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.VirusnachweistestDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

public class PCRCompositionConverter implements org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter<GECCOVirologischerBefundComposition, Observation> {
    @Override
    public GECCOVirologischerBefundComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }
        GECCOVirologischerBefundComposition composition = new GECCOVirologischerBefundComposition();
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        composition.setFeederAudit(fa);

        TemporalAccessor effectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
        composition.setStartTimeValue(effectiveDateTime);
        composition = setRequiredFields(composition);
        composition.setStatusDefiningCode(createStatusDefiningCode(observation.getStatus()));
        composition.setKategorieDefiningCode(KategorieDefiningCode.LABORATORY);
        composition.setKategorieLoinc(createKategorieLoinc());
        composition.setBefund(createBefund(effectiveDateTime, observation));
        return composition;
    }

    @Override
    public Observation fromComposition(GECCOVirologischerBefundComposition composition) {
        //your mapping code
        // return the mapped Observation of body temp
        return null;
    }

    private GECCOVirologischerBefundComposition setRequiredFields(GECCOVirologischerBefundComposition composition) {
        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setTerritory(Territory.DE);
        composition.setComposer(new PartySelf());
        return composition;
    }

    private StatusDefiningCode createStatusDefiningCode(Observation.ObservationStatus fhirStatus) {
        switch(fhirStatus.toCode()) {
            case "registered":
                return StatusDefiningCode.REGISTRIERT;
            case "preliminary":
                return StatusDefiningCode.VORLAEUFIG;
            case "final":
                return StatusDefiningCode.FINAL;
            case "amended":
                return StatusDefiningCode.GEAENDERT;
            default:
                throw new UnprocessableEntityException("createStatusDefiningCode failed. Code not found for: " + fhirStatus.toString());
        }
    }

    private List<GeccoVirologischerBefundKategorieLoincElement> createKategorieLoinc() {
        List<GeccoVirologischerBefundKategorieLoincElement> list = new ArrayList<>();
        GeccoVirologischerBefundKategorieLoincElement elem = new GeccoVirologischerBefundKategorieLoincElement();
        elem.setValue(KategorieLoincDefiningCode.LABORATORY_STUDIES_SET);
        list.add(elem);
        return list;
    }

    private BefundObservation createBefund(TemporalAccessor origin, Observation observation) {
        BefundObservation befund = new BefundObservation();
        befund.setLanguage(Language.DE);
        befund.setSubject(new PartySelf());
        befund.setOriginValue(origin);
        befund.setTimeValue(origin);
        befund.setFeederAudit(createIdentifierSection(observation));
        befund.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.DETECTION_OF_VIRUS_PROCEDURE);
        befund.setLabortestPanel(createLabortestPanel(observation));
        return befund;
    }

    private FeederAudit createIdentifierSection(Observation observation) {
        FeederAudit feederAudit = new FeederAudit();
        List<com.nedap.archie.rm.datavalues.DvIdentifier> identifierList = new ArrayList<>();
        com.nedap.archie.rm.datavalues.DvIdentifier e =  new com.nedap.archie.rm.datavalues.DvIdentifier();
        e.setAssigner(observation.getIdentifier().get(0).getAssigner().getReference());
        e.setType(observation.getIdentifier().get(0).getType().toString().split("@")[0]);
        e.setId(observation.getIdentifier().get(0).getValue());
        identifierList.add(e);
        feederAudit.setOriginatingSystemItemIds(identifierList);
        com.nedap.archie.rm.archetyped.FeederAuditDetails originatingSystemAudit = new com.nedap.archie.rm.archetyped.FeederAuditDetails();
        originatingSystemAudit.setSystemId(observation.getIdentifier().get(0).getSystem());
        feederAudit.setOriginatingSystemAudit(originatingSystemAudit);
        return feederAudit;
    }

    private LabortestPanelCluster createLabortestPanel(Observation observation) {
        LabortestPanelCluster labortestPanel = new LabortestPanelCluster();
        ProAnalytCluster analyt = new ProAnalytCluster();
        analyt.setVirusnachweistestDefiningCode(VirusnachweistestDefiningCode.SARS_COV2_COVID19_RNA_PRESENCE_IN_RESPIRATORY_SPECIMEN_BY_NAA_WITH_PROBE_DETECTION);
        switch(observation.getValueCodeableConcept().getCoding().get(0).getCode()) {
            case "260373001":
                analyt.setNachweisDefiningCode(NachweisDefiningCode.DETECTED_QUALIFIER_VALUE);
                break;
            case "419984006":
                analyt.setNachweisDefiningCode(NachweisDefiningCode.INCONCLUSIVE_QUALIFIER_VALUE);
                break;
            case "260415000":
                analyt.setNachweisDefiningCode(NachweisDefiningCode.NOT_DETECTED_QUALIFIER_VALUE);
                break;
            default:
                throw new UnprocessableEntityException("Value code " + observation.getValueCodeableConcept().getCoding().get(0).getCode() + " is not supported");
        }
        analyt.setErgebnisStatusValue(observation.getStatus().toCode());
        labortestPanel.setProAnalyt(analyt);
        return labortestPanel;
    }
}
