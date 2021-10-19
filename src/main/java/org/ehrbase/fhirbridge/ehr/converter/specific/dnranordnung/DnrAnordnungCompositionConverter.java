package org.ehrbase.fhirbridge.ehr.converter.specific.dnranordnung;

import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ConsentToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.DNRAnordnungComposition;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.DnrAnordnungEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.DnrAnordnungKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Consent;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DnrAnordnungCompositionConverter extends ConsentToCompositionConverter<DNRAnordnungComposition> {

    @Override
    public DNRAnordnungComposition convertInternal(@NonNull Consent resource) {
        DNRAnordnungComposition composition = new DNRAnordnungComposition();
        composition.setStatusDefiningCode(createStatusDefiningCode(resource.getStatus()));
        composition.setKategorie(createDnrAnordnungKategorieElement());
        composition.setDnrAnordnung(createDnrAnordnung(resource));
        return composition;
    }

    private StatusDefiningCode createStatusDefiningCode(Consent.ConsentState fhirStatus) {
        switch (fhirStatus.toCode()) {
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
            default: // cannot be reached currently - maintained for security purposes
                throw new ConversionException("createStatusDefiningCode failed. Code not found for: " + fhirStatus);
        }
    }

    private List<DnrAnordnungKategorieElement> createDnrAnordnungKategorieElement() {
        List<DnrAnordnungKategorieElement> items = new ArrayList<>();
        DnrAnordnungKategorieElement elem = new DnrAnordnungKategorieElement();
        elem.setValue(KategorieDefiningCode.DO_NOT_RESUSCITATE);
        items.add(elem);
        return items;
    }

    // Not an Resource so just leave it as it is
    private DnrAnordnungEvaluation createDnrAnordnung(@NonNull Consent resource) {
        DnrAnordnungEvaluation dnrAnordnung = new DnrAnordnungEvaluation();
        dnrAnordnung.setLanguage(Language.DE);
        dnrAnordnung.setSubject(new PartySelf());
        dnrAnordnung.setArtDerRichtlinie(DvCodedTextParser.parseDefiningCode(KategorieDefiningCode.DO_NOT_RESUSCITATE));
        getCoding(resource.getProvision()).ifPresent(dnrAnordnung::setBeschreibung);
        return dnrAnordnung;
    }

    private Optional<DvCodedText> getCoding(Consent.provisionComponent provision) {
        Optional<List<Coding>> codings = provision.getCode().stream()
                .map(CodeableConcept::getCoding)
                .findFirst();
        return Optional.of(CodingToDvCodedTextConverter.getInstance()
                .convert(codings.get().get(0)));
    }
}
