package org.ehrbase.fhirbridge.camel;

import org.ehrbase.client.aql.record.Record;
import org.ehrbase.fhirbridge.camel.component.ehr.aql.RowMapper;
import org.ehrbase.fhirbridge.ehr.converter.ProzedurCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.ProzedurComposition;
import org.hl7.fhir.r4.model.Procedure;

public class FhirProcedureRowMapper implements RowMapper<Procedure> {

    private final ProzedurCompositionConverter converter = new ProzedurCompositionConverter();

    @Override
    public Procedure mapRow(Record record, int rowNum) {
        ProzedurComposition composition = (ProzedurComposition) record.value(0);
        return converter.fromComposition(composition);
    }
}
