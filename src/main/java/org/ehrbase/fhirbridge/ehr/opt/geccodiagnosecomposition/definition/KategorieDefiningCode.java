package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum KategorieDefiningCode implements EnumValueSet {
    PULMONARYMEDICINE("pulmonaryMedicine", "", "SNOMED Clinical Terms", "418112009"),

    TRANSPLANT_MEDICINE("Transplant medicine", "", "SNOMED Clinical Terms", "788415003"),

    PSYCHIATRY("Psychiatry", "", "SNOMED Clinical Terms", "394587001"),

    RHEUMATOLOTY("rheumatoloty", "", "SNOMED Clinical Terms", "394810000"),

    DIABETIC_MEDICINE("Diabetic Medicine", "", "SNOMED Clinical Terms", "408475000"),

    INFECTIOUSDISEASES("infectiousDiseases", "", "SNOMED Clinical Terms", "394807007"),

    IMMUNOLOGY("immunology", "", "SNOMED Clinical Terms", "408480009"),

    NEUROLOGY("Neurology", "", "SNOMED Clinical Terms", "394591006"),

    VASCULARMEDICINE("vascularMedicine", "", "SNOMED Clinical Terms", "722414000"),

    NEPHROLOGY_QUALIFIER_VALUE("Nephrology (qualifier value)", "", "SNOMED Clinical Terms", "394589003"),

    COMPLICATION_DISORDER("Complication (disorder)", "", "SNOMED Clinical Terms", "116223007"),

    HEPATOLOGY("hepatology", "", "SNOMED Clinical Terms", "408472002"),

    ONCOLOGY("Oncology", "", "SNOMED Clinical Terms", "394593009"),

    GASTROENTEROLOGY("Gastroenterology", "", "SNOMED Clinical Terms", "394584008"),

    VENTILATION_STATUS_OBSERVABLE_ENTITY("Ventilation status (observable entity)", "", "SNOMED Clinical Terms", "404989005");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    KategorieDefiningCode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
    }

    public String getValue() {
        return this.value ;
    }

    public String getDescription() {
        return this.description ;
    }

    public String getTerminologyId() {
        return this.terminologyId ;
    }

    public String getCode() {
        return this.code ;
    }
}