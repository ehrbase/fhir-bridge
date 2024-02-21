package org.ehrbase.fhirbridge.ehr.converter.specific.geccoPatient.personendaten;

import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EinzelheitenDerKommunikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EinzelheitenDerKommunikationModusElement;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.ModusDefiningCode;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.Patient;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EinzelheitenDerKommunikationConverter {

    protected List<EinzelheitenDerKommunikationCluster> convert(Patient resource) {
        List<EinzelheitenDerKommunikationCluster> einzelheitenDerKommunikationClusters = new ArrayList<>();
        if (resource.hasContact()) {
            resource.getContact()
                    .stream()
                    .filter(Patient.ContactComponent::hasTelecom)
                    .flatMap(contact -> contact.getTelecom().stream())
                    .forEach(contactPoint ->
                            convertEinzelheitenDerKommunikationCluster(contactPoint).ifPresent(einzelheitenDerKommunikationClusters::add)
                    );
        }
        return einzelheitenDerKommunikationClusters;
    }

    private Optional<EinzelheitenDerKommunikationCluster> convertEinzelheitenDerKommunikationCluster(ContactPoint telecom) {
        EinzelheitenDerKommunikationCluster einzelheitenDerKommunikationCluster = new EinzelheitenDerKommunikationCluster();
        mapModus(telecom).ifPresent(einzelheitenDerKommunikationCluster::setModus);
        if(telecom.hasSystem() && telecom.getSystemElement().getCode().equals("url") || telecom.getSystemElement().getCode().equals("email")){
            InternetKommunikationConverter.convert(telecom).ifPresent(einzelheitenDerKommunikationCluster::setInternetKommunikation);
        }else{
            KontaktDatenConverter.convert(telecom).ifPresent(einzelheitenDerKommunikationCluster::setKontaktdaten);
        }
        if (!getJavers().compare(einzelheitenDerKommunikationCluster, new EinzelheitenDerKommunikationCluster()).hasChanges()) {
            return Optional.of(einzelheitenDerKommunikationCluster);
        } else {
            return Optional.empty();
        }
    }

    private Optional<List<EinzelheitenDerKommunikationModusElement>> mapModus(ContactPoint telecom) {
        if(telecom.hasUse()){
            return Optional.of(List.of(convertModusElement(telecom)));
        }else{
            return Optional.empty();
        }
    }

    private EinzelheitenDerKommunikationModusElement convertModusElement(ContactPoint telecom) {
        EinzelheitenDerKommunikationModusElement einzelheitenDerKommunikationModusElement = new EinzelheitenDerKommunikationModusElement();
        if ("work".equals(telecom.getUseElement().getCode())) {
            einzelheitenDerKommunikationModusElement.setValue(ModusDefiningCode.ARBEIT);
            return einzelheitenDerKommunikationModusElement;
        }
        einzelheitenDerKommunikationModusElement.setValue(ModusDefiningCode.PRIVAT); // if not work adress use everything else for home
        return einzelheitenDerKommunikationModusElement;
    }

    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValueObject(EinzelheitenDerKommunikationCluster.class)
                .build();
    }

}
