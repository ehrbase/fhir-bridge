package org.ehrbase.fhirbridge.ihe.xds;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class XDSValidator implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
            if(exchange.getIn().getHeader("iti65-trace")==null){
                throw new UnprocessableEntityException("ITI 41 is only supported, if the resource instance was sent via ITI65.");
            }
    }
}
