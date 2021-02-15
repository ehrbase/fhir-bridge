package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.ehr.Composition;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.PulsoxymetrieComposition;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.DomainResource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ContextConverter {

/*    public Composition setMandatoryFields(Composition composition, String time) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
       Method setLanguage = composition.getClass().getDeclaredMethod("setLanguage", Language.class);
        setLanguage.invoke(composition, Language.DE);

        Method setCategoryDefiningCode = composition.getClass().getDeclaredMethod("setCategoryDefiningCode", Category.class);
        setCategoryDefiningCode.invoke(composition, Category.EVENT);

        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setComposer(new PartySelf());
        composition.setStartTimeValue(time);
        composition.setEndTimeValue(time);
        composition.setLocation("test");
        composition.setTerritory(Territory.DE);
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
    }*/

    public Composition mapStatus(Composition composition, DomainResource domainResource) {
        try {
            return mapStatusCode(composition, domainResource);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return composition;
    }

    private Composition mapStatusCode(Composition composition, DomainResource resource) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try {
            Method setStatusDefiningCode = composition.getClass().getDeclaredMethod("setStatusDefiningCode", StatusDefiningCode.class);
            String status = getStatus(resource);
            switch (status) {
                case "registered":
                    setStatusDefiningCode.invoke(composition, StatusDefiningCode.REGISTRIERT);
                    break;
                case "final":
                    setStatusDefiningCode.invoke(composition, StatusDefiningCode.FINAL);
                    break;
                case "amended":
                    setStatusDefiningCode.invoke(composition, StatusDefiningCode.GEAENDERT);
                    break;
                case "preliminary":
                    setStatusDefiningCode.invoke(composition, StatusDefiningCode.VORLAEUFIG);
                    break;
                default:
                    throw new IllegalStateException("Invalid Code " + status + "" +
                            " for mapping of 'status', valid codes are: registered, final, amended and preliminary");
            }
        } catch (NoSuchMethodException noSuchMethodException) {
            throw new NoSuchMethodException("Class " + composition.getClass().getCanonicalName() + " is not supported for the method setStatusDefiningCode");
        }
        return composition;
    }


    private String getStatus(DomainResource resource) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try {
            Method getStatusElement = resource.getClass().getDeclaredMethod("getStatusElement");
            Object statusElement = getStatusElement.invoke(resource);
            Method getCode = statusElement.getClass().getDeclaredMethod("getCode");
            return (String) getCode.invoke(statusElement);
        } catch (NoSuchMethodException noSuchMethodException) {
            throw new NoSuchMethodException("Class " + resource.getClass().getCanonicalName() + " is not supported for the method getStatusElement().getCode().");
        }
    }
}
