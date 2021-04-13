package org.ehrbase.fhirbridge.ehr.converter.specific.therapy;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ProcedureToProcedureActionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.CurrentStateDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.GeraetenameDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.KoerperstelleDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.MedizingeraetCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.NameDerProzedurDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.ProzedurAction;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Procedure;

import java.util.ArrayList;

public class ProzedurActionConverter extends ProcedureToProcedureActionConverter<ProzedurAction> {
    @Override
    protected ProzedurAction convertInternal(Procedure procedure) {
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
            durchgefuehrteProzedur.setDurchfuehrungsabsichtValue(mapDurchfuhrungsabsicht(procedure));
            durchgefuehrteProzedur.setKommentarValue(procedure.getNote().toString());

        } catch (UnprocessableEntityException e) {
            throw new ConversionException("Some parts of the present procedure did not contain the required elements. "
                    + e.getMessage(), e);
        }
        durchgefuehrteProzedur.setCurrentStateDefiningCode(CurrentStateDefiningCode.PLANNED);
        return durchgefuehrteProzedur;
    }

    private void mapBodySite(ProzedurAction durchgefuehrteProzedur, Procedure procedure) {
        Coding bodySiteCoding = procedure.getBodySite().get(0).getCoding().get(0);
        if (bodySiteCoding.getSystem().equals(CodeSystem.SNOMED.getUrl()) &&
                KoerperstelleDefiningCode.getCodesAsMap().containsKey(bodySiteCoding.getCode())) {
            durchgefuehrteProzedur.setKoerperstelleDefiningCode(KoerperstelleDefiningCode.getCodesAsMap().get(bodySiteCoding.getCode()));
        } else {
            throw new UnprocessableEntityException("Invalid body site for PLAIN_RADIOGRAPHY");
        }
    }

    private void mapMedizingerat(ProzedurAction durchgefuehrteProzedur, Procedure procedure) {
        if (procedure.getUsedCode() == null || procedure.getUsedCode().isEmpty()) {
            return;
        }
        Coding usedCodeCoding = procedure.getUsedCode().get(0).getCoding().get(0);
        if (usedCodeCoding.getSystem().equals(CodeSystem.SNOMED.getUrl()) && GeraetenameDefiningCode.getCodesAsMap().containsKey(usedCodeCoding.getCode())) {
            MedizingeraetCluster medizingeraetCluster = new MedizingeraetCluster();
            medizingeraetCluster.setGeraetenameDefiningCode(GeraetenameDefiningCode.getCodesAsMap().get(usedCodeCoding.getCode()));
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

    private NameDerProzedurDefiningCode mapNameDerProzedur(Procedure procedure) throws UnprocessableEntityException {
        Coding coding = procedure.getCode().getCoding().get(0);

        if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl()) && NameDerProzedurDefiningCode.getCodesAsMap().containsKey(coding.getCode())) {
            return NameDerProzedurDefiningCode.getCodesAsMap().get(coding.getCode());
        } else {
            throw new UnprocessableEntityException("Invalid name of procedure");
        }
    }
}
