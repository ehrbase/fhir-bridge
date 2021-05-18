package org.ehrbase.fhirbridge.ehr.converter.specific.DnrAnordnung;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.ConsentToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.DNRAnordnungComposition;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.ArtDerRichtlinieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.BeschreibungDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.DnrAnordnungEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.DnrAnordnungKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Consent;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DnrAnordnungCompositionConverter extends ConsentToCompositionConverter<DNRAnordnungComposition> {

    @Override
    public DNRAnordnungComposition convertInternal(@NonNull Consent resource) {
        DNRAnordnungComposition composition = new DNRAnordnungComposition();
        composition.setStatusDefiningCode(createStatusDefiningCode(resource.getStatus()));
        composition.setKategorie(createDnrAnordnungKategorieElement());
        composition.setDnrAnordnung(createDnrAnordnung(resource.getProvision()));
        return composition;
    }

    private StatusDefiningCode createStatusDefiningCode(Consent.ConsentState fhirStatus) {
        switch(fhirStatus.toCode()) {
            case "draft":
                return StatusDefiningCode.ENTWORFEN;
            case "proposed":
                return StatusDefiningCode.VORGESCHLAGEN;
            case "active":
                return StatusDefiningCode.AKTIV;
            case "rejected":
                return StatusDefiningCode.VERWORFEN;
            case "inactive":
                return StatusDefiningCode.INAKTIV;
            case "entered-in-error":
                return StatusDefiningCode.EINGABEFEHLER;
            default:
                throw new UnprocessableEntityException("createStatusDefiningCode failed. Code not found for: " + fhirStatus.toString());
        }
    }

    private List<DnrAnordnungKategorieElement> createDnrAnordnungKategorieElement() {
        List<DnrAnordnungKategorieElement> items = new ArrayList<>();
        DnrAnordnungKategorieElement elem = new DnrAnordnungKategorieElement();
        elem.setValue(KategorieDefiningCode.DO_NOT_RESUSCITATE);
        items.add(elem);
        return items;
    }

    private DnrAnordnungEvaluation createDnrAnordnung(@NonNull Consent.provisionComponent provision) {
        DnrAnordnungEvaluation dnrAnordnung = new DnrAnordnungEvaluation();
        dnrAnordnung.setLanguage(Language.DE);
        dnrAnordnung.setSubject(new PartySelf());
        dnrAnordnung.setArtDerRichtlinieDefiningCode(ArtDerRichtlinieDefiningCode.DO_NO_RESUSCIATE);
        dnrAnordnung.setBeschreibungDefiningCode(BeschreibungDefiningCode.get_by_SNOMED_code(provision.getCode()));
        return dnrAnordnung;
    }
}
