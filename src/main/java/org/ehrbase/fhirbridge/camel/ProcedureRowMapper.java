package org.ehrbase.fhirbridge.camel;

import org.ehrbase.client.aql.record.Record;
import org.ehrbase.fhirbridge.camel.component.ehr.aql.RowMapper;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.ProzedurComposition;

public class ProcedureRowMapper implements RowMapper<ProzedurComposition> {

    @Override
    public ProzedurComposition mapRow(Record record, int rowNum) {
        return (ProzedurComposition) record.value(0);
    }
}
