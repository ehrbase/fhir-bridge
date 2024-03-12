package org.ehrbase.fhirbridge.ehr.converter.specific.kdsdiagnose;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

public abstract class DiagnoseCodeEvaluationConverter< E extends EntryEntity> extends EntryEntityConverter<Condition, E> {

     final Coding coding;

    public DiagnoseCodeEvaluationConverter(Coding coding) {
        this.coding = coding;
    }



}